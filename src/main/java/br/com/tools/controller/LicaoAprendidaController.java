package br.com.tools.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.tools.configuracao.ConnectionFactory;
import br.com.tools.model.IndexLogin;
import br.com.tools.model.LicaoAprendidaDTO;
import br.com.tools.model.ReferenciaDTO;
import br.com.tools.model.SubReferenciaDTO;
import br.com.tools.model.UsuarioDTO;
import br.com.tools.model.vo.LicaoAprendida;
import br.com.tools.services.LicaoAprendidaService;
import br.com.tools.services.ReferenciaServices;
import br.com.tools.services.SubReferenciaServices;
import br.com.tools.services.UsuarioServices;
import br.com.tools.services.impl.LicaoAprendidaServiceImpl;
import br.com.tools.services.impl.ReferenciaServicesImpl;
import br.com.tools.services.impl.SubReferenciaServicesImpl;
import br.com.tools.services.impl.UsuarioServicesImpl;
import br.com.tools.utils.ConstantDataManager;

@Controller
@RequestMapping("licao")
public class LicaoAprendidaController {
	
	@RequestMapping(value = "/open/{tipo}", method = RequestMethod.GET)
	public ModelAndView open(LicaoAprendidaDTO licaoAprendida, ModelMap model, @PathVariable("tipo") Integer tipo, HttpServletRequest request) {

		Connection connection = null;
		
		ModelAndView modelAndView = null;
		if(2==tipo) {
			 modelAndView = new ModelAndView("licao/filtrar");
		} else if(1==tipo) {
			 modelAndView = new ModelAndView("licao/filtrar");

			 IndexLogin indexLogin = new IndexLogin();
			 UsuarioDTO usuarioDTO = indexLogin.getUsuarioDTO();
			 
			 HttpSession sessao = request.getSession();
			 UsuarioDTO usuario = (UsuarioDTO) sessao.getAttribute(ConstantDataManager.OBJETO_USUARIO_SESSAO);
			 if(null == usuario) {
				 sessao.setAttribute(ConstantDataManager.OBJETO_USUARIO_SESSAO, usuarioDTO);
			 }
		} else {
			 modelAndView = new ModelAndView("licao/form-novo");
		}
		
		List<UsuarioDTO> usuarios = null;
		List<ReferenciaDTO> referencias = null;
		List<SubReferenciaDTO> subReferencias = null;
		try 
		{
			connection = ConnectionFactory.getConnection();
			UsuarioServices usuarioServices = new UsuarioServicesImpl(connection);
			usuarios = usuarioServices.getComboUsuarios();
			usuarios.sort(Comparator.comparing(u -> u.getUsuario()));
			model.addAttribute(ConstantDataManager.OBJETO_LISTA_USUARIOS, usuarios);
			
			ReferenciaServices referenciaServices = new ReferenciaServicesImpl(connection);
			referencias = referenciaServices.getComboReferencias();
			referencias.sort(Comparator.comparing(r -> r.getDescricaoReferencia()));
			model.addAttribute(ConstantDataManager.OBJETO_LISTA_REFERENCIAS, referencias);

			SubReferenciaServices subReferenciaServices = new SubReferenciaServicesImpl(connection);
			subReferencias = subReferenciaServices.getComboSubReferencias();
			subReferencias.sort(Comparator.comparing(s -> s.getDescricaoSubReferencia()));
			model.addAttribute(ConstantDataManager.OBJETO_LISTA_SUB_REFERENCIAS, subReferencias);

			ConnectionFactory.closeConnection(connection);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	public ModelAndView listar(LicaoAprendidaDTO licaoAprendidaDTO, ModelMap model) {
		
		ModelAndView modelAndView = new ModelAndView("licao/lista");
		try 
		{
			Connection connection = ConnectionFactory.getConnection();
			LicaoAprendidaService licaoAprendidaService = new LicaoAprendidaServiceImpl(connection);
			licaoAprendidaService.getAll(licaoAprendidaDTO);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		List<LicaoAprendida> licoes = licaoAprendidaDTO.getLicoes();
		licoes.sort(Comparator.comparing(l -> l.getDescricaoSubReferencia()));

		model.addAttribute(ConstantDataManager.OBJETO_LISTA_LICAO_APRENDIDA, licoes);
		return modelAndView;
	}
	
	@RequestMapping(value = "/lista", method = RequestMethod.POST)
	public ModelAndView gravar(@Valid LicaoAprendidaDTO licaoAprendidaDTO, BindingResult result, RedirectAttributes redirectAttributes, ModelMap model, HttpServletRequest request) {
	
		if(result.hasErrors()) {
			
			if(licaoAprendidaDTO.getId() <= 0) {
				return open(licaoAprendidaDTO, model, 0, null);
			} else {
				List<String> msgs = new ArrayList<String>();
				for(ObjectError objectError : result.getAllErrors()) {
					msgs.add(objectError.getDefaultMessage());
					model.addAttribute(ConstantDataManager.MESSAGE, msgs);
				}
				return detalhar(licaoAprendidaDTO.getId(), model);
			}
		}

		boolean isUpdated = false;
		String message = ConstantDataManager.BLANK;		

		try
		{
			IndexLogin indexLogin = new IndexLogin();
			UsuarioDTO usuario = indexLogin.getUsuarioDTO();

			Connection connection = ConnectionFactory.getConnection();
			LicaoAprendidaService licaoAprendidaService = new LicaoAprendidaServiceImpl(connection);
			isUpdated = licaoAprendidaService.gravar(licaoAprendidaDTO, usuario.getCodigoUsuario());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		if(isUpdated) {
			message = ConstantDataManager.MESSAGE_DADOS_ALTERADOS_SUCESSO;
		} else {
			message = ConstantDataManager.MESSAGE_PROBLEMAS_SALVAR_ALTERAR;
		}
		
		redirectAttributes.addFlashAttribute(message);
		return new ModelAndView("redirect:lista");
	}

	@RequestMapping(value = "/form-editar/{id}", method = RequestMethod.GET)
	public ModelAndView detalhar(@PathVariable("id") Integer id, ModelMap model) {

		ModelAndView modelAndView = new ModelAndView("licao/form-editar");
		LicaoAprendida licaoAprendida = new LicaoAprendida();
		
		List<UsuarioDTO> usuarios = null;
		List<ReferenciaDTO> referencias = null;
		List<SubReferenciaDTO> subReferencias = null;

		try 
		{
			Connection connection = ConnectionFactory.getConnection();
			LicaoAprendidaService licaoAprendidaService = new LicaoAprendidaServiceImpl(connection);
			licaoAprendida = licaoAprendidaService.getLicaoById(id);

			UsuarioServices usuarioServices = new UsuarioServicesImpl(connection);
			usuarios = usuarioServices.getComboUsuarios();
			model.addAttribute(ConstantDataManager.OBJETO_LISTA_USUARIOS, usuarios);
			
			ReferenciaServices referenciaServices = new ReferenciaServicesImpl(connection);
			referencias = referenciaServices.getComboReferencias();
			model.addAttribute(ConstantDataManager.OBJETO_LISTA_REFERENCIAS, referencias);

			SubReferenciaServices subReferenciaServices = new SubReferenciaServicesImpl(connection);
			subReferencias = subReferenciaServices.getComboSubReferencias();
			model.addAttribute(ConstantDataManager.OBJETO_LISTA_SUB_REFERENCIAS, subReferencias);

			ConnectionFactory.closeConnection(connection);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new RuntimeException();
		}

		model.addAttribute(ConstantDataManager.MESSAGE); //message valid gravar/editar
		model.addAttribute(ConstantDataManager.OBJETO_LICAO_APRENDIDA, licaoAprendida);
		return modelAndView;
	}
	
	@RequestMapping(value = "/lista/{id}/{inativo}", method = RequestMethod.GET)
	public ModelAndView remover(@PathVariable("id") Integer id, @PathVariable("inativo") Boolean inativo, ModelMap model, HttpServletRequest request) {

		boolean isRemoved = false;
		String message = ConstantDataManager.BLANK;

		try 
		{	
			IndexLogin indexLogin = new IndexLogin();
			UsuarioDTO usuario = indexLogin.getUsuarioDTO();
			
			Connection connection = ConnectionFactory.getConnection();
			LicaoAprendidaService licaoAprendidaService = new LicaoAprendidaServiceImpl(connection);
			isRemoved = licaoAprendidaService.remove(id, inativo, usuario.getCodigoUsuario());
			
			ConnectionFactory.closeConnection(connection);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new RuntimeException();
		}

		if(isRemoved) {
			if(inativo) {
				message = "Id: "+ id + " "+ ConstantDataManager.MESSAGE_ATIVAR_SUCESSO;
			} else {
				message = "Id: "+ id + " "+ ConstantDataManager.MESSAGE_INATIVAR_SUCESSO;
			}
		} else {
			message = ConstantDataManager.MESSAGE_PROBLEMAS_INATIVAR;
		}
		model.addAttribute(ConstantDataManager.MESSAGE, message);
		return listar(new LicaoAprendidaDTO(), model);
	}
}

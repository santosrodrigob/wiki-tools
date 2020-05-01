/**
 * @author Rodrigo Borges 
 * @date 01/04/2020
 */
package br.com.tools.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import br.com.tools.configuracao.ConnectionFactory;
import br.com.tools.model.IndexLogin;
import br.com.tools.model.ReferenciaDTO;
import br.com.tools.model.SubReferenciaDTO;
import br.com.tools.model.UsuarioDTO;
import br.com.tools.model.vo.SubReferencia;
import br.com.tools.services.ReferenciaServices;
import br.com.tools.services.SubReferenciaServices;
import br.com.tools.services.impl.ReferenciaServicesImpl;
import br.com.tools.services.impl.SubReferenciaServicesImpl;
import br.com.tools.utils.ConstantDataManager;
import br.com.tools.utils.StringUtils;

@Controller
@RequestMapping("sub-referencia")
public class SubReferenciaController {
	
	@RequestMapping(value = "/open/{tipo}", method = RequestMethod.GET)
	public ModelAndView open(SubReferenciaDTO subReferencia, ModelMap model, @PathVariable("tipo") Integer tipo) {

		ModelAndView modelAndView = null;
		if(1==tipo) {
			 modelAndView = new ModelAndView("sub-referencia/filtrar-sub");
		} else {
			 modelAndView = new ModelAndView("sub-referencia/form-novo-sub");
		}
		
		List<ReferenciaDTO> referencias = null;
		try 
		{
			Connection connection = ConnectionFactory.getConnection();
			ReferenciaServices referenciaServices = new ReferenciaServicesImpl(connection);
			referencias = referenciaServices.getComboReferencias();
			referencias.sort(Comparator.comparing(r -> r.getDescricaoReferencia()));
			model.addAttribute(ConstantDataManager.OBJETO_LISTA_REFERENCIAS, referencias);

			ConnectionFactory.closeConnection(connection);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/lista-sub", method = RequestMethod.GET)
	public ModelAndView listar(SubReferenciaDTO subReferenciaDTO, ModelMap model) {

		ModelAndView modelAndView = new ModelAndView("sub-referencia/lista-sub");
		try 
		{
			Connection connection = ConnectionFactory.getConnection();

			SubReferenciaServices subReferenciaServices = new SubReferenciaServicesImpl(connection);
			subReferenciaServices.getAll(subReferenciaDTO);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		List<SubReferencia> subReferencias = subReferenciaDTO.getSubReferencias();
		subReferencias = subReferencias
		.stream()
		.sorted(Comparator.comparing(l -> l.getDescricaoSubReferencia()))
		.sorted(Comparator.comparing(l -> l.getDescricaoReferencia()))
		.collect(Collectors.toList());

		model.addAttribute(ConstantDataManager.OBJETO_LISTA_SUB_REFERENCIAS, subReferencias);
		return modelAndView;
	}

	@RequestMapping(value = "/lista-sub", method = RequestMethod.POST)
	public ModelAndView gravar(@Valid SubReferenciaDTO subReferenciaDTO, BindingResult result, RedirectAttributes redirectAttributes, ModelMap model, HttpServletRequest request) {

		if(result.hasErrors()) {
			
			if(subReferenciaDTO.getCodigoSubReferencia() <= 0) {
				return open(subReferenciaDTO, model, 0);
			} else {
				List<String> msgs = new ArrayList<String>();
				for(ObjectError objectError : result.getAllErrors()) {
					msgs.add(objectError.getDefaultMessage());
					model.addAttribute(ConstantDataManager.MESSAGE, msgs);
				}
				return detalhar(subReferenciaDTO.getCodigoSubReferencia(), model);
			}
		}

		boolean isUpdated = false;
		String message = ConstantDataManager.BLANK;		

		try
		{
			IndexLogin indexLogin = new IndexLogin();
			UsuarioDTO usuario = indexLogin.getUsuarioDTO();

			Connection connection = ConnectionFactory.getConnection();
			SubReferenciaServices subReferenciaServices = new SubReferenciaServicesImpl(connection);
			isUpdated = subReferenciaServices.gravar(subReferenciaDTO, usuario.getCodigoUsuario());
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
		return new ModelAndView("redirect:lista-sub");
	}
	
	@RequestMapping(value = "/form-editar-sub/{id}", method = RequestMethod.GET)
	public ModelAndView detalhar(@PathVariable("id") Integer id, ModelMap model) {

		ModelAndView modelAndView = new ModelAndView("sub-referencia/form-editar-sub");
		List<ReferenciaDTO> referencias = null;
		SubReferencia subReferencia = null;
		try 
		{
			Connection connection = ConnectionFactory.getConnection();

			ReferenciaServices referenciaServices = new ReferenciaServicesImpl(connection);
			referencias = referenciaServices.getComboReferencias();
			referencias.sort(Comparator.comparing(r -> r.getDescricaoReferencia()));
			model.addAttribute(ConstantDataManager.OBJETO_LISTA_REFERENCIAS, referencias);
			
			SubReferenciaServices subReferenciaServices = new SubReferenciaServicesImpl(connection);
			subReferencia = subReferenciaServices.getReferenciaById(id);

			ConnectionFactory.closeConnection(connection);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new RuntimeException();
		}

		model.addAttribute(ConstantDataManager.MESSAGE); //message valid gravar/editar
		model.addAttribute(ConstantDataManager.OBJETO_SUB_REFERENCIA, subReferencia);
		return modelAndView;
	}
	
	@RequestMapping(value = "/lista-sub/{id}/{inativo}", method = RequestMethod.GET)
	public ModelAndView remover(@PathVariable("id") Integer id, @PathVariable("inativo") Boolean inativo, ModelMap model, HttpServletRequest request) {

		boolean isRemoved = false;
		String message = ConstantDataManager.BLANK;

		try 
		{
			IndexLogin indexLogin = new IndexLogin();
			UsuarioDTO usuario = indexLogin.getUsuarioDTO();
			
			Connection connection = ConnectionFactory.getConnection();
			SubReferenciaServices subReferenciaServices = new SubReferenciaServicesImpl(connection);
			isRemoved = subReferenciaServices.remove(id, inativo, usuario.getCodigoUsuario());
			
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
		return listar(new SubReferenciaDTO(), model);
	}
	
	@RequestMapping(value = "ajaxComboSubReferencia", method = RequestMethod.POST)
	public ResponseEntity<String> getSubReferencias(final HttpServletRequest request) {
		
		final Gson gson = new Gson();
		Map<String, Object> result = new TreeMap<String, Object>();
		
		String codigoReferenciaParameter = request.getParameter(ConstantDataManager.PARAMETER_CODIGO);

		List<SubReferenciaDTO> subReferenciaDTOs = null;
		try
		{
			Integer codigoReferencia = 0;
					
			if(StringUtils.isLong(codigoReferenciaParameter)) {
				
				Connection connection = ConnectionFactory.getConnection();
				
				codigoReferencia = Integer.valueOf(codigoReferenciaParameter);
				
				SubReferenciaServices subReferenciaServices = new SubReferenciaServicesImpl(connection);
				subReferenciaDTOs = subReferenciaServices.getComboByReferencia(codigoReferencia);
				subReferenciaDTOs.sort(Comparator.comparing(r -> r.getDescricaoSubReferencia()));
				result.put(ConstantDataManager.OBJETO_LISTA_SUB_REFERENCIAS, subReferenciaDTOs);

				ConnectionFactory.closeConnection(connection);
			}

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		final String json = gson.toJson(result);
		return ResponseEntity.ok(json);
	}
}

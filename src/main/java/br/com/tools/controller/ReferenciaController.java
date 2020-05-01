/**
 * @author Rodrigo Borges 
 * @date 01/04/2020
 */
package br.com.tools.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
import br.com.tools.model.ReferenciaDTO;
import br.com.tools.model.UsuarioDTO;
import br.com.tools.model.vo.Referencia;
import br.com.tools.services.ReferenciaServices;
import br.com.tools.services.impl.ReferenciaServicesImpl;
import br.com.tools.utils.ConstantDataManager;

@Controller
@RequestMapping("referencia")
public class ReferenciaController {
	
	@RequestMapping(value = "/open/{tipo}", method = RequestMethod.GET)
	public ModelAndView open(ReferenciaDTO referencia, ModelMap model, @PathVariable("tipo") Integer tipo) {

		ModelAndView modelAndView = null;
		if(1==tipo) {
			 modelAndView = new ModelAndView("referencia/filtrar-referencia");
		} else {
			 modelAndView = new ModelAndView("referencia/form-novo-referencia");
		}
				
		return modelAndView;
	}

	@RequestMapping(value = "/lista-referencia", method = RequestMethod.GET)
	public ModelAndView listar(ReferenciaDTO referenciaDTO, ModelMap model) {
		
		ModelAndView modelAndView = new ModelAndView("referencia/lista-referencia");
		try 
		{
			Connection connection = ConnectionFactory.getConnection();
			ReferenciaServices referenciaService = new ReferenciaServicesImpl(connection);
			referenciaService.getAll(referenciaDTO);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		List<Referencia> referencias = referenciaDTO.getReferencias();
		referencias.sort(Comparator.comparing(l -> l.getDescricaoReferencia()));

		model.addAttribute(ConstantDataManager.OBJETO_LISTA_REFERENCIAS, referencias);
		return modelAndView;
	}
	
	@RequestMapping(value = "/lista-referencia", method = RequestMethod.POST)
	public ModelAndView gravar(@Valid ReferenciaDTO referenciaDTO, BindingResult result, RedirectAttributes redirectAttributes, ModelMap model) {

		if(result.hasErrors()) {
			
			if(referenciaDTO.getCodigoReferencia() <= 0) {
				return open(referenciaDTO, model, 0);
			} else {
				List<String> msgs = new ArrayList<String>();
				for(ObjectError objectError : result.getAllErrors()) {
					msgs.add(objectError.getDefaultMessage());
					model.addAttribute(ConstantDataManager.MESSAGE, msgs);
				}
				return detalhar(referenciaDTO.getCodigoReferencia(), model);
			}
		}

		boolean isUpdated = false;
		String message = ConstantDataManager.BLANK;		

		try
		{
			IndexLogin indexLogin = new IndexLogin();
			UsuarioDTO usuarioDTO = indexLogin.getUsuarioDTO();
			
			Connection connection = ConnectionFactory.getConnection();
			ReferenciaServices referenciaServices = new ReferenciaServicesImpl(connection);
			isUpdated = referenciaServices.gravar(referenciaDTO, usuarioDTO.getCodigoUsuario());
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
		return new ModelAndView("redirect:lista-referencia");
	}
	
	@RequestMapping(value = "/form-editar-referencia/{id}", method = RequestMethod.GET)
	public ModelAndView detalhar(@PathVariable("id") Integer id, ModelMap model) {

		ModelAndView modelAndView = new ModelAndView("referencia/form-editar-referencia");
		ReferenciaDTO referenciaDTO = null;
		try 
		{
			Connection connection = ConnectionFactory.getConnection();
			ReferenciaServices referenciaServices = new ReferenciaServicesImpl(connection);
			referenciaDTO = referenciaServices.getReferenciaById(id);

			ConnectionFactory.closeConnection(connection);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new RuntimeException();
		}

		model.addAttribute(ConstantDataManager.MESSAGE); //message valid gravar/editar
		model.addAttribute(ConstantDataManager.OBJETO_REFERENCIA, referenciaDTO);
		return modelAndView;
	}
	
	@RequestMapping(value = "/lista-referencia/{id}/{inativo}", method = RequestMethod.GET)
	public ModelAndView remover(@PathVariable("id") Integer id, @PathVariable("inativo") Boolean inativo, ModelMap model) {

		boolean isRemoved = false;
		String message = ConstantDataManager.BLANK;

		try 
		{
			IndexLogin indexLogin = new IndexLogin();
			UsuarioDTO usuarioDTO = indexLogin.getUsuarioDTO();
			
			Connection connection = ConnectionFactory.getConnection();
			ReferenciaServices referenciaServices = new ReferenciaServicesImpl(connection);
			isRemoved = referenciaServices.remove(id, inativo, usuarioDTO.getCodigoUsuario());
			
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
		return listar(new ReferenciaDTO(), model);
	}
}

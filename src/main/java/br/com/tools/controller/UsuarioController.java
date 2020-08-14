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
import br.com.tools.model.UsuarioDTO;
import br.com.tools.model.vo.Usuario;
import br.com.tools.services.UsuarioServices;
import br.com.tools.services.impl.UsuarioServicesImpl;
import br.com.tools.utils.ConstantDataManager;
import br.com.tools.utils.StringUtils;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@RequestMapping(value = "/open", method = RequestMethod.GET)
	public ModelAndView open(UsuarioDTO usuario, ModelMap model) {
		return new ModelAndView("usuario/form-novo-usuario");
	}
	
	@RequestMapping(value = "/lista-usuario", method = RequestMethod.GET)
	public ModelAndView listar(UsuarioDTO usuarioDTO, ModelMap model) {
		
		ModelAndView modelAndView = new ModelAndView("usuario/lista-usuario");
		try 
		{
			Connection connection = ConnectionFactory.getConnection();
			UsuarioServices usuarioServices = new UsuarioServicesImpl(connection);
			usuarioServices.getAll(usuarioDTO);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		List<Usuario> usuarios = usuarioDTO.getUsuarios();
		usuarios = usuarios.stream()
		.sorted(Comparator.comparing(u -> u.getUsuario()))
		.sorted(Comparator.comparing(u -> u.getAtivo()))
		.collect(Collectors.toList());
		
		model.addAttribute(ConstantDataManager.OBJETO_LISTA_USUARIOS, usuarios);
		return modelAndView;
	}
	
	@RequestMapping(value = "/lista-usuario", method = RequestMethod.POST)
	public ModelAndView gravar(@Valid UsuarioDTO usuarioDTO, BindingResult result, RedirectAttributes redirectAttributes, ModelMap model) {

		if(result.hasErrors()) {

			if(usuarioDTO.getCodigoUsuario() <= 0) {
				return open(usuarioDTO, model);
			} else {
				List<String> msgs = new ArrayList<String>();
				for(ObjectError objectError : result.getAllErrors()) {
					msgs.add(objectError.getDefaultMessage());
					model.addAttribute(ConstantDataManager.MESSAGE, msgs);
				}
				return detalhar(usuarioDTO.getCodigoUsuario(), model);
			}
		}

		boolean isUpdated = false;
		String message = ConstantDataManager.BLANK;		

		try
		{
			IndexLogin indexLogin = new IndexLogin();
			UsuarioDTO usu = indexLogin.getUsuarioDTO();
			
			Connection connection = ConnectionFactory.getConnection();
			UsuarioServices usuarioServices = new UsuarioServicesImpl(connection);
			isUpdated = usuarioServices.gravar(usuarioDTO, usu.getCodigoUsuario());
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
		return new ModelAndView("redirect:lista-usuario");
	}
	
	@RequestMapping(value = "/form-editar-usuario/{id}", method = RequestMethod.GET)
	public ModelAndView detalhar(@PathVariable("id") Integer id, ModelMap model) {

		ModelAndView modelAndView = new ModelAndView("usuario/form-editar-usuario");
		Usuario usuario = null;
		try 
		{
			Connection connection = ConnectionFactory.getConnection();
			UsuarioServices usuarioServices = new UsuarioServicesImpl(connection);
			usuario = usuarioServices.getUsuarioById(id);

			ConnectionFactory.closeConnection(connection);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new RuntimeException();
		}

		model.addAttribute(ConstantDataManager.MESSAGE); //message valid gravar/editar
		model.addAttribute(ConstantDataManager.OBJETO_USUARIO, usuario);
		return modelAndView;
	}
	
	@RequestMapping(value = "/lista-usuario/{id}/{ativo}", method = RequestMethod.GET)
	public ModelAndView remover(@PathVariable("id") Integer id, @PathVariable("ativo") Boolean ativo, ModelMap model) {

		boolean isRemoved = false;
		String message = ConstantDataManager.BLANK;

		try 
		{
			Connection connection = ConnectionFactory.getConnection();
			UsuarioServices usuarioServices = new UsuarioServicesImpl(connection);
			isRemoved = usuarioServices.remove(id, ativo);
			
			ConnectionFactory.closeConnection(connection);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new RuntimeException();
		}

		if(isRemoved) {
			if(!ativo) {
				message = "Id: "+ id + " "+ ConstantDataManager.MESSAGE_ATIVAR_SUCESSO;
			} else {
				message = "Id: "+ id + " "+ ConstantDataManager.MESSAGE_INATIVAR_SUCESSO;
			}
		} else {
			message = ConstantDataManager.MESSAGE_PROBLEMAS_INATIVAR;
		}
		model.addAttribute(ConstantDataManager.MESSAGE, message);
		return listar(new UsuarioDTO(), model);
	}
	
	@RequestMapping(value = "ajaxProximo", method = RequestMethod.POST)
	public ResponseEntity<String> getProximo(final HttpServletRequest request) {
		
		Gson gson = new Gson();
		Map<String, Object> result = new TreeMap<String, Object>();
		
		String codigoAtualParameter = request.getParameter(ConstantDataManager.PARAMETER_CODIGO);
		Integer proximo = 0;

		try
		{
			Integer codigoAtual = 0;

			Connection connection = ConnectionFactory.getConnection();
			
			if(StringUtils.isLong(codigoAtualParameter)) {
				codigoAtual = Integer.valueOf(codigoAtualParameter);
			}
			UsuarioServices usuarioServices = new UsuarioServicesImpl(connection);
			proximo = usuarioServices.getProximoCodigo(codigoAtual);
			result.put(ConstantDataManager.OBJETO_PROXIMO, proximo);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		String json = gson.toJson(result);
		return ResponseEntity.ok(json);
	}

}

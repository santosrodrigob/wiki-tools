/**
 * @author Rodrigo Borges 
 * @date 01/04/2020
 */
package br.com.tools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String loginForm() {
		return "login/form-login";
	}
}

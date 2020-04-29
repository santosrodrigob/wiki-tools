package br.com.tools.model;

import java.sql.Connection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import br.com.tools.configuracao.ConnectionFactory;
import br.com.tools.services.UsuarioServices;
import br.com.tools.services.impl.UsuarioServicesImpl;

public class IndexLogin {

	private UsuarioDTO usuarioDTO;

	public IndexLogin(){
	
		usuarioDTO = new UsuarioDTO();
		SecurityContext context = SecurityContextHolder.getContext();
		if(context instanceof SecurityContext)
		{
			Authentication authentication = context.getAuthentication();
			if(authentication instanceof Authentication)
			{
				usuarioDTO.setCodigoUsuario(Integer.valueOf(((User)authentication.getPrincipal()).getUsername()));
				getObjetoUsuarioSessao(usuarioDTO);
			}
		}
	}
	
	private void getObjetoUsuarioSessao(UsuarioDTO usuario) {
		
		Connection connection;
		try 
		{
			connection = ConnectionFactory.getConnection();

			UsuarioServices usuarioServices = new UsuarioServicesImpl(connection);
			usuario = usuarioServices.getObjetoUsuarioSessao(usuario);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public final UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public final void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}
}

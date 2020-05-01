/**
 * @author Rodrigo Borges 
 * @date 01/04/2020
 */
package br.com.tools.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.tools.model.vo.Usuario;

public class UsuarioDTO extends DadosCriacao {
	
	private int id;
	@Min(value = 1, message="Código, preenchimento obrigatório!")
	private int codigoUsuario;
	
	@NotNull(message="Descrição, preenchimento obrigatório!") 
	@NotEmpty(message="Descrição, preenchimento obrigatório!")
	private String usuario;

	@NotNull(message="Senha, preenchimento obrigatório!") 
	@NotEmpty(message="Senha, preenchimento obrigatório!")
	private String senha;

	@Min(value = 1, message="Código Acesso, preenchimento obrigatório!")
	private int codigoAcessoWiki;

	private int ativo;
	
	List<Usuario> usuarios = new ArrayList<Usuario>();

	public final int getId() {
		return this.id;
	}
	public final void setId(int id) {
		this.id = id;
	}
	public final String getUsuario() {
		return usuario;
	}
	public final void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public final String getSenha() {
		return senha;
	}
	public final void setSenha(String senha) {
		this.senha = senha;
	}
	public final int getAtivo() {
		return ativo;
	}
	public final void setAtivo(int ativo) {
		this.ativo = ativo;
	}
	public final int getCodigoUsuario() {
		return codigoUsuario;
	}
	public final void setCodigoUsuario(int codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}	
	public final int getCodigoAcessoWiki() {
		return this.codigoAcessoWiki;
	}
	public final void setCodigoAcessoWiki(int codigoAcessoWiki) {
		this.codigoAcessoWiki = codigoAcessoWiki;
	}
	public void add(Usuario usuario) {
		this.usuarios.add(usuario);
	}
	
	public final List<Usuario> getUsuarios() {
		return this.usuarios;
	}
	
	public void add(UsuarioDTO usuarioDTO) {
		this.setCodigoUsuario(usuarioDTO.getCodigoUsuario());
		this.setSenha(usuarioDTO.getSenha());
		this.setUsuario(usuarioDTO.getUsuario());
	}
	public boolean validaUsuario(UsuarioDTO usuarioDTO) {
		if(this.codigoUsuario == usuarioDTO.getCodigoUsuario() && this.senha.equalsIgnoreCase(usuarioDTO.getSenha())) {
			return true;
		} else {
			return false;
		}
	}
}

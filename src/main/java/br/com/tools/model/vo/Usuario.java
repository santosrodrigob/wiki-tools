/**
 * @author Rodrigo Borges 
 * @date 01/04/2020
 */
package br.com.tools.model.vo;

public class Usuario extends Role {

	private int id;
	private int codigoUsuario;
	private String usuario;
	private String senha;
	private int codigoAcessoWiki;
	private int ativo;
	private int inativo;

	public final int getId() {
		return id;
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
	public final int getInativo() {
		return inativo;
	}
	public final void setInativo(int inativo) {
		this.inativo = inativo;
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
		this.setCodigoUsuario(usuario.getCodigoUsuario());
		this.setSenha(usuario.getSenha());
		this.setUsuario(usuario.getUsuario());
	}
	public boolean validaUsuario(Usuario usuario) {
		if(this.codigoUsuario == usuario.getCodigoUsuario() && this.senha.equalsIgnoreCase(usuario.getSenha())) {
			return true;
		} else {
			return false;
		}
	}
}

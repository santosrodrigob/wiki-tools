package br.com.tools.model.vo;

import br.com.tools.model.DadosCriacao;

public class Role extends DadosCriacao {
	
	private int codigoAcesso;
	private String acesso;
	
	public final int getCodigoAcesso() {
		return codigoAcesso;
	}
	public final void setCodigoAcesso(int codigoAcesso) {
		this.codigoAcesso = codigoAcesso;
	}
	public final String getAcesso() {
		return acesso;
	}
	public final void setAcesso(String acesso) {
		this.acesso = acesso;
	}
}

package br.com.tools.model.vo;

public class LicaoAprendida extends SubReferencia {

	private String cliente;
	private String erro;
	private String solucao;

	public final String getCliente() {
		return cliente;
	}
	public final void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public final String getErro() {
		return erro;
	}
	public final void setErro(String erro) {
		this.erro = erro;
	}
	public final String getSolucao() {
		return solucao;
	}
	public final void setSolucao(String solucao) {
		this.solucao = solucao;
	}
}

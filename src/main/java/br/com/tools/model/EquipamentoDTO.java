package br.com.tools.model;

public class EquipamentoDTO extends DadosCriacao {
	
	private int codigoEquipamento;
	private String descricaoEquipamento;
	
	public int getCodigoEquipamento() {
		return codigoEquipamento;
	}
	public void setCodigoEquipamento(int codigoEquipamento) {
		this.codigoEquipamento = codigoEquipamento;
	}
	public String getDescricaoEquipamento() {
		return descricaoEquipamento;
	}
	public void setDescricaoEquipamento(String descricaoEquipamento) {
		this.descricaoEquipamento = descricaoEquipamento;
	}
}

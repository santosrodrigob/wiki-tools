/**
 * @author Rodrigo Borges 
 * @date 01/04/2020
 */
package br.com.tools.model.vo;

public class SubReferencia extends Referencia {

	private int codigoSubReferencia;
	private String descricaoSubReferencia;
	
	public final int getCodigoSubReferencia() {
		return codigoSubReferencia;
	}
	public final void setCodigoSubReferencia(int codigoSubReferencia) {
		this.codigoSubReferencia = codigoSubReferencia;
	}
	public final String getDescricaoSubReferencia() {
		return descricaoSubReferencia;
	}
	public final void setDescricaoSubReferencia(String descricaoSubReferencia) {
		this.descricaoSubReferencia = descricaoSubReferencia;
	}
}

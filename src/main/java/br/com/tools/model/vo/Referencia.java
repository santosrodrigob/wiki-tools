/**
 * @author Rodrigo Borges 
 * @date 01/04/2020
 */
package br.com.tools.model.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class Referencia extends Usuario {

	private int codigoReferencia;
	
	@NotNull(message="Descrição, preenchimento obrigatório!") 
	@NotEmpty(message="Descrição, preenchimento obrigatório!")
	private String descricaoReferencia;
	
	public final int getCodigoReferencia() {
		return codigoReferencia;
	}
	public final void setCodigoReferencia(int codigoReferencia) {
		this.codigoReferencia = codigoReferencia;
	}
	public final String getDescricaoReferencia() {
		return descricaoReferencia;
	}
	public final void setDescricaoReferencia(String descricaoReferencia) {
		this.descricaoReferencia = descricaoReferencia;
	}
}

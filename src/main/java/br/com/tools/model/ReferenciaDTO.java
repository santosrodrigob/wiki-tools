/**
 * @author Rodrigo Borges 
 * @date 01/04/2020
 */
package br.com.tools.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.tools.model.vo.Referencia;

public class ReferenciaDTO extends DadosCriacao {

	private int codigoReferencia;
	
	@NotNull(message="Descrição, preenchimento obrigatório!") 
	@NotEmpty(message="Descrição, preenchimento obrigatório!")
	private String descricaoReferencia;
	
	private int inativo;
	
	List<Referencia> referencias = new ArrayList<Referencia>();
	
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
	
	public final int getInativo() {
		return inativo;
	}
	public final void setInativo(int inativo) {
		this.inativo = inativo;
	}
	
	public final List<Referencia> getReferencias() {
		return this.referencias;
	}
	
	public void add(Referencia referencia) {
		this.referencias.add(referencia);
	}

}

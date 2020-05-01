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

import br.com.tools.model.vo.SubReferencia;

public class SubReferenciaDTO extends DadosCriacao {

	private int codigoSubReferencia;

	@NotNull(message="Descrição, preenchimento obrigatório!") 
	@NotEmpty(message="Descrição, preenchimento obrigatório!")
	private String descricaoSubReferencia;

	@Min(value = 1, message="Referência, preenchimento obrigatório!")
	private int codigoReferencia;
	
	private int inativo;
	
	List<SubReferencia> subReferencias = new ArrayList<SubReferencia>();

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
	public final int getCodigoReferencia() {
		return codigoReferencia;
	}
	public final void setCodigoReferencia(int codigoReferencia) {
		this.codigoReferencia = codigoReferencia;
	}
	public final int getInativo() {
		return inativo;
	}
	public final void setInativo(int inativo) {
		this.inativo = inativo;
	}
	
	public final List<SubReferencia> getSubReferencias() {
		return this.subReferencias;
	}
	
	public void add(SubReferencia subReferencia) {
		this.subReferencias.add(subReferencia);
	}
}

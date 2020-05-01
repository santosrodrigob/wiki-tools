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

import br.com.tools.model.vo.LicaoAprendida;

public class LicaoAprendidaDTO extends DadosCriacao {

	private int id;
	private String cliente;

	@NotNull(message="Erro, preenchimento obrigatório!") 
	@NotEmpty(message="Erro, preenchimento obrigatório!")
	private String erro;

	@NotNull(message="Solução, preenchimento obrigatório!") 
	@NotEmpty(message="Solução, preenchimento obrigatório!")
	private String solucao;

	@Min(value = 1, message="Referência, preenchimento obrigatório!")
	private int codigoReferencia;

	@Min(value = 1, message="Sub-referência, preenchimento obrigatório!")
	private int codigoSubReferencia;

	@Min(value = 1, message="Usuário/Técnico, preenchimento obrigatório!")
	private int codigoUsuario;
	
	private int inativo;
	
	List<LicaoAprendida> licoes = new ArrayList<LicaoAprendida>();
	
	public final int getId() {
		return id;
	}
	public final void setId(int id) {
		this.id = id;
	}
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
	public final int getCodigoReferencia() {
		return codigoReferencia;
	}
	public final void setCodigoReferencia(int codigoReferencia) {
		this.codigoReferencia = codigoReferencia;
	}
	public final int getCodigoSubReferencia() {
		return codigoSubReferencia;
	}
	public final void setCodigoSubReferencia(int codigoSubReferencia) {
		this.codigoSubReferencia = codigoSubReferencia;
	}
	public final int getCodigoUsuario() {
		return codigoUsuario;
	}
	public final void setCodigoUsuario(int codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	public final int getInativo() {
		return inativo;
	}
	public final void setInativo(int inativo) {
		this.inativo = inativo;
	}
	public void add(LicaoAprendida licaoAprendida) {
		this.licoes.add(licaoAprendida);
	}
	public final List<LicaoAprendida> getLicoes() {
		return this.licoes;
	}
}
package br.com.tools.model;

public class ClienteDTO extends DadosCriacao {
	
	private int codigo;
	private long cnpj;
	private String nomeCliente;
	private int codigoEquipamento;
	private String ip;
	private String dnsPrimario;
	private String dnsSecundario;
	private String mac;
	private String local;
	private String usuario;
	private String senha;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public long getCnpj() {
		return cnpj;
	}
	public void setCnpj(long cnpj) {
		this.cnpj = cnpj;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public int getCodigoEquipamento() {
		return codigoEquipamento;
	}
	public void setCodigoEquipamento(int codigoEquipamento) {
		this.codigoEquipamento = codigoEquipamento;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getDnsPrimario() {
		return dnsPrimario;
	}
	public void setDnsPrimario(String dnsPrimario) {
		this.dnsPrimario = dnsPrimario;
	}
	public String getDnsSecundario() {
		return dnsSecundario;
	}
	public void setDnsSecundario(String dnsSecundario) {
		this.dnsSecundario = dnsSecundario;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}

package br.com.tools.services;

public interface MigrationServices {

	boolean validaVersao(int versaoInicial, int versaoFinal) throws Exception;
	boolean execSql(String sql) throws Exception;
	boolean atualizaVersao(int versaoFinal) throws Exception;
}

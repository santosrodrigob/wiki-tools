package br.com.tools.dao;

public interface MigrationDao {

	boolean validaVersao(int versaoInicial, int versaoFinal) throws Exception;
	boolean execSql(String sql) throws Exception;
}

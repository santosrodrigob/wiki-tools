package br.com.tools.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.tools.dao.MigrationDao;

public class MigrationDaoImpl implements MigrationDao {

	private Connection connection;
	
	public MigrationDaoImpl(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public boolean validaVersao(int versaoInicial, int versaoFinal) throws Exception {
		
		boolean isValid = false;
		
		StringBuilder query = new StringBuilder();
		query.append("select ");
		query.append("	versao_banco ");
		query.append("from ");
		query.append("	tb_modulo_versionamento ");
		query.append("where ");
		query.append("	codigo_modulo = 1");
		
		PreparedStatement statement = null;
						
		try 
		{
			statement = this.connection.prepareStatement(query.toString());
			statement.executeQuery();
			ResultSet rs = statement.getResultSet();
			
			Integer versaoAtual = 0;
			
			while (rs.next()) {
				versaoAtual = rs.getInt("versao_banco");
			}
			
			if(versaoAtual > 0 && versaoAtual==versaoInicial) {
				isValid = true;
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new RuntimeException();
		}
		finally 
		{
			if(statement!=null) {
				statement.close();
			}
		}		
		return isValid;
	}

	@Override
	public boolean execSql(String sql) throws Exception {
		
		boolean isValid = false;
		
		StringBuilder query = new StringBuilder();
		query.append(sql);
		
		PreparedStatement statement = null;
						
		try 
		{
			statement = this.connection.prepareStatement(query.toString());
			statement.execute();
			System.out.println(statement.getUpdateCount());
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new RuntimeException();
		}
		finally 
		{
			if(statement!=null) {
				statement.close();
			}
		}		
		return isValid;		
	}
}

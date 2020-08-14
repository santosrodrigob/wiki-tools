package br.com.tools.controller;

import java.sql.Connection;

import br.com.tools.configuracao.ConnectionFactory;
import br.com.tools.services.MigrationServices;
import br.com.tools.services.impl.MigrationServicesImpl;

public class MigrationsController {

	private Connection connection = null;
	private final int versaoInicial;
	private final int versaoFinal;
	
	public MigrationsController(final int versaoInicial, final int versaoFinal) {
		this.versaoInicial = versaoInicial;
		this.versaoFinal = versaoFinal;
	}
	
	private boolean verificaVersao() throws Exception {
		
		boolean isValid = false;
		
		if(this.versaoFinal<=this.versaoInicial) {
			return false;
		}
		
		try 
		{
			connection = ConnectionFactory.getConnection();
			final MigrationServices migrationServices = new MigrationServicesImpl(connection);
//			isValid = migrationServices.validaVersao(versaoInicial, versaoFinal);
			
			isValid = true;
			
			ConnectionFactory.closeConnection(connection);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			if(connection!=null)
			{
				connection.close();
			}
		}		
		return isValid;
	}

	public void executaMigration(String sql) {
		
		boolean isValid = false;

		try 
		{
			isValid = verificaVersao();
			if(isValid) {
				isValid = false;
				connection = ConnectionFactory.getConnection();
				final MigrationServices migrationServices = new MigrationServicesImpl(connection);
				isValid = migrationServices.execSql(sql);

				if(isValid) {
					isValid = migrationServices.atualizaVersao(versaoFinal);
				}
				
				ConnectionFactory.closeConnection(connection);
				
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
	}
}

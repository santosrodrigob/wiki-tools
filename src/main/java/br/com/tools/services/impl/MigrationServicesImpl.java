package br.com.tools.services.impl;

import java.sql.Connection;

import br.com.tools.dao.MigrationDao;
import br.com.tools.dao.impl.MigrationDaoImpl;
import br.com.tools.services.MigrationServices;

public class MigrationServicesImpl implements MigrationServices {
	
	private MigrationDao migrationDao;
	
	public MigrationServicesImpl(Connection connection) {
		super();
		this.migrationDao = new MigrationDaoImpl(connection);
	}

	@Override
	public boolean validaVersao(int versaoInicial, int versaoFinal) throws Exception {
		return this.migrationDao.validaVersao(versaoInicial, versaoFinal);
	}

	@Override
	public boolean execSql(String sql) throws Exception {
		return this.migrationDao.execSql(sql);
	}

	@Override
	public boolean atualizaVersao(int versaoFinal) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}

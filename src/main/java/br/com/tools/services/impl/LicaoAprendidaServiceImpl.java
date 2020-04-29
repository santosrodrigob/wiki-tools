package br.com.tools.services.impl;

import java.sql.Connection;

import br.com.tools.dao.LicaoAprendidaDao;
import br.com.tools.dao.impl.LicaoAprendidaDaoImpl;
import br.com.tools.model.LicaoAprendidaDTO;
import br.com.tools.model.vo.LicaoAprendida;
import br.com.tools.services.LicaoAprendidaService;

public class LicaoAprendidaServiceImpl implements LicaoAprendidaService {
	
	LicaoAprendidaDao licaoAprendidaDao;
	
	public LicaoAprendidaServiceImpl(Connection connection) {
		super();
		this.licaoAprendidaDao = new LicaoAprendidaDaoImpl(connection);
	}

	@Override
	public boolean gravar(final LicaoAprendidaDTO licaoAprendidaDTO, final Integer codigoUsuario) throws Exception {
		return this.licaoAprendidaDao.gravar(licaoAprendidaDTO, codigoUsuario);
	}

	@Override
	public void getAll(final LicaoAprendidaDTO licaoAprendidaDTO) throws Exception {
		this.licaoAprendidaDao.getAll(licaoAprendidaDTO);
	}

	@Override
	public LicaoAprendida getLicaoById(final Integer id) throws Exception {
		return this.licaoAprendidaDao.getLicaoById(id);
	}

	@Override
	public boolean remove(final Integer id, final Boolean inativo, final Integer codigoUsuario) throws Exception {
		return this.licaoAprendidaDao.remove(id, inativo, codigoUsuario);
	}
}

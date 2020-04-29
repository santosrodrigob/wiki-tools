package br.com.tools.services.impl;

import java.sql.Connection;
import java.util.List;

import br.com.tools.dao.SubReferenciaDao;
import br.com.tools.dao.impl.SubReferenciaDaoImpl;
import br.com.tools.model.SubReferenciaDTO;
import br.com.tools.model.vo.SubReferencia;
import br.com.tools.services.SubReferenciaServices;

public class SubReferenciaServicesImpl implements SubReferenciaServices {
	
	SubReferenciaDao subReferenciaDao;
	
	public SubReferenciaServicesImpl(Connection connection) {
		super();
		this.subReferenciaDao = new SubReferenciaDaoImpl(connection);
	}

	@Override
	public List<SubReferenciaDTO> getComboSubReferencias() throws Exception {
		return this.subReferenciaDao.getComboSubReferencias();
	}

	@Override
	public List<SubReferenciaDTO> getComboByReferencia(Integer codigoReferencia) throws Exception {
		return this.subReferenciaDao.getComboByReferencia(codigoReferencia);
	}

	@Override
	public void getAll(SubReferenciaDTO subReferenciaDTO) throws Exception {
		this.subReferenciaDao.getAll(subReferenciaDTO);
	}

	@Override
	public boolean gravar(SubReferenciaDTO subReferenciaDTO, Integer codigoUsuario) throws Exception {
		return this.subReferenciaDao.gravar(subReferenciaDTO, codigoUsuario);
	}

	@Override
	public SubReferencia getReferenciaById(Integer id) throws Exception {
		return this.subReferenciaDao.getReferenciaById(id);
	}

	@Override
	public boolean remove(Integer id, Boolean inativo, Integer codigoUsuario) throws Exception {
		return this.subReferenciaDao.remove(id, inativo, codigoUsuario);
	}
}

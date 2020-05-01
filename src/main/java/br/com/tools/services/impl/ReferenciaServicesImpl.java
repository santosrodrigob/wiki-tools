/**
 * @author Rodrigo Borges 
 * @date 01/04/2020
 */
package br.com.tools.services.impl;

import java.sql.Connection;
import java.util.List;

import br.com.tools.dao.ReferenciaDao;
import br.com.tools.dao.impl.ReferenciaDaoImpl;
import br.com.tools.model.ReferenciaDTO;
import br.com.tools.services.ReferenciaServices;

public class ReferenciaServicesImpl implements ReferenciaServices {
	
	ReferenciaDao referenciaDao;
	
	public ReferenciaServicesImpl(Connection connection) {
		super();
		referenciaDao = new ReferenciaDaoImpl(connection);
	}

	@Override
	public List<ReferenciaDTO> getComboReferencias() throws Exception {
		return this.referenciaDao.getComboReferencias();
	}

	@Override
	public void getAll(ReferenciaDTO referenciaDTO) throws Exception {
		this.referenciaDao.getAll(referenciaDTO);
	}

	@Override
	public boolean gravar(ReferenciaDTO referenciaDTO, Integer codigoUsuario) throws Exception {
		return this.referenciaDao.gravar(referenciaDTO, codigoUsuario);
	}

	@Override
	public ReferenciaDTO getReferenciaById(Integer id) throws Exception {
		return this.referenciaDao.getReferenciaById(id);
	}

	@Override
	public boolean remove(Integer id, Boolean inativo, Integer codigoUsuario) throws Exception {
		return this.referenciaDao.remove(id, inativo, codigoUsuario);
	}
}

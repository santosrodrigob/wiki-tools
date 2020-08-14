package br.com.tools.services.impl;

import java.sql.Connection;
import java.util.List;

import br.com.tools.dao.EquipamentoDao;
import br.com.tools.dao.impl.EquipamentoDaoImpl;
import br.com.tools.model.EquipamentoDTO;
import br.com.tools.services.EquipamentoServices;

public class EquipamentoServicesImpl implements EquipamentoServices {
	
	private EquipamentoDao equipamentoDao;
	
	public EquipamentoServicesImpl(Connection connection) {
		super();
		this.equipamentoDao = new EquipamentoDaoImpl(connection);
	}

	@Override
	public List<EquipamentoDTO> getAll() throws Exception {
		return this.equipamentoDao.getAll();
	}

	@Override
	public List<EquipamentoDTO> getComboEquipamentos() throws Exception {
		return this.equipamentoDao.getComboEquipamentos();
	}

}

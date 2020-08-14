package br.com.tools.dao;

import java.util.List;

import br.com.tools.model.EquipamentoDTO;

public interface EquipamentoDao {

	List<EquipamentoDTO> getAll() throws Exception;
	List<EquipamentoDTO> getComboEquipamentos() throws Exception;
}

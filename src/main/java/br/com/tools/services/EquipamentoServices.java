package br.com.tools.services;

import java.util.List;

import br.com.tools.model.ClienteDTO;
import br.com.tools.model.EquipamentoDTO;

public interface EquipamentoServices {

	List<EquipamentoDTO> getAll() throws Exception;
	List<EquipamentoDTO> getComboEquipamentos() throws Exception;
}

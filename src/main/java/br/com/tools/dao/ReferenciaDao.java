package br.com.tools.dao;

import java.util.List;

import br.com.tools.model.ReferenciaDTO;

public interface ReferenciaDao {

	List<ReferenciaDTO> getComboReferencias() throws Exception;
	void getAll(final ReferenciaDTO referenciaDTO) throws Exception;
	boolean gravar(final ReferenciaDTO referenciaDTO, final Integer codigoUsuario) throws Exception;
	ReferenciaDTO getReferenciaById(final Integer id) throws Exception;
	boolean remove(final Integer id, final Boolean inativo, final Integer codigoUsuario) throws Exception;
}

package br.com.tools.services;

import java.util.List;

import br.com.tools.model.SubReferenciaDTO;
import br.com.tools.model.vo.SubReferencia;

public interface SubReferenciaServices {

	List<SubReferenciaDTO> getComboSubReferencias() throws Exception;
	List<SubReferenciaDTO> getComboByReferencia(final Integer codigoReferencia) throws Exception;
	void getAll(final SubReferenciaDTO subReferenciaDTO) throws Exception;
	boolean gravar(final SubReferenciaDTO subReferenciaDTO, final Integer codigoUsuario) throws Exception;
	SubReferencia getReferenciaById(final Integer id) throws Exception;
	boolean remove(final Integer id, final Boolean inativo, final Integer codigoUsuario) throws Exception;

}

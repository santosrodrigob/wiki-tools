/**
 * @author Rodrigo Borges 
 * @date 01/04/2020
 */
package br.com.tools.services;

import br.com.tools.model.LicaoAprendidaDTO;
import br.com.tools.model.vo.LicaoAprendida;

public interface LicaoAprendidaService {

	boolean gravar(final LicaoAprendidaDTO licaoAprendidaDTO, final Integer codigoUsuario) throws Exception;
	void getAll(final LicaoAprendidaDTO licaoAprendidaDTO) throws Exception;
	LicaoAprendida getLicaoById(final Integer id) throws Exception;
	boolean remove(final Integer id, final Boolean inativo, Integer codigoUsuario) throws Exception;
}

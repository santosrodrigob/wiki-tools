package br.com.tools.services;

import java.util.List;

import br.com.tools.model.UsuarioDTO;
import br.com.tools.model.vo.Usuario;

public interface UsuarioServices {

	List<UsuarioDTO> getComboUsuarios() throws Exception;
	boolean login(final UsuarioDTO usuarioDTO) throws Exception;
	UsuarioDTO getObjetoUsuarioSessao(final UsuarioDTO usuario) throws Exception;
	void getAll(UsuarioDTO usuarioDTO) throws Exception;
	boolean gravar(final UsuarioDTO usuarioDTO, final Integer codigoUsuario) throws Exception;
	Usuario getUsuarioById(final Integer id) throws Exception;
	boolean remove(final Integer id, final Boolean ativo) throws Exception;
	Integer getProximoCodigo(Integer codigoAtual) throws Exception;
}

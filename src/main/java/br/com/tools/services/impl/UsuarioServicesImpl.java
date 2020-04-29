package br.com.tools.services.impl;

import java.sql.Connection;
import java.util.List;

import br.com.tools.dao.UsuarioDao;
import br.com.tools.dao.impl.UsuarioDaoImpl;
import br.com.tools.model.UsuarioDTO;
import br.com.tools.model.vo.Usuario;
import br.com.tools.services.UsuarioServices;

public class UsuarioServicesImpl implements UsuarioServices {
	
	UsuarioDao usuarioDao;
	
	public UsuarioServicesImpl(Connection connection) {
		super();
		usuarioDao = new UsuarioDaoImpl(connection);
	}

	@Override
	public List<UsuarioDTO> getComboUsuarios() throws Exception {
		return this.usuarioDao.getComboUsuarios();
	}

	@Override
	public boolean login(final UsuarioDTO usuarioDTO) throws Exception {
		return this.usuarioDao.login(usuarioDTO);
	}

	@Override
	public UsuarioDTO getObjetoUsuarioSessao(final UsuarioDTO usuario) throws Exception {
		return this.usuarioDao.getObjetoUsuarioSessao(usuario);
	}

	@Override
	public void getAll(UsuarioDTO usuarioDTO) throws Exception {
		this.usuarioDao.getAll(usuarioDTO);
	}

	@Override
	public boolean gravar(UsuarioDTO usuarioDTO, Integer codigoUsuario) throws Exception {
		return this.usuarioDao.gravar(usuarioDTO, codigoUsuario);
	}

	@Override
	public Usuario getUsuarioById(Integer id) throws Exception {
		return this.usuarioDao.getUsuarioById(id);
	}

	@Override
	public boolean remove(Integer id, Boolean ativo) throws Exception {
		return this.usuarioDao.remove(id, ativo);
	}

	@Override
	public Integer getProximoCodigo(Integer codigoAtual) throws Exception {
		return this.usuarioDao.getProximoCodigo(codigoAtual);
	}
}

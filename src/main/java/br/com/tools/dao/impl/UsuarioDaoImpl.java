/**
 * @author Rodrigo Borges 
 * @date 01/04/2020
 */
package br.com.tools.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.tools.dao.UsuarioDao;
import br.com.tools.model.UsuarioDTO;
import br.com.tools.model.vo.Usuario;

public class UsuarioDaoImpl implements UsuarioDao {
	
	Connection connection;
	
	public UsuarioDaoImpl(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public List<UsuarioDTO> getComboUsuarios() throws Exception {

		List<UsuarioDTO> usuarioDTOs = new ArrayList<UsuarioDTO>();
		
		StringBuilder query = new StringBuilder();
		query.append("select ");
		query.append("	codigo_usuario, ");
		query.append("	concat(usuario, '(', codigo_usuario, ')') as usuario ");
		query.append("from ");
		query.append("	tb_usuario ");
		query.append("where ");
		query.append("	ativo = 1");
		
		PreparedStatement statement = null;
						
		try 
		{
			statement = this.connection.prepareStatement(query.toString());
			statement.executeQuery();
			ResultSet rs = statement.getResultSet();
			while (rs.next()) {
				UsuarioDTO usuarioDTO = new UsuarioDTO();
				usuarioDTO.setCodigoUsuario(rs.getInt("codigo_usuario"));
				usuarioDTO.setUsuario(rs.getString("usuario"));
				usuarioDTOs.add(usuarioDTO);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new RuntimeException();
		}
		finally 
		{
			if(statement!=null) {
				statement.close();
			}
		}
		return usuarioDTOs;
	}

	@Override
	public boolean login(final UsuarioDTO usuarioDTO) throws Exception {
		
		boolean isValid = false;
		
		StringBuilder query = new StringBuilder();
		query.append("select ");
		query.append("	codigo_usuario, ");
		query.append("	senha, ");
		query.append("	concat(usuario, '(', codigo_usuario, ')') as usuario, ");
		query.append("	ativo ");
		query.append("from ");
		query.append("	tb_usuario ");
		query.append("where ");
		query.append("	ativo = 1");
		query.append("	and codigo_usuario = ? ");
		query.append("	and senha = ? ");
		
		PreparedStatement statement = null;
		
		try
		{
			statement = this.connection.prepareStatement(query.toString());
			statement.setInt(1, usuarioDTO.getCodigoUsuario());
			statement.setString(2, usuarioDTO.getSenha());
			statement.executeQuery();
			
			ResultSet rs = statement.getResultSet();
			
			while (rs.next()) {
				
				usuarioDTO.setCodigoUsuario(rs.getInt("codigo_usuario"));
				usuarioDTO.setSenha(rs.getString("senha"));
				usuarioDTO.setUsuario(rs.getString("usuario"));
				usuarioDTO.add(usuarioDTO);
				isValid = usuarioDTO.validaUsuario(usuarioDTO);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		finally 
		{
			if(statement!=null) {
				statement.close();
			}
			if(connection!=null) {
				connection.close();
			}
		}
		return isValid;
	}

	@Override
	public UsuarioDTO getObjetoUsuarioSessao(UsuarioDTO usuario) throws Exception {
		
		StringBuilder query = new StringBuilder();
		query.append("select ");
		query.append("	id_usuario, ");
		query.append("	codigo_usuario, ");
		query.append("	codigo_acesso_wiki, ");
		query.append("	usuario ");
		query.append("from ");
		query.append("	tb_usuario ");
		query.append("where ");
		query.append("	ativo = 1");
		query.append("	and codigo_usuario = ? ");
		
		PreparedStatement statement = null;
		
		try
		{
			statement = this.connection.prepareStatement(query.toString());
			statement.setInt(1, usuario.getCodigoUsuario());
			statement.executeQuery();
			
			ResultSet rs = statement.getResultSet();
			
			while (rs.next()) {
				
				usuario.setCodigoUsuario(rs.getInt("codigo_usuario"));
				usuario.setCodigoAcessoWiki(rs.getInt("codigo_acesso_wiki"));
				usuario.setId(rs.getInt("id_usuario"));
				usuario.setUsuario(rs.getString("usuario"));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		finally 
		{
			if(statement!=null) {
				statement.close();
			}
			if(connection!=null) {
				connection.close();
			}
		}		
		return usuario;
	}

	@Override
	public void getAll(UsuarioDTO usuarioDTO) throws Exception {
		
		StringBuilder query = new StringBuilder();
		query.append("select ");
		query.append("	id_usuario, ");
		query.append("	codigo_usuario, ");
		query.append("	usuario, ");
		query.append("	ifnull(data_alteracao, data_criacao) as data_alteracao, ");
		query.append("	ativo ");
		query.append("from ");
		query.append("	tb_usuario ");
//		query.append("where ");
//		query.append("	ativo = 1");
		
		PreparedStatement statement = null;
						
		try 
		{
			statement = this.connection.prepareStatement(query.toString());
			statement.executeQuery();
			ResultSet rs = statement.getResultSet();
			while (rs.next()) {
				Usuario  usuario = new Usuario();
				usuario.setId(rs.getInt("id_usuario"));
				usuario.setCodigoUsuario(rs.getInt("codigo_usuario"));
				usuario.setUsuario(rs.getString("usuario"));
				usuario.setAtivo(rs.getInt("ativo"));
				LocalDate ldAlteracao = LocalDate.parse(rs.getString("data_alteracao"));
				usuario.setDataAlteracao(ldAlteracao);
				usuarioDTO.add(usuario);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new RuntimeException();
		}
		finally 
		{
			if(statement!=null) {
				statement.close();
			}
		}
	}

	@Override
	public boolean gravar(UsuarioDTO usuarioDTO, Integer codigoUsuario) throws Exception {
		
		boolean isUpdated = false;
		
		StringBuilder query = new StringBuilder();

		query.append("update ");
		query.append("	tb_usuario ");
		query.append("set ");
		query.append("	codigo_usuario = ?, "); 	//1
		query.append("	senha = ?, "); 				//2
		query.append("	usuario = ?, "); 			//3
		query.append("	codigo_acesso_wiki = ?, ");	//4
		query.append("	ativo = ?, ");				//5
		query.append("	data_alteracao = convert(now(), date) ");
		query.append("where ");
		query.append("	id_usuario = ? ");			//6
		
		PreparedStatement statement = null;
		
		try 
		{
			statement = this.connection.prepareStatement(query.toString());
			statement.setInt(1, usuarioDTO.getCodigoUsuario());
			statement.setString(2, usuarioDTO.getSenha());
			statement.setString(3, usuarioDTO.getUsuario());
			statement.setInt(4, usuarioDTO.getCodigoAcessoWiki());
			statement.setInt(5, usuarioDTO.getAtivo());
			statement.setInt(6, usuarioDTO.getId());
			statement.executeUpdate();
			isUpdated = statement.getUpdateCount() > 0;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		query = new StringBuilder();
		query.append("insert into ");
		query.append("	tb_usuario");
		query.append("(");
		query.append("	codigo_usuario, ");		//1
		query.append("	senha, ");				//2
		query.append("	usuario, ");			//3
		query.append("	codigo_acesso_wiki,");	//4
		query.append("	ativo, ");
		query.append("	data_criacao");
		query.append(")");
		query.append("	select ?, ?, ?, ?, 1, convert(now(), date) ");
		
		try 
		{
			if(!isUpdated) 
			{
				statement = this.connection.prepareStatement(query.toString());
				statement.setInt(1, usuarioDTO.getCodigoUsuario());
				statement.setString(2, usuarioDTO.getSenha());
				statement.setString(3, usuarioDTO.getUsuario());
				statement.setInt(4, usuarioDTO.getCodigoAcessoWiki());
				statement.executeUpdate();
				isUpdated = statement.getUpdateCount() > 0;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new RuntimeException();
		}
		finally 
		{
			if(statement!=null) {
				statement.close();
			}
			if(connection!=null) {
				connection.close();
			}
		}
		return isUpdated;
	}

	@Override
	public Usuario getUsuarioById(Integer id) throws Exception {
		
		Usuario usuario = new Usuario();
		
		StringBuilder query = new StringBuilder();
		query.append("select ");
		query.append("	u.id_usuario, ");
		query.append("	u.codigo_usuario, ");
		query.append("	u.senha, ");
		query.append("	u.usuario, ");
		query.append("	u.codigo_acesso_wiki, ");
		query.append("	r.acesso, ");
		query.append("	u.data_criacao, ");
		query.append("	u.data_alteracao, ");
		query.append("	u.ativo ");
		query.append("from ");
		query.append("	tb_usuario u ");
		query.append("	inner join tb_role r on u.codigo_acesso_wiki=r.codigo_acesso ");
		query.append("	where u.id_usuario = ? ");
		
		PreparedStatement statement = null;
						
		try 
		{
			statement = this.connection.prepareStatement(query.toString());
			statement.setInt(1, id);
			statement.executeQuery();
			ResultSet rs = statement.getResultSet();
			
			while (rs.next()) {

				usuario.setId(rs.getInt("id_usuario"));
				usuario.setCodigoUsuario(rs.getInt("codigo_usuario"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setUsuario(rs.getString("usuario"));
				usuario.setCodigoAcesso(rs.getInt("codigo_acesso_wiki"));
				usuario.setAcesso(rs.getString("acesso"));
				usuario.setAtivo(rs.getInt("ativo"));
				LocalDate lCriacao = LocalDate.parse(rs.getString("data_criacao"));
				usuario.setDataCriacao(lCriacao);
				if(null != rs.getString("data_alteracao")) {
					LocalDate lAlteracao = LocalDate.parse(rs.getString("data_alteracao"));
					usuario.setDataAlteracao(lAlteracao);
				}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new RuntimeException();
		}
		finally 
		{
			if(statement!=null) {
				statement.close();
			}
			if(connection!=null) {
				connection.close();
			}
		}
		return usuario;
	}

	@Override
	public boolean remove(Integer id, Boolean ativo) throws Exception {
		
		boolean isUpdated = false;
		
		StringBuilder query = new StringBuilder();

		query.append("update ");
		query.append("	tb_usuario ");
		query.append("set ");
		if(ativo) {
			query.append("	ativo = 0, ");
		} else {
			query.append("	ativo = 1, ");
		}
		query.append("	data_alteracao = convert(now(), date) ");
		query.append("where ");
		query.append("	id_usuario = ? ");
		
		PreparedStatement statement = null;
		
		try
		{
			statement = this.connection.prepareStatement(query.toString());
			statement.setInt(1, id);
			statement.executeUpdate();
			isUpdated = statement.getUpdateCount() > 0;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new RuntimeException();
		}
		finally 
		{
			if(statement!=null) {
				statement.close();
			}
			if(connection!=null) {
				connection.close();
			}
		}
		return isUpdated;
	}

	@Override
	public Integer getProximoCodigo(Integer codigoAtual) throws Exception {
		
		Integer proximo = 0;
		
		StringBuilder query = new StringBuilder();
		query.append("select ");
		query.append("case ");
		query.append("	when count(codigo_usuario) = 0 then ? - 1 ");
		query.append("	when 1 = 1 then (select codigo_usuario from tb_usuario order by codigo_usuario desc limit 1) ");
		query.append("	else codigo_usuario ");
		query.append("end as codigo_usuario ");
		query.append("from ");
		query.append("	tb_usuario ");
		query.append("where ");
		query.append("	codigo_usuario > (case when ? = 0 then 1 else ? end) ");
		query.append("order by ");
		query.append("	codigo_usuario desc limit 1 ");

		PreparedStatement statement = null;
						
		try 
		{
			statement = this.connection.prepareStatement(query.toString());
			statement.setInt(1, codigoAtual);
			statement.setInt(2, codigoAtual);
			statement.setInt(3, codigoAtual);
			statement.executeQuery();
			ResultSet rs = statement.getResultSet();
			
			while (rs.next()) {

				proximo = rs.getInt("codigo_usuario");
				proximo++;
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new RuntimeException();
		}
		finally 
		{
			if(statement!=null) {
				statement.close();
			}
			if(connection!=null) {
				connection.close();
			}
		}
		return proximo;
	}
}

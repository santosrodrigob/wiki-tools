package br.com.tools.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import br.com.tools.dao.LicaoAprendidaDao;
import br.com.tools.model.LicaoAprendidaDTO;
import br.com.tools.model.vo.LicaoAprendida;

public class LicaoAprendidaDaoImpl implements LicaoAprendidaDao {

	Connection connection;
	
	public LicaoAprendidaDaoImpl(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public void getAll(LicaoAprendidaDTO licaoAprendidaDTO) throws Exception {
		
		StringBuilder query = new StringBuilder();
		query.append("select ");
		query.append("	l.id, ");
		query.append("	l.cliente, ");
		query.append("	l.erro, ");
		query.append("	l.solucao, ");
		query.append("	r.referencia, ");
		query.append("	s.sub_referencia, ");
		query.append("	ifnull(t.usuario, ' - ') as tecnico, ");
		query.append("	ifnull(l.data_alteracao, l.data_criacao) as data, ");
		query.append("	ifnull(a.usuario, c.usuario) as usuario_alteracao, ");
		query.append("	ifnull(l.inativo, 0) as inativo ");
		query.append("from ");
		query.append("	tb_licao_aprendida l ");
		query.append("	inner join tb_referencia r on l.codigo_referencia=r.codigo_referencia ");
		query.append("	inner join tb_sub_referencia s on l.codigo_referencia=s.codigo_referencia and l.codigo_sub_referencia=s.codigo_sub_referencia ");
		query.append("	left join tb_usuario t on l.codigo_tecnico=t.codigo_usuario ");
		query.append("	left join tb_usuario c on l.codigo_usuario_criacao=c.codigo_usuario ");
		query.append("	left join tb_usuario a on l.codigo_usuario_alteracao=a.codigo_usuario ");
		query.append("where 1=1 ");
		if(licaoAprendidaDTO.getInativo() < 2) {
			query.append("	and ifnull(l.inativo, 0) = ? ");
		} else {
			query.append("	and ifnull(l.inativo, 0) < ? ");
		}
		if(licaoAprendidaDTO.getCodigoUsuario() > 0) {
			query.append("	and l.codigo_tecnico = ? ");
		} else if(licaoAprendidaDTO.getCodigoReferencia() > 0 && licaoAprendidaDTO.getCodigoSubReferencia() > 0) {
			query.append(" 	and l.codigo_referencia = ?  ");
			query.append("	and l.codigo_sub_referencia = ? ");
		} else if(licaoAprendidaDTO.getCodigoReferencia() > 0) {
			query.append(" 	and l.codigo_referencia = ?  ");
		} else if(licaoAprendidaDTO.getCodigoSubReferencia() > 0) {
			query.append("	and l.codigo_sub_referencia = ? ");
		}
		
		PreparedStatement statement = null;
						
		try 
		{
			statement = this.connection.prepareStatement(query.toString());
			statement.setInt(1, licaoAprendidaDTO.getInativo());
			if(licaoAprendidaDTO.getCodigoUsuario() > 0) {
				statement.setInt(2, licaoAprendidaDTO.getCodigoUsuario());
			} else if(licaoAprendidaDTO.getCodigoReferencia() > 0 && licaoAprendidaDTO.getCodigoSubReferencia() > 0) {
				statement.setInt(2, licaoAprendidaDTO.getCodigoReferencia());
				statement.setInt(3, licaoAprendidaDTO.getCodigoSubReferencia());
			} else if(licaoAprendidaDTO.getCodigoReferencia() > 0) {
				statement.setInt(2, licaoAprendidaDTO.getCodigoReferencia());
			} else if(licaoAprendidaDTO.getCodigoSubReferencia() > 0) {
				statement.setInt(2, licaoAprendidaDTO.getCodigoSubReferencia());
			}
			statement.executeQuery();
			ResultSet rs = statement.getResultSet();
			
			while (rs.next()) {
				
				LicaoAprendida licao = new LicaoAprendida();
				
				licao.setId(rs.getInt("id"));
				licao.setCliente(rs.getString("cliente"));
				licao.setErro(rs.getString("erro"));
				licao.setSolucao(rs.getString("solucao"));
				licao.setDescricaoReferencia(rs.getString("referencia"));
				licao.setDescricaoSubReferencia(rs.getString("sub_referencia"));
				licao.setUsuario(rs.getString("tecnico"));
				LocalDate lAlteracao = LocalDate.parse(rs.getString("data"));
				licao.setDataAlteracao(lAlteracao);
				licao.setUsuarioAlteracao(rs.getString("usuario_alteracao"));
				licao.setInativo(rs.getInt("inativo"));
				licaoAprendidaDTO.add(licao);
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
	public boolean gravar(LicaoAprendidaDTO licaoAprendidaDTO, final Integer codigoUsuario) throws Exception {
		
		boolean isUpdated = false;
		
		StringBuilder query = new StringBuilder();

		query.append("update ");
		query.append("	tb_licao_aprendida ");
		query.append("set ");
		query.append("	cliente = ?, "); 					//1
		query.append("	erro = ?, ");						//2
		query.append("	solucao = ?, ");					//3	
		query.append("	codigo_referencia = ?, ");			//4
		query.append("	codigo_sub_referencia = ?, ");		//5
		query.append("	codigo_tecnico = ?, ");				//6
		query.append("	codigo_usuario_alteracao = ?, ");	//7
		query.append("	inativo = ?, ");					//8
		query.append("	data_alteracao = now() ");			
		query.append("where ");
		query.append("	id = ? ");							//9
		
		PreparedStatement statement = null;
		
		try 
		{
			statement = this.connection.prepareStatement(query.toString());
			statement.setString(1, licaoAprendidaDTO.getCliente());
			statement.setString(2, licaoAprendidaDTO.getErro());
			statement.setString(3, licaoAprendidaDTO.getSolucao());
			statement.setInt(4, licaoAprendidaDTO.getCodigoReferencia());
			statement.setInt(5, licaoAprendidaDTO.getCodigoSubReferencia());
			statement.setInt(6, licaoAprendidaDTO.getCodigoUsuario());
			statement.setInt(7, codigoUsuario);
			statement.setInt(8, licaoAprendidaDTO.getInativo());
			statement.setInt(9, licaoAprendidaDTO.getId());
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
		query.append("	tb_licao_aprendida");
		query.append("(");
		query.append("	cliente, ");				//1
		query.append("	erro, ");					//2
		query.append("	solucao, ");				//3
		query.append("	codigo_referencia, ");		//4
		query.append("	codigo_sub_referencia, ");	//5
		query.append("	codigo_tecnico, ");			//6
		query.append("	codigo_usuario_criacao,");	//7
		query.append("	data_criacao");
		query.append(")");
		query.append("	select ?, ?, ?, ?, ?, ?, ?, now() ");
		
		try 
		{
			if(!isUpdated) 
			{
				statement = this.connection.prepareStatement(query.toString());
				statement.setString(1, licaoAprendidaDTO.getCliente());
				statement.setString(2, licaoAprendidaDTO.getErro());
				statement.setString(3, licaoAprendidaDTO.getSolucao());
				statement.setInt(4, licaoAprendidaDTO.getCodigoReferencia());
				statement.setInt(5, licaoAprendidaDTO.getCodigoSubReferencia());
				statement.setInt(6, licaoAprendidaDTO.getCodigoUsuario());
				statement.setInt(7, codigoUsuario);
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
	public LicaoAprendida getLicaoById(Integer id) throws Exception {
		
		LicaoAprendida licaoAprendida = new LicaoAprendida();
		
		StringBuilder query = new StringBuilder();
		query.append("select ");
		query.append("	l.id, ");
		query.append("	l.cliente, ");
		query.append("	l.erro, ");
		query.append("	l.solucao, ");
		query.append("	l.codigo_referencia, ");
		query.append("	concat(r.referencia, '(', l.codigo_referencia, ')') as referencia, ");
		query.append("	l.codigo_sub_referencia, ");
		query.append("	concat(s.sub_referencia, '(', l.codigo_sub_referencia, ')') as sub_referencia, ");
		query.append("	l.codigo_tecnico, ");
		query.append("	concat(ifnull(t.usuario, ' - '), '(', l.codigo_tecnico, ')') as tecnico, ");
		query.append("	l.data_criacao, ");
		query.append("	l.data_alteracao, ");
		query.append("	l.codigo_usuario_criacao, ");
		query.append("	ifnull(concat(c.usuario, '(' , l.codigo_usuario_criacao, ')'), ' - ') as usuario_criacao, ");
		query.append("	l.codigo_usuario_alteracao, ");
		query.append("	ifnull(concat(a.usuario, '(' , l.codigo_usuario_alteracao, ')'), ' - ') as usuario_alteracao, ");
		query.append("	ifnull(l.inativo, 0) as inativo ");
		query.append("from ");
		query.append("	tb_licao_aprendida l ");
		query.append("	inner join tb_referencia r on l.codigo_referencia=r.codigo_referencia ");
		query.append("	inner join tb_sub_referencia s on l.codigo_referencia=s.codigo_referencia and l.codigo_sub_referencia=s.codigo_sub_referencia ");
		query.append("	left join tb_usuario t on l.codigo_tecnico=t.codigo_usuario ");
		query.append("	left join tb_usuario c on l.codigo_usuario_criacao=c.codigo_usuario ");
		query.append("	left join tb_usuario a on l.codigo_usuario_alteracao=a.codigo_usuario ");
		query.append("where l.id = ? ");
		
		PreparedStatement statement = null;
						
		try 
		{
			statement = this.connection.prepareStatement(query.toString());
			statement.setInt(1, id);
			statement.executeQuery();
			ResultSet rs = statement.getResultSet();
			
			while (rs.next()) {
				
				licaoAprendida.setId(rs.getInt("id"));
				licaoAprendida.setCliente(rs.getString("cliente"));
				licaoAprendida.setErro(rs.getString("erro"));
				licaoAprendida.setSolucao(rs.getString("solucao"));
				licaoAprendida.setCodigoReferencia(rs.getInt("codigo_referencia"));
				licaoAprendida.setDescricaoReferencia(rs.getString("referencia"));
				licaoAprendida.setCodigoSubReferencia(rs.getInt("codigo_sub_referencia"));
				licaoAprendida.setDescricaoSubReferencia(rs.getString("sub_referencia"));
				licaoAprendida.setCodigoUsuario(rs.getInt("codigo_tecnico"));
				licaoAprendida.setUsuario(rs.getString("tecnico"));
				LocalDate lCriacao = LocalDate.parse(rs.getString("data_criacao"));
				licaoAprendida.setDataCriacao(lCriacao);
				if(null != rs.getString("data_alteracao")) {
					LocalDate lAlteracao = LocalDate.parse(rs.getString("data_alteracao"));
					licaoAprendida.setDataAlteracao(lAlteracao);
				}
				licaoAprendida.setCodigoUsuarioCriacao(rs.getInt("codigo_usuario_criacao"));
				licaoAprendida.setUsuarioCriacao(rs.getString("usuario_criacao"));
				licaoAprendida.setCodigoUsuarioAlteracao(rs.getInt("codigo_usuario_criacao"));
				licaoAprendida.setUsuarioAlteracao(rs.getString("usuario_alteracao"));
				licaoAprendida.setInativo(rs.getInt("inativo"));
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
		return licaoAprendida;
	}

	@Override
	public boolean remove(final Integer id, final Boolean inativo, final Integer codigoUsuario) throws Exception {

		boolean isUpdated = false;
		
		StringBuilder query = new StringBuilder();

		query.append("update ");
		query.append("	tb_licao_aprendida ");
		query.append("set ");
		if(inativo) {
			query.append("	inativo = 0, ");
		} else {
			query.append("	inativo = 1, ");
		}
		query.append("	codigo_usuario_alteracao = ?, ");
		query.append("	data_alteracao = convert(now(), date) ");
		query.append("where ");
		query.append("	id = ? ");
		
		PreparedStatement statement = null;
		
		try
		{
			statement = this.connection.prepareStatement(query.toString());
			statement.setInt(1, codigoUsuario);
			statement.setInt(2, id);
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
}

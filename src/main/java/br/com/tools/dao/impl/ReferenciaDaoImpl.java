package br.com.tools.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.tools.dao.ReferenciaDao;
import br.com.tools.model.ReferenciaDTO;
import br.com.tools.model.vo.Referencia;

public class ReferenciaDaoImpl implements ReferenciaDao {

	Connection connection;
	
	public ReferenciaDaoImpl(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public List<ReferenciaDTO> getComboReferencias() throws Exception {
		
		List<ReferenciaDTO> referenciaDTOs = new ArrayList<ReferenciaDTO>();
		
		StringBuilder query = new StringBuilder();
		query.append("select ");
		query.append("	codigo_referencia, ");
		query.append("	concat(referencia, '(', codigo_referencia, ')') as referencia ");
		query.append("from ");
		query.append("	tb_referencia ");
		query.append("where ");
		query.append("	ifnull(inativo, 0) = 0");
		
		PreparedStatement statement = null;
						
		try 
		{
			statement = this.connection.prepareStatement(query.toString());
			statement.executeQuery();
			ResultSet rs = statement.getResultSet();
			
			while (rs.next()) {
				ReferenciaDTO referenciaDTO = new ReferenciaDTO();
				referenciaDTO.setCodigoReferencia(rs.getInt("codigo_referencia"));
				referenciaDTO.setDescricaoReferencia(rs.getString("referencia"));
				referenciaDTOs.add(referenciaDTO);
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
		return referenciaDTOs;
	}

	@Override
	public void getAll(ReferenciaDTO referenciaDTO) throws Exception {
		
		StringBuilder query = new StringBuilder();
		query.append("select ");
		query.append("	codigo_referencia, ");
		query.append("	referencia, ");
		query.append("	ifnull(data_alteracao, data_criacao) as data_alteracao, ");
		query.append("	inativo ");
		query.append("from ");
		query.append("	tb_referencia ");
		query.append("where 1 = 1 ");
		if(referenciaDTO.getInativo() < 2) {
			query.append("	and ifnull(inativo, 0) = ? ");
		} else {
			query.append("	and ifnull(inativo, 0) < ? ");
		}
		if(null != referenciaDTO.getDescricaoReferencia() && "" != referenciaDTO.getDescricaoReferencia()) {
			query.append("	and referencia like ? ");
		}

		PreparedStatement statement = null;
						
		try 
		{
			statement = this.connection.prepareStatement(query.toString());
			statement.setInt(1, referenciaDTO.getInativo());
			if(null != referenciaDTO.getDescricaoReferencia() && "" != referenciaDTO.getDescricaoReferencia()) {
				statement.setString(2, '%' +referenciaDTO.getDescricaoReferencia()+ '%');
			}
			statement.executeQuery();
			ResultSet rs = statement.getResultSet();
			
			while (rs.next()) {
				
				Referencia refer = new Referencia();
				refer.setCodigoReferencia(rs.getInt("codigo_referencia"));
				refer.setDescricaoReferencia(rs.getString("referencia"));
				LocalDate ldAlteracao = LocalDate.parse(rs.getString("data_alteracao"));
				refer.setDataAlteracao(ldAlteracao);
				refer.setInativo(rs.getInt("inativo"));
				referenciaDTO.add(refer);
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
	public boolean gravar(ReferenciaDTO referenciaDTO, Integer codigoUsuario) throws Exception {
		
		boolean isUpdated = false;
		
		StringBuilder query = new StringBuilder();

		query.append("update ");
		query.append("	tb_referencia ");
		query.append("set ");
		query.append("	referencia = ?, "); 				//1
		query.append("	codigo_usuario_alteracao = ?, ");	//2
		query.append("	inativo = ?, ");					//3
		query.append("	data_alteracao = now() ");			
		query.append("where ");
		query.append("	codigo_referencia = ? ");			//4
		
		PreparedStatement statement = null;
		
		try 
		{
			statement = this.connection.prepareStatement(query.toString());
			statement.setString(1, referenciaDTO.getDescricaoReferencia());
			statement.setInt(2, codigoUsuario);
			statement.setInt(3, referenciaDTO.getInativo());
			statement.setInt(4, referenciaDTO.getCodigoReferencia());
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
		query.append("	tb_referencia");
		query.append("(");
		query.append("	referencia, ");				//1
		query.append("	codigo_usuario_criacao,");	//2
		query.append("	data_criacao");
		query.append(")");
		query.append("	select ?, ?, now() ");
		
		try 
		{
			if(!isUpdated) 
			{
				statement = this.connection.prepareStatement(query.toString());
				statement.setString(1, referenciaDTO.getDescricaoReferencia());
				statement.setInt(2, codigoUsuario);
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
	public ReferenciaDTO getReferenciaById(Integer id) throws Exception {

		ReferenciaDTO referenciaDTO = new ReferenciaDTO();
		
		StringBuilder query = new StringBuilder();
		query.append("select ");
		query.append("	r.codigo_referencia, ");
		query.append("	r.referencia, ");
		query.append("	ifnull(r.inativo, 0) as inativo, ");
		query.append("	ifnull(concat(c.usuario, '(', r.codigo_usuario_criacao, ')'), ' - ') as usuario_criacao, ");
		query.append("	ifnull(concat(a.usuario, '(', r.codigo_usuario_alteracao, ')'), ' - ') as usuario_alteracao, ");
		query.append("	r.data_criacao, ");
		query.append("	r.data_alteracao ");
		query.append("from ");
		query.append("	tb_referencia r");
		query.append("	left join tb_usuario c on r.codigo_usuario_criacao=c.codigo_usuario ");
		query.append("	left join tb_usuario a on r.codigo_usuario_alteracao=a.codigo_usuario ");
		query.append("where r.codigo_referencia = ? ");
		
		PreparedStatement statement = null;
						
		try 
		{
			statement = this.connection.prepareStatement(query.toString());
			statement.setInt(1, id);
			statement.executeQuery();
			ResultSet rs = statement.getResultSet();
			
			while (rs.next()) {
				
				referenciaDTO.setCodigoReferencia(rs.getInt("codigo_referencia"));
				referenciaDTO.setDescricaoReferencia(rs.getString("referencia"));
				referenciaDTO.setInativo(rs.getInt("inativo"));
				referenciaDTO.setUsuarioCriacao(rs.getString("usuario_criacao"));
				referenciaDTO.setUsuarioAlteracao(rs.getString("usuario_alteracao"));
				LocalDate lCriacao = LocalDate.parse(rs.getString("data_criacao"));
				referenciaDTO.setDataCriacao(lCriacao);
				if(null != rs.getString("data_alteracao")) {
					LocalDate lAlteracao = LocalDate.parse(rs.getString("data_alteracao"));
					referenciaDTO.setDataAlteracao(lAlteracao);
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
		return referenciaDTO;
	}

	@Override
	public boolean remove(final Integer id, final Boolean inativo, final Integer codigoUsuario) throws Exception {

		boolean isUpdated = false;
		
		StringBuilder query = new StringBuilder();

		query.append("update ");
		query.append("	tb_referencia ");
		query.append("set ");
		if(inativo) {
			query.append("	inativo = 0, ");
		} else {
			query.append("	inativo = 1, ");
		}
		query.append("	codigo_usuario_alteracao = ?, ");
		query.append("	data_alteracao = convert(now(), date) ");
		query.append("where ");
		query.append("	codigo_referencia = ? ");
		
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

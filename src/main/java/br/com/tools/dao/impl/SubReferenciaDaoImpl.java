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

import br.com.tools.dao.SubReferenciaDao;
import br.com.tools.model.SubReferenciaDTO;
import br.com.tools.model.vo.SubReferencia;

public class SubReferenciaDaoImpl implements SubReferenciaDao {

	Connection connection;
	
	public SubReferenciaDaoImpl(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public List<SubReferenciaDTO> getComboSubReferencias() throws Exception {
		
		List<SubReferenciaDTO> subReferenciasDTOs = new ArrayList<SubReferenciaDTO>();
		
		StringBuilder query = new StringBuilder();
		query.append("select ");
		query.append("	codigo_sub_referencia, ");
		query.append("	concat(sub_referencia, '(', codigo_sub_referencia, ')') as sub_referencia ");
		query.append("from ");
		query.append("	tb_sub_referencia ");
		query.append("where ");
		query.append("	ifnull(inativo, 0) = 0");
		
		PreparedStatement statement = null;
						
		try 
		{
			statement = this.connection.prepareStatement(query.toString());
			statement.executeQuery();
			ResultSet rs = statement.getResultSet();
			
			while (rs.next()) {
				SubReferenciaDTO subReferenciaDTO = new SubReferenciaDTO();
				subReferenciaDTO.setCodigoSubReferencia(rs.getInt("codigo_sub_referencia"));
				subReferenciaDTO.setDescricaoSubReferencia(rs.getString("sub_referencia"));
				subReferenciasDTOs.add(subReferenciaDTO);
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
		return subReferenciasDTOs;
	}

	@Override
	public List<SubReferenciaDTO> getComboByReferencia(Integer codigoReferencia) throws Exception {
		
		List<SubReferenciaDTO> subReferenciaDTOs = new ArrayList<SubReferenciaDTO>();
		
		StringBuilder query = new StringBuilder();
		query.append("select ");
		query.append("	codigo_sub_referencia, ");
		query.append("	concat(sub_referencia, '(', codigo_sub_referencia, ')') as sub_referencia ");
		query.append("from ");
		query.append("	tb_sub_referencia ");
		query.append("where ");
		query.append("	codigo_referencia = ? ");
		query.append("	and ifnull(inativo, 0) = 0 ");
		
		PreparedStatement statement = null;
						
		try 
		{
			statement = this.connection.prepareStatement(query.toString());
			statement.setInt(1, codigoReferencia);
			statement.executeQuery();
			ResultSet rs = statement.getResultSet();
			
			while (rs.next()) {
				SubReferenciaDTO subReferenciaDTO = new SubReferenciaDTO();
				subReferenciaDTO.setCodigoSubReferencia(rs.getInt("codigo_sub_referencia"));
				subReferenciaDTO.setDescricaoSubReferencia(rs.getString("sub_referencia"));
				subReferenciaDTOs.add(subReferenciaDTO);
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
		return subReferenciaDTOs;
	}

	@Override
	public void getAll(SubReferenciaDTO subReferenciaDTO) throws Exception {
		
		StringBuilder query = new StringBuilder();
		query.append("select ");
		query.append("	s.codigo_sub_referencia, ");
		query.append("	s.sub_referencia, ");
		query.append("	concat(ifnull(r.referencia, ' - '), '(', s.codigo_referencia, ')') as referencia, ");
		query.append("	ifnull(s.data_alteracao, s.data_criacao) as data_alteracao, ");
		query.append("	s.inativo ");
		query.append("from ");
		query.append("	tb_sub_referencia s ");
		query.append("	inner join tb_referencia r on s.codigo_referencia=r.codigo_referencia ");
		query.append("where 1 = 1 ");
		if(subReferenciaDTO.getInativo() < 2) {
			query.append("	and ifnull(s.inativo, 0) = ? ");
		} else {
			query.append("	and ifnull(s.inativo, 0) < ? ");
		}
		if(null != subReferenciaDTO.getDescricaoSubReferencia() && "" != subReferenciaDTO.getDescricaoSubReferencia()
				&& subReferenciaDTO.getCodigoReferencia() > 0) {
			query.append("	and s.sub_referencia like ? ");
			query.append("	and s.codigo_referencia = ? ");
		} else if(null != subReferenciaDTO.getDescricaoSubReferencia() && "" != subReferenciaDTO.getDescricaoSubReferencia()) {
			query.append("	and s.sub_referencia like ? ");
		} else if(subReferenciaDTO.getCodigoReferencia() > 0) {
			query.append("	and s.codigo_referencia = ? ");
		}

		PreparedStatement statement = null;
						
		try 
		{
			statement = this.connection.prepareStatement(query.toString());
			statement.setInt(1, subReferenciaDTO.getInativo());
			if(null != subReferenciaDTO.getDescricaoSubReferencia() && "" != subReferenciaDTO.getDescricaoSubReferencia()
					&& subReferenciaDTO.getCodigoReferencia() > 0) {
				statement.setString(2, '%' +subReferenciaDTO.getDescricaoSubReferencia()+ '%');
				statement.setInt(3, subReferenciaDTO.getCodigoReferencia());
			} else if(null != subReferenciaDTO.getDescricaoSubReferencia() && "" != subReferenciaDTO.getDescricaoSubReferencia()) {
				statement.setString(2, '%' +subReferenciaDTO.getDescricaoSubReferencia()+ '%');
			} else if(subReferenciaDTO.getCodigoReferencia() > 0) {
				statement.setInt(2, subReferenciaDTO.getCodigoReferencia());				
			}
			statement.executeQuery();
			ResultSet rs = statement.getResultSet();
			
			while (rs.next()) {
				
				SubReferencia sub = new SubReferencia();
				sub.setCodigoSubReferencia(rs.getInt("codigo_sub_referencia"));
				sub.setDescricaoSubReferencia(rs.getString("sub_referencia"));
				sub.setDescricaoReferencia(rs.getString("referencia"));
				LocalDate ldAlteracao = LocalDate.parse(rs.getString("data_alteracao"));
				sub.setDataAlteracao(ldAlteracao);
				sub.setInativo(rs.getInt("inativo"));
				subReferenciaDTO.add(sub);
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
	}

	@Override
	public boolean gravar(SubReferenciaDTO subReferenciaDTO, Integer codigoUsuario) throws Exception {

		boolean isUpdated = false;
		
		StringBuilder query = new StringBuilder();

		query.append("update ");
		query.append("	tb_sub_referencia ");
		query.append("set ");
		query.append("	sub_referencia = ?, "); 			//1
		query.append("	codigo_referencia = ?, ");			//2
		query.append("	codigo_usuario_alteracao = ?, ");	//3
		query.append("	inativo = ?, ");					//4
		query.append("	data_alteracao = now() ");			
		query.append("where ");
		query.append("	codigo_sub_referencia = ? ");			//5
		
		PreparedStatement statement = null;
		
		try 
		{
			statement = this.connection.prepareStatement(query.toString());
			statement.setString(1, subReferenciaDTO.getDescricaoSubReferencia());
			statement.setInt(2, subReferenciaDTO.getCodigoReferencia());
			statement.setInt(3, codigoUsuario);
			statement.setInt(4, subReferenciaDTO.getInativo());
			statement.setInt(5, subReferenciaDTO.getCodigoSubReferencia());
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
		query.append("	tb_sub_referencia");
		query.append("(");
		query.append("	sub_referencia, ");			//1
		query.append("	codigo_referencia,");		//2
		query.append("	codigo_usuario_criacao,");	//3
		query.append("	data_criacao");
		query.append(")");
		query.append("	select ?, ?, ?, now() ");
		
		try 
		{
			if(!isUpdated) 
			{
				statement = this.connection.prepareStatement(query.toString());
				statement.setString(1, subReferenciaDTO.getDescricaoSubReferencia());
				statement.setInt(2, subReferenciaDTO.getCodigoReferencia());
				statement.setInt(3, codigoUsuario);
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
	public SubReferencia getReferenciaById(Integer id) throws Exception {
		
		SubReferencia subReferencia = new SubReferencia();
		
		StringBuilder query = new StringBuilder();
		query.append("select ");
		query.append("	s.codigo_sub_referencia, ");
		query.append("	s.sub_referencia, ");
		query.append("	s.codigo_referencia, ");
		query.append("	concat(ifnull(r.referencia, ' - '), '(', s.codigo_referencia, ')') as referencia, ");
		query.append("	ifnull(s.inativo, 0) as inativo, ");
		query.append("	ifnull(concat(c.usuario, '(', s.codigo_usuario_criacao, ')'), ' - ') as usuario_criacao, ");
		query.append("	ifnull(concat(a.usuario, '(', s.codigo_usuario_alteracao, ')'), ' - ') as usuario_alteracao, ");
		query.append("	s.data_criacao, ");
		query.append("	s.data_alteracao ");
		query.append("from ");
		query.append("	tb_sub_referencia s");
		query.append("	inner join tb_referencia r on s.codigo_referencia=r.codigo_referencia ");
		query.append("	left join tb_usuario c on r.codigo_usuario_criacao=c.codigo_usuario ");
		query.append("	left join tb_usuario a on r.codigo_usuario_alteracao=a.codigo_usuario ");
		query.append("where s.codigo_sub_referencia = ? ");
		
		PreparedStatement statement = null;
						
		try 
		{
			statement = this.connection.prepareStatement(query.toString());
			statement.setInt(1, id);
			statement.executeQuery();
			ResultSet rs = statement.getResultSet();
			
			while (rs.next()) {
				
				subReferencia.setCodigoSubReferencia(rs.getInt("codigo_sub_referencia"));
				subReferencia.setDescricaoSubReferencia(rs.getString("sub_referencia"));
				subReferencia.setCodigoReferencia(rs.getInt("codigo_referencia"));
				subReferencia.setDescricaoReferencia(rs.getString("referencia"));
				subReferencia.setInativo(rs.getInt("inativo"));
				subReferencia.setUsuarioCriacao(rs.getString("usuario_criacao"));
				subReferencia.setUsuarioAlteracao(rs.getString("usuario_alteracao"));
				LocalDate lCriacao = LocalDate.parse(rs.getString("data_criacao"));
				subReferencia.setDataCriacao(lCriacao);
				if(null != rs.getString("data_alteracao")) {
					LocalDate lAlteracao = LocalDate.parse(rs.getString("data_alteracao"));
					subReferencia.setDataAlteracao(lAlteracao);
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
		return subReferencia;
	}

	@Override
	public boolean remove(final Integer id, final Boolean inativo, final Integer codigoUsuario) throws Exception {

		boolean isUpdated = false;
		
		StringBuilder query = new StringBuilder();

		query.append("update ");
		query.append("	tb_sub_referencia ");
		query.append("set ");
		if(inativo) {
			query.append("	inativo = 0, ");
		} else {
			query.append("	inativo = 1, ");
		}
		query.append("	codigo_usuario_alteracao = ?, ");
		query.append("	data_alteracao = convert(now(), date) ");
		query.append("where ");
		query.append("	codigo_sub_referencia = ? ");
		
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

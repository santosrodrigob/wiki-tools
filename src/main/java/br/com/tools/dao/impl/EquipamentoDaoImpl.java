package br.com.tools.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.tools.dao.EquipamentoDao;
import br.com.tools.model.EquipamentoDTO;

public class EquipamentoDaoImpl implements EquipamentoDao {

	private Connection connection;
	
	public EquipamentoDaoImpl(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public List<EquipamentoDTO> getAll() throws Exception {
		return null;
	}

	@Override
	public List<EquipamentoDTO> getComboEquipamentos() throws Exception {
		
		List<EquipamentoDTO> equipamentos = new ArrayList<EquipamentoDTO>();
		
		StringBuilder query = new StringBuilder();
		query.append("select ");
		query.append("	codigo_equipamento, ");
		query.append("	equipamento ");
		query.append("from ");
		query.append("	tb_equipamento ");
		query.append("where ");
		query.append("	ifnull(inativo, 0) = 0");
		
		PreparedStatement statement = null;
						
		try 
		{
			statement = this.connection.prepareStatement(query.toString());
			statement.executeQuery();
			ResultSet rs = statement.getResultSet();
			
			while (rs.next()) {
				EquipamentoDTO equipamentoDTO = new EquipamentoDTO();
				equipamentoDTO.setCodigoEquipamento(rs.getInt("codigo_equipamento"));
				equipamentoDTO.setDescricaoEquipamento(rs.getString("equipamento"));
				equipamentos.add(equipamentoDTO);
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
		return equipamentos;
	}
}

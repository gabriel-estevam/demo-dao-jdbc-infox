package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.OrdemServicoDao;
import model.entities.Client;
import model.entities.Employee;
import model.entities.OrdemServico;
import model.entities.Service;

public class OrdemServicoDaoJDBC implements OrdemServicoDao
{
	private Connection conn;
	
	public OrdemServicoDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(OrdemServico obj) 
	{
		PreparedStatement st = null;
		
		try 
		{
			st = conn.prepareStatement("INSERT INTO tb_ordem_servico(\r\n" + 
					"    Ordem_Inicial_Date,\r\n" + 
					"    Ordem_Final_Date,\r\n" + 
					"    Ordem_Equipamento,\r\n" + 
					"    Ordem_Defeito,\r\n" + 
					"    Ordem_Laudo,\r\n" + 
					"    Ordem_Status,\r\n" + 
					"    Employee_Id,\r\n" + 
					"    Client_Id,\r\n" + 
					"    Service_Id\r\n" + 
					") VALUES(?, ?, ?, ?, \r\n" + 
					"	    ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
			
			st.setDate(1, new java.sql.Date(obj.getDtIncial().getTime()));
			st.setDate(2, new java.sql.Date(obj.getDtFinal().getTime()));
			st.setString(3,obj.getEquipamento());
			st.setString(4, obj.getDefeito());
			st.setString(5, obj.getLaudo());
			st.setString(6, obj.getStatus());
			st.setInt(7, obj.getEmployeeId().getId());
			st.setInt(8, obj.getClientId().getId());
			st.setInt(9, obj.getServiceId().getId());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) 
			{
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpected error! No rows affected");
			}
		} 
		catch (SQLException e)
		{
			throw new DbException(e.getMessage());
		}
		finally 
		{
			DB.closeStatement(st);
		}
	}
	@Override
	public void update(OrdemServico obj) 
	{
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public OrdemServico findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrdemServico> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrdemServico> findByEmployeeId(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrdemServico> findByClientId(Client client) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrdemServico> findByServiceId(Service service) {
		// TODO Auto-generated method stub
		return null;
	} 

}

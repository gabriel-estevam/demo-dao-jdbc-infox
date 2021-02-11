package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.ServiceDao;
import model.entities.Service;

public class ServiceDaoJDBC  implements ServiceDao{

	private Connection conn;
	
	public ServiceDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Service obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Service obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Service findById(Integer id) 
	{
		//metodo para retorna um serviço pelo seu id
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try 
		{
			st = conn.prepareStatement("SELECT * FROM tb_Service WHERE Service_Id = ? "); //query para retorna um service
			st.setInt(1, id); //seta para query qual o id a ser buscado
			rs = st.executeQuery(); //executa a query e guarda o resultado da consulta variavel "rs"
			
			if(rs.next()) //valida se o resultado contem o registro
			{
				Service obj = new Service(); //instancia um service e abaixo seta os valores para o objeto
				obj.setId(rs.getInt("Service_Id"));
				obj.setName(rs.getString("Service_Name"));
				obj.setValor(rs.getDouble("Service_Valor"));
				obj.setDescricao(rs.getString("Service_Descricao"));
				return obj; //retorna o objeto com os valores da consulta
			}
			return null; //retorna nulo caso a consultado não tenha nada
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	@Override
	public List<Service> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

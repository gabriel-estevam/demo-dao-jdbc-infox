package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.ServiceDao;
import model.entities.Service;

public class ServiceDaoJDBC  implements ServiceDao{

	private Connection conn;
	
	public ServiceDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Service obj) 
	{
		//Metodo para inserir um serviço na tabela de serviços
		PreparedStatement st = null;
		try 
		{
			st = conn.prepareStatement("INSERT INTO tb_service(Service_Name, Service_Valor ,Service_Descricao) "  
					+"VALUES(?, ?, ? )", Statement.RETURN_GENERATED_KEYS); //query para inserir um serviço,apos a inserção retorna o id gerado
			//abaixo estamos setando os valores a serem inseridos
			st.setString(1, obj.getName());
			st.setDouble(2, obj.getValor());
			st.setString(3, obj.getDescricao());
			
			int rowsAffected = st.executeUpdate(); //executa a query e guarda o valor de execução, que é um int, na variavel
			
			if(rowsAffected > 0) 
			{
			//esse bloco verifica se a execução da query funcionou
				ResultSet rs = st.getGeneratedKeys(); //pega o id do registro que foi e armazena no objeto ResultSet
				if(rs.next()) {
				//esse bloco acessa o ResultSet 
					int id = rs.getInt(1); //pega o id gerado
					obj.setId(id); //seta o id para objeto
				}
				DB.closeResultSet(rs); //fecha a conexão com ResultSet
			}
			else {
				throw new DbException("Unexpected error! No rows affected");
			}
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Service obj) 
	{
		// Metodo para atualizar um registro (serviço) do banco de dados
		PreparedStatement st = null;
		try 
		{
			st = conn.prepareStatement(
					"  UPDATE tb_service " 
					+" SET Service_Name = ?, " 
					+"	   Service_Valor = ?, "  
					+"     Service_Descricao = ? " 
					+" WHERE Service_Id = ? "); //query para alterar um registro pelo seu id
			//seta as infomações para a query
			st.setString(1, obj.getName());
			st.setDouble(2, obj.getValor());
			st.setString(3, obj.getDescricao());
			st.setInt(4, obj.getId());
			st.executeUpdate(); //executa a query
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) 
	{
		// Metodo para deletar um registro (servico) pelo seu id
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM tb_service WHERE Service_Id = ?"); //query para deletar um registro
			st.setInt(1, id); //seta o id para a query
			st.executeUpdate(); //executa a query
		} 
		catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
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
	public List<Service> findAll() 
	{
		//Metodo para retorna todos os serviços cadastrado, vai retornar uma lista do tipo Service
		PreparedStatement st = null;
		ResultSet rs = null;
		try 
		{
			st = conn.prepareStatement("SELECT * FROM tb_service ORDER BY Service_Name "); //query para retornar todos os serviços
			rs = st.executeQuery(); //executa a query e armazena o resultado no objeto ResultSet
			List<Service> list = new ArrayList<>(); //lista para armazenar os serviços
			
			while(rs.next())
			{
				//esse bloco vai percorrer o ResultSet e instanciar um objeto Service setar o valor e adicinar na lista
				Service obj = new Service(); //instancia um service e abaixo seta os valores para o objeto
				obj.setId(rs.getInt("Service_Id"));
				obj.setName(rs.getString("Service_Name"));
				obj.setValor(rs.getDouble("Service_Valor"));
				obj.setDescricao(rs.getString("Service_Descricao"));
				list.add(obj); //adiciona o objeto na lista, para cada registro um novo objeto
			}
			return list; //retonar a lista apos percorrer todo o ResulSet
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

}

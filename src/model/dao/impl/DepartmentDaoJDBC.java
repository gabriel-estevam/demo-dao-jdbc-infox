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
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao
{
	//essa classe implentado todos os metodos de acesso ao banco de dados
	private Connection conn;
	
	public DepartmentDaoJDBC(Connection conn) {
		//construtor da classe, recebe como parametro uma conexão com o banco de dados
		//apos estabelecer a conexão podemos usar os metodos implementados
		this.conn = conn;
	}
	
	@Override
	public void insert(Department obj) 
	{
		//metodo para fazer a inserção no banco de dados, tem como parametro um objeto do tipo Department
		PreparedStatement st = null;
		
		try 
		{
			st = conn.prepareStatement("INSERT INTO tb_department(Department_Name) VALUES(?) ",Statement.RETURN_GENERATED_KEYS);
			//esse comando faz a inserção e retorna o valor do seu Id gerado
			st.setString(1, obj.getName()); //seta o nome do departmento para o banco
			int rowsAffected =  st.executeUpdate(); //executa o comando e armazena na variavel "rowsAffected" um valor interior 0 ou 1
			
			if(rowsAffected > 0) //se o valor for maior do que 0, segnifica que executou
			{
				ResultSet rs = st.getGeneratedKeys(); //Essa variavel recebe o ResultSet do id que o insert gerou
				
				while(rs.next()) //navega a coluna do id gerado
				{
					int id = rs.getInt(1); //esse variavel armazena o id que ele encotrou na inserção
					obj.setId(id); //seta para o objeto seu id
				}
				
				DB.closeResultSet(rs); //fecha a conexão com resultSet que abrimos nesse bloco
			}
			else
			{
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
	public void update(Department obj) {
		// Metodo para autulizar um registro do banco de dados, tem como parametro um objeto do tipo Department
		PreparedStatement st = null;
		try
		{
			st = conn.prepareStatement("UPDATE tb_department SET Department_Name = ? WHERE Department_Id = ? ");
			st.setString(1, obj.getName()); //seta o nome para a coluna Deparment_Name
			st.setInt(2, obj.getId()); //seta o Id para a coluna Department_Id
			st.executeUpdate();
		} catch (SQLException e) 
		{
			throw new DbException(e.getMessage());
		}
		finally
		{
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) 
	{
		//Metodo para deletar um registro do banco de dados pelo seu Id, tem como parametro um interger que vai receber o Id do departamento a ser deletado	
		PreparedStatement st = null;
		try
		{
			st = conn.prepareStatement("DELETE FROM tb_department WHERE Department_Id = ? ");
			st.setInt(1, id);
			st.executeUpdate();
		} 
		catch (SQLException e) 
		{
			throw new DbIntegrityException(e.getMessage());
		}
		finally
		{
			DB.closeStatement(st);
		}
	}

	@Override
	public Department findById(Integer id) 
	{
		//metodo que retorna um departmento pelo seua Id, tem como parametro um valor integer 
		//e retorna um departamento
		PreparedStatement st = null;
		ResultSet rs = null;
		try
		{
			st = conn.prepareStatement("SELECT * FROM tb_department WHERE Department_Id = ? ");
			st.setInt(1, id); //seta para a query qual o id, esse id vem do parametro do metodo
			rs = st.executeQuery(); //executa a query
			
			while(rs.next()) //enquanto tiver valor no ResultSet, true
			{
				Department department = new Department(); //instancia um novo objeto do tipo Department
				department.setId(rs.getInt("Department_Id")); //seta para o objeto o Id que encontrou no coluna Department_Id
				department.setName(rs.getString("Department_Name")); //seta para obj o nome que encontrou na coluna Department_Name
				return department; //retorna um novo departmento
			}
			return null; //caso não encotre nada no ResultSet, retorna nulo
		}
		catch(SQLException e)
		{
			throw new DbException(e.getMessage()); //lança uma exceção se acontecer um erro no momento da execução
		}
		finally
		{
			DB.closeResultSet(rs); //fecha a conexão com o ResultSet
			DB.closeStatement(st); //fecha a conexão com PreparedStatement
		}
	}

	@Override
	public List<Department> findAll() 
	{
		//metodo para retornar todos os departmentos, esse metodo retorna uma lista de departamentos
		PreparedStatement st = null;
		ResultSet rs = null;
		try
		{
			st = conn.prepareStatement("SELECT * FROM tb_department ");
			rs = st.executeQuery();
			List<Department> list = new ArrayList<>(); //list para retorna os departamentos, é do tipo department
			while(rs.next())
			{
				Department department = new Department(); //é instanciado um objeto para cada departamento do resultSet
				department.setId(rs.getInt("Department_Id")); //seta o id do departamento para o objeto
				department.setName(rs.getString("Department_Name")); //seta o nome departamento para o objeto
				list.add(department); //adiciona na lista o departmento, com os  dados setados no objeto
			}
			return list; //retorna a lista
		}
		catch(SQLException e)
		{
			throw new DbException(e.getMessage());
		}
		finally
		{
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

}

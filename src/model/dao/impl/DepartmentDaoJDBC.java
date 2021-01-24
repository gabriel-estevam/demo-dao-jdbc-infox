package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
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
	public void insert(Department obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Department obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
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
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

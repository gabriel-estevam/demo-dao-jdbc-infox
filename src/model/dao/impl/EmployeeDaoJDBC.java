package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.EmployeeDao; //essa camada, criamos classes que implementa os metodos de acesso ao banco de daos
import model.entities.Department;
import model.entities.Employee;

public class EmployeeDaoJDBC implements EmployeeDao
{
	private Connection conn; 
	
	public EmployeeDaoJDBC(Connection conn) {
		//Construtor que tem como parametro uma conexão, a DaoFactory chama
		//chama no parametro o metodo getConnection() da classe DB, essa conexão
		//vem  nesse construtor e apartir da podemos usar os metodos implementados
		this.conn = conn;
	}

	//essa classe implementa todos os metodos de acesso a dados de um funcionario
	@Override
	public void insert(Employee obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Employee obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee findById(Integer id) {
		//vai retorna um resultSet do select abaixo,
		//esse resultSet Preenche os atributos dos objetos abaixo
		PreparedStatement st = null;
		ResultSet rs = null;
		try 
		{
			st = conn.prepareStatement(
					"SELECT emp.*,\r\n" + 
					"	   dep.Department_Name\r\n" + 
					"FROM tb_employee as emp\r\n" + 
					"INNER JOIN tb_department as dep\r\n" + 
					"ON emp.Department_Id = dep.Department_Id\r\n" + 
					"WHERE emp.Employee_Id = ?;"
				);
			st.setInt(1, id);//passado como parametro, valor 1, que é o primeiro interroga na query acima, e o Id
			//que for informado no metodo
			rs = st.executeQuery(); //executa a query e retorna o ResultSet
			
			if(rs.next())
			{
				//Aqui vamos percorrre o ResultSet e enviar para os objetos os resultados obtidos
				Department dep = new Department();
				dep.setId(rs.getInt("Department_Id"));
				dep.setName(rs.getString("Department_Name"));
				Employee obj = new Employee();
				obj.setId(rs.getInt("Employee_Id"));
				obj.setName(rs.getString("Employee_Name"));
				obj.setCpf(rs.getString("Employee_Cpf"));
				obj.setBirthDate(rs.getDate("Employee_BirthDate"));
				obj.setBaseSalary(rs.getDouble("Employee_BaseSalary"));
				obj.setEmail(rs.getString("Employee_Email"));
				obj.setDepartmentId(dep);
				return obj; //retona o obj
			}
			return null; //caso não tenha nada na tabela (resultSet) retorna nulo
		} 
		catch (SQLException e) 
		{
			throw new DbException(e.getMessage());// lança exception
		}
		finally 
		{
			//fecha conexão
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findByDepartmentId(Integer id) {
		return null;
	}

}

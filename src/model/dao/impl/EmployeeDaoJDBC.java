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
			/*Aqui aplicamos os metodos de reutilização de codigo, portanto se algum
			 * outro metodo venha ter relação com department ou employee, cria uma variavel
			 * e chama o metodo*/
				Department dep = instantiateDepartment(rs);
				Employee obj = instantiateEmployee(dep, rs); 
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
	
	private Employee instantiateEmployee(Department dep, ResultSet rs) throws SQLException 
	{
		/*Metodo para reutilização de codigo, tem como parametro um Departamento e um
		 * ResultSet do banco nesse metodo instanciamos  um objeto do tipo Employee e 
		 * setamos os valores obtido do resultset
		*/
		Employee obj = new Employee();
		obj.setId(rs.getInt("Employee_Id"));
		obj.setName(rs.getString("Employee_Name"));
		obj.setCpf(rs.getString("Employee_Cpf"));
		obj.setBirthDate(rs.getDate("Employee_BirthDate"));
		obj.setBaseSalary(rs.getDouble("Employee_BaseSalary"));
		obj.setEmail(rs.getString("Employee_Email"));
		obj.setDepartmentId(dep);
		return obj;
	}
	
	private Department instantiateDepartment(ResultSet rs) throws SQLException
	{
		/*Metodo para reutilização de codigo, nesse metodo 
		 * temos como parametro um ResultSet que o SELECT retorna
		 * Instanciamos um objeto do tipo department, e setamos os valores
		 * obtido do ResultSet, esse metodo pode gerar uma execeção, então ela é 
		 * propagada no metodo
		*/
		Department dep = new Department();
		dep.setId(rs.getInt("Department_Id"));
		dep.setName(rs.getString("Department_Name"));
		return dep;
	}
}

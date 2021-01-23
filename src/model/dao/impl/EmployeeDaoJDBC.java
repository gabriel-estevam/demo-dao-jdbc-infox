package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public void insert(Employee obj) 
	{
		//metodo para inserir um novo funcionario no banco de dados, os dados do funcionario ja vem direto
		//do objeto do parametro
		PreparedStatement st = null;
		try 
		{
			st = conn.prepareStatement(
					"INSERT INTO tb_employee "
					+ "(Employee_Name, Employee_cpf, Employee_BirthDate , Employee_BaseSalary, Employee_Email, Department_Id)\r\n"
					+ "VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS); //esse metodo alem de executar o insert, tambem vai gerar
			//a chave gera da inserção, para podermos usar para sertar o id daquele funcionario no objeto
			st.setString(1, obj.getName());
			st.setString(2, obj.getCpf());
			st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			st.setDouble(4, obj.getBaseSalary());
			st.setString(5, obj.getEmail());
			st.setInt(6, obj.getDepartmentId().getId());
			
			int rowsAffected = st.executeUpdate(); //executa o insert se deu certo retona 1, caso contrario 0
			
			if(rowsAffected > 0) // se o insert deu certo a variavel rowsAffected sera maior que 0
			{
				ResultSet rs = st.getGeneratedKeys(); //essa variavel vai receber o valor da chave gerada para o registro
				//o retorno desse metodo é um resultSet porque ele gera apenas uma coluna (não é a employee_Id)
				if(rs.next()) //se o resultSet é true
				{
					int id = rs.getInt(1); //declarado uma variavel para setar o id do funcionario, atraves da variavel
				 //rs vamos acessar a "coluna" que esta o Id, pegando o seu valor gerado
					obj.setId(id); //seta o id para o objeto
				}
				DB.closeResultSet(rs); //fecha a conexão com ResultSet
			}
			else
			{
				throw new DbException("Unexpected error! No rows affected"); //Caso a execução tenha falhado lança uma exceção
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
	public void update(Employee obj) 
	{
		//metodo para atulizar
		PreparedStatement st = null;
		try
		{
			st = conn.prepareStatement(
					"UPDATE tb_employee \r\n" + 
					"SET Employee_Name = ?,\r\n" + 
					"	Employee_Cpf = ?,\r\n" + 
					"    Employee_BirthDate = ?,\r\n" + 
					"	Employee_BaseSalary = ?,\r\n" + 
					"    Employee_Email = ?,\r\n" + 
					"    Department_Id = ?\r\n" + 
					"WHERE Employee_Id = ?;"
					);
			st.setString(1, obj.getName());
			st.setString(2, obj.getCpf());
			st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			st.setDouble(4, obj.getBaseSalary());
			st.setString(5, obj.getEmail());
			st.setInt(6, obj.getDepartmentId().getId());
			st.setInt(7, obj.getId());
			
			st.executeUpdate();
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
	public List<Employee> findAll() 
	{
		//metodo que retorna todos os funcionarios e seu respectivo departamento
		//tem o mesmo funcionamento que o metodo findByDepartmentId com diferença do 
		//script sql
		PreparedStatement st = null;
		ResultSet rs = null;
		try 
		{
			st = conn.prepareStatement(
					"SELECT emp.*,\r\n" + 
					"	dep.Department_Name\r\n" + 
					"FROM tb_employee AS emp\r\n" + 
					"INNER JOIN tb_department AS dep\r\n" + 
					"ON emp.Department_Id = dep.Department_Id\r\n" + 
					"ORDER BY emp.Employee_Name;"
					);
			rs = st.executeQuery();
			
			List<Employee> list = new ArrayList<>();//lista para armazenar o resultado
			Map<Integer, Department> map = new HashMap<>();//map para fazer o mapeamento de departmentos
			
			while(rs.next())
			{
				//nesse while estamos percorrendo o resultSet gerado
				Department dep = map.get(rs.getInt("Department_Id")); // aqui começa o mapeamento atraves do metodo get
//dentro do metodo estamos percorrendo a coluna departmentId, esse metodo retorna null quando não econtra nada nada na sua chave 
//portanto, o map vai verificar se ele tem um chave (key) que é o id do departamento e seu id correspondente, caso não encotre instancia
// um novo objeto, caso encontre não instancia apenas aponta qual o objeto pertence aquela chave
				if(dep == null)
				{
					//nesse if estamos verificando se o objeto department é nulo, caso seja é instanciado um departmento
					//e armazenado na chave do mapeamento o valor do id e seu respectivo objeto
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("Department_Id"), dep);
				}
				Employee obj = instantiateEmployee(dep, rs); //instancia um novo objeto do tipo Employee
				list.add(obj); //adiciona na lista o objeto (funcionario)
			}
			return list;
		} 
		catch (SQLException e) 
		{
			throw new DbException(e.getMessage());
		}
		finally
		{
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	@Override
	public List<Employee> findByDepartmentId(Department department) 
	{
		// metodo que retorna todos os funcionarios de um determinado departmento
		PreparedStatement st = null;
		ResultSet rs = null;
		try 
		{
			st = conn.prepareStatement(
					"SELECT emp.*,\r\n" + 
					"	   dep.Department_Name\r\n" + 
					"FROM tb_employee AS emp\r\n" + 
					"INNER JOIN tb_department AS dep\r\n" + 
					"ON emp.Department_Id = dep.Department_Id\r\n" + 
					"WHERE dep.Department_Id = ?\r\n" + 
					"ORDER BY dep.Department_Name;"
					);
			st.setInt(1, department.getId()); //passa o valor do id obtido da query
			rs = st.executeQuery();

			List<Employee> list = new ArrayList<>(); //lista para armazenar os funcionarios
			Map<Integer, Department> map = new HashMap<>(); //vai fazer o mapeamento dos objetos do tipo department, 
	//para que não acontece de ficar instanciando um novo department para cada funcionario
			while(rs.next())
			{
				//while vai percorre o resultSet quando true
				
				Department dep = map.get(rs.getInt("Department_Id")); //essa variavel vai armazenar objeto departmento 
//para cada department que ele encontrar na coluna Department_Id do ResultSet, isso atraves do map.get, então antes o metodo get
//vai tentar fazer a busca na coluna department o id do resultSet (rs da estrutura while), caso ele não encontre retora null
//para a variavel, se null dai sim instanciamos um novo objeto do tipo Department
//se não fizermos isso vai ficar instanciando um novo deparmento para cada funcionario, mesmo estando no mesmo departmento
				if(dep == null)
				{
					//esse bloco verifica se o department é nulo, caso seja 
					dep = instantiateDepartment(rs); //instancia um novo departmento
					map.put(rs.getInt("Department_Id"), dep); //depois de instanciado armazena no map, o valor
					//do id do departamento (chave) e seu objeto (valor)
				}
				
				Employee obj = instantiateEmployee(dep, rs); //instancia um novo funcionario
				list.add(obj); //adiciona na lista um funcionario
			}
			return list; //retorna a lista de funcinarios
		} 
		catch (SQLException e) 
		{
			throw new DbException(e.getMessage());
		}
		finally
		{
			DB.closeResultSet(rs);
			DB.closeStatement(null);
		}
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

package model.dao;

import db.DB;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.EmployeeDaoJDBC;
import model.dao.impl.UserDaoJDBC;

public class DaoFactory 
{
	/*Essa classe é responsavel pela criação dos daos, isto é, aqui nesse classe
	 * criamos METODOS ESTATICOS que disponibilizam o acesso ao banco de dados, por exemplo
	 * queremos fazer um acesso a Employee, então criamos um metodo estatico que retorna um
	 * employeeDao que é a interface que contem um dos metodos queremos  utilizar,
	 * porem internamente no metodo retornamos uma nova classe que ja implemnta, e contempla
	 * todos os metodos de acesso ao Employee
	 * 
	 * public static [CLASSE_DAO_QUE_IMPLEMENTA_METODO_ACESSO] create_NOME_DA_CLASSE_DAO() {
	 * 		return new CLASSE_QUE_IMPLEMENTA_METODOS_ACESSO;
	    }
	 *Portanto ao inves, de chamarmos a interface, chamamos o metodo que ja implementa
	 *essa interface que esta na camada model.dao.impl
	 *
	 *Exemplo instanciando um obj: INTERFACE_DAO NomeObjeto = DaoFactory.METODO_CREATE_DAO();
	 *Sendo assim, acessamos a classe que implementa os metodos da interface
	 * */
	public static EmployeeDao createEmployeeDao() {
		return new EmployeeDaoJDBC(DB.getConnection());
	}
	
	public static DepartmentDao createDepartmentDao() {
		return new DepartmentDaoJDBC(DB.getConnection());
	}
	
	public static UserDao createUserDao() {
		return new UserDaoJDBC(DB.getConnection());
	}
}

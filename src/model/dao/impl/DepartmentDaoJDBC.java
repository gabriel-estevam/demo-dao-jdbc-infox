package model.dao.impl;

import java.sql.Connection;
import java.util.List;

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
	public Department findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

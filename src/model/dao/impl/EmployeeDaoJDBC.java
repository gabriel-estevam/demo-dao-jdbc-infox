package model.dao.impl;

import java.util.List;

import model.dao.EmployeeDao; //essa camada, criamos classes que implementa os metodos de acesso ao banco de daos

import model.entities.Employee;

public class EmployeeDaoJDBC implements EmployeeDao
{
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

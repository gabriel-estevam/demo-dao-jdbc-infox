package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 
{
	public static void main(String[] args) 
	{
		//classe para testar os metodos
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		Department dep = departmentDao.findById(3);
		System.out.println("==Test DepartmentById==");
		System.out.println(dep);
	}
}
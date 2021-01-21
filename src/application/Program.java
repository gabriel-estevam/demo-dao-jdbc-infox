package application;

import model.dao.DaoFactory;
import model.dao.EmployeeDao;
import model.entities.Employee;

public class Program 
{
	public static void main(String[] args) 
	{
		EmployeeDao employeeDao = DaoFactory.createEmployeeDao();
		Employee employee = employeeDao.findById(5);
		System.out.println(employee);
	}
}

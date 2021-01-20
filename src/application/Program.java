package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.EmployeeDao;
import model.entities.Department;
import model.entities.Employee;

public class Program 
{
	public static void main(String[] args) 
	{
		Department obj = new Department(1,"Book");
		Employee employee = new Employee(1, "Bob", new Date(), "Bob@gmail.com", 3000.0,obj);
		EmployeeDao employeeDao = DaoFactory.createEmployeeDao();
		System.out.println(employee);
	}
}

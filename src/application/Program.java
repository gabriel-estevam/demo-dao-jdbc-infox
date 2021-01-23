package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.EmployeeDao;
import model.entities.Department;
import model.entities.Employee;

public class Program 
{
	public static void main(String[] args) 
	{
		//classe para testar os metodos
		EmployeeDao employeeDao = DaoFactory.createEmployeeDao();
		Employee employee = employeeDao.findById(5);
		System.out.println("=== Test 1 findById ===");
		System.out.println(employee);
		
		System.out.println("\n=== Test 2 findByDepartementId ===");
		Department dep = new Department(3, null);
		List<Employee>list =  employeeDao.findByDepartmentId(dep);
		for(Employee obj : list) {
			System.out.println(obj);
		}		
		System.out.println("\n=== Test 3 findAll");
		list = employeeDao.findAll();
		for(Employee obj : list) {
			System.out.println(obj);
		}
		
		Employee newEmployee = new Employee(null,"Maria", "345.345.345-00", new Date(),"maria@gmail.com",1000.0,dep);
		employeeDao.insert(newEmployee);
		System.out.println("\n=== Test 4 Insert new employee ===");
		System.out.println("Inserted! New id = " + newEmployee.getId());
	}
}

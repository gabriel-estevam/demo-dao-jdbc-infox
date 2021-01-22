package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.EmployeeDao;
import model.entities.Department;
import model.entities.Employee;

public class Program 
{
	public static void main(String[] args) 
	{
		EmployeeDao employeeDao = DaoFactory.createEmployeeDao();
		Employee employee = employeeDao.findById(5);
		System.out.println("=== Test 1 findById ===");
		System.out.println(employee);
		
		System.out.println("=== Test 2 findByDepartementId ===");
		Department dep = new Department(2, null);
		List<Employee>list =  employeeDao.findByDepartmentId(dep);
		for(Employee obj : list) {
			System.out.println(obj);
		}		
	}
}

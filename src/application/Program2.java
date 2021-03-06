package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
		
		List<Department> list = new ArrayList<>();		
		list = departmentDao.findAll();
		System.out.println("==Test 2 findAll==");
		for(Department obj : list){
			System.out.println(obj);
		}
		Department newDepartment = new Department(null, "Controle");
		System.out.println("==Test 3 insert==");
		departmentDao.insert(newDepartment);
		System.out.println("Inserted new id = " + newDepartment.getId());
		
		System.out.println("\n==Test 4 update");
		dep = departmentDao.findById(4);
		dep.setName("Suprimentos");
		departmentDao.update(dep);
		System.out.println("Updated completed");
		
		Scanner sc = new Scanner(System.in);
		System.out.println("\n==Test 5 delete==");
		System.out.print("Enter id for delete test: ");
		int id = sc.nextInt();
		departmentDao.deleteById(id);
		System.out.println("Deleted completed");
		sc.close();
	}
}

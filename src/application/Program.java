package application;

import java.util.Date;

import model.entities.Department;
import model.entities.Employee;

public class Program 
{
	public static void main(String[] args) 
	{
		Department obj = new Department(1,"Book");
		Employee employee = new Employee(1, "Bob", new Date(), "Bob@gmail.com", 3000.0,obj);
		
		System.out.println(employee);
	}
}

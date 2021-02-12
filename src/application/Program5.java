package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.ServiceDao;
import model.entities.Service;

public class Program5 
{
	public static void main(String[] args) 
	{
		//classe para testar os metodos
		ServiceDao serviceDao = DaoFactory.createServiceDao();
		
		System.out.println("==Test findById");
		Service service =  serviceDao.findById(3);
		System.out.println(service);
		
		System.out.println("\n==Test findAll");
		List<Service> list = new ArrayList<>();
		list = serviceDao.findAll();
		for(Service obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n==Test insert");
		Service newService = new Service(null, "Troca da tela Moto G5", "Troca tela Motorola Moto G5", 250.0);
		serviceDao.insert(newService);
		System.out.println("Inserted! New id = "+ newService.getId());
		
		System.out.println("\n==Test update");
		service = serviceDao.findById(4);
		service.setValor(245.00);
		serviceDao.update(service);
		System.out.println("Updated completed");
		
		Scanner sc = new Scanner(System.in);
		System.out.println("\n==Test delete");
		System.out.print("Enter id for delete: ");
		int id = sc.nextInt();
		serviceDao.deleteById(id);
		System.out.println("Delete completed");
		sc.close();
	}
}

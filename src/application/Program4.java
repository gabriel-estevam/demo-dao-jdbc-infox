package application;

import java.util.ArrayList;
import java.util.List;

import model.dao.ClientDao;
import model.dao.DaoFactory;
import model.entities.Client;

public class Program4 
{
	public static void main(String[] args) 
	{
		//classe para testar os metodos
		
		ClientDao clientDao = DaoFactory.createClientDao();
		
		System.out.println("==Test findById==");
		Client client = clientDao.findById(3);
		System.out.println(client);
		
		List<Client> list = new ArrayList<>(); 
		System.out.println("\n==Test 2 findAll==");
		list = clientDao.findAll();
		
		for(Client obj : list) {
			System.out.println(obj);
		}
		
//		Client newClient = new Client(null, "Paulo barbosa", "Rua natal, 34","Brasilia","Salto", "São Paulo", "11934342311", "S", "paulo@gmail.com");
//		System.out.println("\n==Test 3 insert==");
//		clientDao.insert(newClient);
//		System.out.println("Inserted! New id = " + newClient.getId());
		
		System.out.println("\n==Test 4 update==");
		client = clientDao.findById(2);
		client.setEmail("selena@gmail.com");
		clientDao.update(client);
		System.out.println("Updated completed");
	}
}

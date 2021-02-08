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
	}
}

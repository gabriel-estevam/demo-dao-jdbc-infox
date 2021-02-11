package application;

import java.util.ArrayList;
import java.util.List;

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
	}
}

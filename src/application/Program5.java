package application;

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
	}
}

package application;

import java.util.ArrayList;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;

public class Program3 
{
	public static void main(String[] args) 
	{
		//classe para testar os metodos
		
		UserDao userDao = DaoFactory.createUserDao();
		
		System.out.println("==Test finById==");
		User user = userDao.findById(3);
		System.out.println(user);
		
		System.out.println("\n==Test 2 findAll==");
		List<User> userList = new ArrayList<>(); 
		userList = userDao.findAll();
		for(User obj : userList) {
			System.out.println(obj);
		}
	}
}

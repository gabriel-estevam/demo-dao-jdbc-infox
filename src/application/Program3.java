package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
		
		User newUser = new User(null, "Usuario Teste", "teste5", "teste", "administrador");
		System.out.println("\n==Test 3 insert==");
		userDao.insert(newUser);
		System.out.println("Inserted! new Id = "+ newUser.getId());
		
		user = userDao.findById(7);
		user.setLogin("sistema.acesso");
		user.setName("Administrador");
		userDao.update(user);
		System.out.println("\n==Test 4 update==");
		System.out.println("Updated completed");
		
		Scanner sc = new Scanner(System.in);
		System.out.println("==Test 5 delete==");
		System.out.print("Enter id for delete test: ");
		int id = sc.nextInt();
		userDao.deleteById(id);
		System.out.println("Delete completed");
		sc.close();
	}
}

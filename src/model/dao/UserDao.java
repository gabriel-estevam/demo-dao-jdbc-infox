package model.dao;

import java.util.List;
import model.entities.User;

public interface UserDao 
{
	//interface responsavel disponibiliza os metodos de acesso (inserção, exclusão, atualização, buscas) do usuário do sistema
	void insert(User obj); //metodo para inserir, tem como parametro um objeto do tipo User
	void update(User obj); //metodo para atualizar, tem como parametro um objeto do tipo User 
	void deleteById(Integer id); //metodo para deletar um usuario, temo como parametro um Integer 
	User findById(Integer id); //metodo que retorna um usuario pelo seu id, tem como parametro um Integer 
	List<User>findAll(); //metodo para trazer todos os usuários
	
}

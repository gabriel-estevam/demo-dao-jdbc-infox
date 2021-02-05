package model.dao;

import java.util.List;

import model.entities.Client;

public interface ClientDao 
{
	//interace para responsavel por distribuir os metodos de acesso a dados do cliente
	void insert(Client obj); //metodo para inserir um novo cliente
	void update(Client obj); //metodo para atualizar um cliente
	void deleteById(Integer id); //metodo para delete um cliente pelo seu id
	Client findById(Integer id); //metodo para retornar um cliente pelo seu id
	List<Client> findAll();  //metodo para trazer todos os clientes
}

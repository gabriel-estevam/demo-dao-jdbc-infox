package model.dao;

import java.util.List;

import model.entities.Service;

public interface ServiceDao 
{
	//interface que disp�e dos metodos de acesso ao servi�o
	void insert(Service obj); //metodo para inserir um servi�o
	void update(Service obj); //metodo para atualizar um servi�o
	void deleteById(Integer id); //metodo para deletar um servi�o pelo seu id
	Service findById(Integer id); //metodo para retonar um servi�o pelo seu id
	List<Service> findAll(); //metodo para retornar todos os servi�os
}

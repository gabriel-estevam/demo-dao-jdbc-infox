package model.dao;

import java.util.List;

import model.entities.Service;

public interface ServiceDao 
{
	//interface que dispõe dos metodos de acesso ao serviço
	void insert(Service obj); //metodo para inserir um serviço
	void update(Service obj); //metodo para atualizar um serviço
	void deleteById(Integer id); //metodo para deletar um serviço pelo seu id
	Service findById(Integer id); //metodo para retonar um serviço pelo seu id
	List<Service> findAll(); //metodo para retornar todos os serviços
}

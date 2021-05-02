package model.dao;

import java.util.List;

import model.entities.Client;
import model.entities.Employee;
import model.entities.OrdemServico;
import model.entities.Service;

public interface OrdemServicoDao
{
	//Interface responsavel por acesso a dados a Ordem de Servico
	void insert(OrdemServico obj); //metodo para inserir uma ordem de servi�o
	void update(OrdemServico obj); //metodo para atualizar um ordem de servi�o
	void deleteById(Integer id); // metodo para deletar uma ordem de servi�o
	OrdemServico findById(Integer id);  //metodo para buscar uma ordem de servi�o pelo seu id
	List<OrdemServico> findAll(); //metodo para retorna todas as ordens de servi�os
	List<OrdemServico> findByEmployeeId(Employee employee); //metodo para retorna todas ordem de servi�o com base no id do funcionario
	List<OrdemServico> findByClientId(Client client); //metodo para retorna todas ordem de servi�o com base no id do cliente
	List<OrdemServico> findByServiceId(Service service); //metodo para retorna todas as ordem de servi�o com base no id do servi�o
}
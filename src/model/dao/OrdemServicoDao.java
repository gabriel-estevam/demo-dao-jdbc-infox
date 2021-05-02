package model.dao;

import java.util.List;

import model.entities.Client;
import model.entities.Employee;
import model.entities.OrdemServico;
import model.entities.Service;

public interface OrdemServicoDao
{
	//Interface responsavel por acesso a dados a Ordem de Servico
	void insert(OrdemServico obj); //metodo para inserir uma ordem de serviço
	void update(OrdemServico obj); //metodo para atualizar um ordem de serviço
	void deleteById(Integer id); // metodo para deletar uma ordem de serviço
	OrdemServico findById(Integer id);  //metodo para buscar uma ordem de serviço pelo seu id
	List<OrdemServico> findAll(); //metodo para retorna todas as ordens de serviços
	List<OrdemServico> findByEmployeeId(Employee employee); //metodo para retorna todas ordem de serviço com base no id do funcionario
	List<OrdemServico> findByClientId(Client client); //metodo para retorna todas ordem de serviço com base no id do cliente
	List<OrdemServico> findByServiceId(Service service); //metodo para retorna todas as ordem de serviço com base no id do serviço
}
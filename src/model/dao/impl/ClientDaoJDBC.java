package model.dao.impl;

import java.sql.Connection;
import java.util.List;

import model.dao.ClientDao;
import model.entities.Client;

public class ClientDaoJDBC implements ClientDao 
{
	//classe que implementa a interface ClientDao que disponibiliza os metodos de acesso a dados dp cliente

	private Connection conn;
	
	public ClientDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Client obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Client obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Client findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

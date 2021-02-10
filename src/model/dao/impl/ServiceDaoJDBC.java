package model.dao.impl;

import java.sql.Connection;
import java.util.List;

import model.dao.ServiceDao;
import model.entities.Service;

public class ServiceDaoJDBC  implements ServiceDao{

	private Connection conn;
	
	public ServiceDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Service obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Service obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Service findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Service> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

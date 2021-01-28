package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.UserDao;
import model.entities.User;

public class UserDaoJDBC implements UserDao 
{
	private Connection conn;
	
	public UserDaoJDBC(Connection conn){
		this.conn = conn;
	}
	
	@Override
	public void insert(User obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(User obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User findById(Integer id) 
	{
		//metodo para retorna um usuario pelo seu id
		PreparedStatement st = null;
		ResultSet rs = null;
		try 
		{
			st = conn.prepareStatement("SELECT * FROM tb_user WHERE User_Id = ? ");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			while(rs.next())
			{
				User user = new User();
				user.setId(rs.getInt("User_Id"));
				user.setName(rs.getString("User_Name"));
				user.setLogin(rs.getString("User_Login"));
				user.setPassword(rs.getString("User_Password"));
				user.setCategory(rs.getString("User_Category"));
				return user;
			}
			return null;
		} 
		catch (SQLException e) 
		{
			throw new DbException(e.getMessage());
		}
		finally
		{
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	@Override
	public List<User> findAll() 
	{
		// Metodo que retorna todos os usuarios cadastrados, retona uma lista do tipo User
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM tb_User ORDER BY User_Name ");
			rs = st.executeQuery();
		
			List<User> list = new ArrayList<>();
			
			while(rs.next())
			{
				User user = new User();
				user.setId(rs.getInt("User_Id"));
				user.setName(rs.getString("User_Name"));
				user.setLogin(rs.getString("User_Login"));
				user.setPassword(rs.getNString("User_Password"));
				user.setCategory(rs.getString("User_Category"));
				list.add(user);
			}
			return list;
		} 
		catch (SQLException e) 
		{
			throw new DbException(e.getMessage());
		}
		finally 
		{
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

}

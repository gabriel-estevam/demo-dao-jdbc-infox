package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	public void insert(User obj) 
	{
		// Metodo para inserir um usuario no sistema, tem como parametro um objeto do tipo User
		PreparedStatement st = null;
		try 
		{
			st = conn.prepareStatement(
					"INSERT INTO tb_User(User_Name, User_Login, User_Password, User_Category)\r\n" 
					+ "				VALUES(?,?,?,?) ", Statement.RETURN_GENERATED_KEYS); //script para inserir um usuario,
//esse metodo tambem retorna o Id do registro, que é gerado na aplicação com base no ultimo id que tem no banco
//detalhe inportante é que esse metodo seta para objeto na aplicação o valor do Id, pois no banco esse campo é auto_increment
			//set os valores para banco, esses valores acessamos atraves do objeto que esta no parametro do metodo
			st.setString(1, obj.getName());
			st.setString(2, obj.getLogin());
			st.setString(3, obj.getPassword());
			st.setString(4, obj.getCategory());
			
			int rowsAffected = st.executeUpdate(); //executa o script e amarzena o valor de retono na variavel
			
			if(rowsAffected > 0) //se o valor da variavel for maior que 0, funcionou 
			{
				ResultSet rs = st.getGeneratedKeys(); //objeto vai guardar o valor da chave gerada na inserção
				while(rs.next()) {
					//acessa as chaves gerada
					int id = rs.getInt(1); //pega o valor da chave que gerou
					obj.setId(id); //seta para objeto o id
				}
				DB.closeResultSet(rs);
			}
			else{
				throw new DbException("Unexpected error! No rows affected");
			}
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(User obj) 
	{
		// metodo para atualizar uma informação do usuario, tem como parametro um objeto do tipo User
		PreparedStatement st = null;
		try 
		{
			st = conn.prepareStatement(
					"UPDATE tb_User SET User_Name = ?, \r\n" + 
					"				    User_Login = ?,\r\n" + 
					"                   User_Password = ?,\r\n" + 
					"                   User_Category = ?\r\n" + 
					"WHERE User_Id = ? "); //script para executar a atualização
			//as linhas abaixo seta os valores, atraves dos metodos do objeto do parametro
			st.setString(1, obj.getName());
			st.setString(2, obj.getLogin());
			st.setString(3, obj.getPassword());
			st.setString(4, obj.getCategory());
			st.setInt(5, obj.getId());
			
			st.executeUpdate(); //executa o script
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
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

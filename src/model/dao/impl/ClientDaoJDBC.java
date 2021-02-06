package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.ClientDao;
import model.entities.Client;

public class ClientDaoJDBC implements ClientDao {
	// classe que implementa a interface ClientDao que disponibiliza os metodos de
	// acesso a dados dp cliente

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
	public Client findById(Integer id) 
	{
		// metodo para retornar um usuário pelo seu Id
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM tb_client WHERE Client_Id = ?"); // query a ser executado
			st.setInt(1, id); // aqui setamos a condição para a query acima

			rs = st.executeQuery(); // executa a query acima
			
			if (rs.next()) {
				// esse bloco vai instanciar um objeto com base no ResultSet obtido da query la
				// do banco de dados
				Client obj = instanciateClient(rs); // declarado um objeto tipo "Client"
				return obj; // retorna objeto
			}
			return null; // caso o ResultSet seja nulo retorna null, nem executa o bloco acima
		}
		
		catch (SQLException e) {
			throw new DbException(e.getMessage()); // lança uma exeção se acontecer algum erro no processo com banco de dados											// de dados
		}
		
		finally {
			DB.closeResultSet(rs); // fecha a conexão com o resultSet
			DB.closeStatement(st); // fecha a conexão com o Statement
		}
	}

	@Override
	public List<Client> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	private Client instanciateClient(ResultSet rs) throws SQLException {
		// Esse metodo sera usado para fazer a instanciação do client, visto que tem
		// muitos
		// sets para enviar a classe, esse metodo tem como parametro um ResultSet e
		// retona um
		// objeto client com todas as informações setadas
		Client obj = new Client();
		obj.setId(rs.getInt("Client_Id"));
		obj.setName(rs.getString("Client_Name"));
		obj.setAddress(rs.getString("Client_Address"));
		obj.setBairro(rs.getString("Client_Bairro"));
		obj.setCity(rs.getString("Client_City"));
		obj.setEstado(rs.getString("Client_Estado"));
		obj.setPhone(rs.getString("Client_Phone"));
		obj.setWhatsapp(rs.getString("Client_Whatsapp"));
		obj.setEmail(rs.getString("Client_Email"));
		return obj;
	}
}

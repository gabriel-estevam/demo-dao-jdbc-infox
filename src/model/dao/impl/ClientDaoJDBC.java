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
import db.DbIntegrityException;
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
	public void insert(Client obj) 
	{
		//metoodo para inserir um cliente, temo como parametro um objeto tipo client
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO tb_Client "
					+ " (Client_Name, Client_Address, Client_Bairro, Client_City, Client_Estado, Client_Phone, Client_Whatsapp, Client_Email) "  
					+" VALUES(?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS); //query para executar a inserção
		//e retorna a chave de registro (id) da iserção, sera gerado para cada registro que houver
			//as linhas abaixo seta as informações para a query
			st.setString(1, obj.getName());
			st.setString(2, obj.getAddress());
			st.setString(3, obj.getBairro());
			st.setString(4, obj.getCity());
			st.setString(5, obj.getEstado());
			st.setString(6, obj.getPhone());
			st.setString(7, obj.getWhatsapp());
			st.setString(8, obj.getEmail());
			
			int rowsAffected = st.executeUpdate(); //executa a inserção e guardar o resultado em uma variavel do tipo int
			
			if(rowsAffected > 0 ) {
			//executa esse bloco caso a variavel seja maior que zero, indicando que funcionou
				ResultSet rs = st.getGeneratedKeys(); //aqui pegamos a chave que foi gerada para inserção do registro
				if(rs.next()) {
				//esse bloco acessa a coluna do Id do registro 
					int id = rs.getInt(1); //salva o Id gerado em uma variavel do tipo int
					obj.setId(id); //seta o valor parao objeto, aqui é para o objeto porque no banco ja possui esse id, mas a classe, no atributo id, ainda não
				}
				DB.closeResultSet(rs);
			}
			else {
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
	public void update(Client obj) 
	{
		// Metodo para atulizar um registro (client) no banco de dados, tem como parametro um objeto do tipo Client
		PreparedStatement st = null;
		
		try 
		{
			st = conn.prepareStatement("UPDATE tb_client \r\n" + 
					"SET Client_Name = ?,\r\n" + 
					"	 Client_Address = ?,\r\n" + 
					"    Client_Bairro = ?,\r\n" + 
					"    Client_City = ?,\r\n" + 
					"    Client_Estado = ?,\r\n" + 
					"    Client_Phone = ?,\r\n" + 
					"    Client_Whatsapp = ?,\r\n" + 
					"    Client_Email = ?\r\n" + 
					"WHERE Client_Id = ?;\r\n");
			st.setString(1, obj.getName());
			st.setString(2, obj.getAddress());
			st.setString(3, obj.getBairro());
			st.setString(4, obj.getCity());
			st.setString(5, obj.getEstado());
			st.setString(6, obj.getPhone());
			st.setString(7, obj.getWhatsapp());
			st.setString(8, obj.getEmail());
			st.setInt(9, obj.getId());
			
			st.executeUpdate();
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) 
	{
		// metodo para deletar um registro (client) do banco de dados, tem como parametro um Integer
		PreparedStatement st = null;
		try 
		{
			st = conn.prepareStatement("DELETE FROM tb_client WHERE client_Id = ?");
			st.setInt(1, id);
			st.executeUpdate();
		} 
		catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
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
	public List<Client> findAll() 
	{
		// Metodo que retorna todos os clientes, vai retorna uma lista de clientes
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM tb_Client ORDER BY Client_Name"); //prepara a query que vai retornar todos os clientes
			rs = st.executeQuery(); //executa a query
			
			List<Client> list = new ArrayList<>(); //instanciado um lista do tipo client para armazenar e retorna os clientes
			
			while(rs.next()) {
			//esse bloco vai percorrer o ResultSet e para cada registro vai instanciar um objeto do tipo client e
			//adicionar na lista
				Client obj = instanciateClient(rs); //instancia um objeto para cada registro
				list.add(obj); //adiciona na lista o objeto
			}
			return list; // apos percorrer o ResultSet, retorna a lista com todos os objetos carregados
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
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

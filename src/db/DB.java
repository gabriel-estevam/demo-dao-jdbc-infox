package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB 
{
	private static Connection conn = null; //atributo para conex�o do banco de dados, sempre come�a como nula
	
	public static Connection getConnection()
	{
		/*Metodo que cria uma conex�o com o banco de dados, usando a API DriveManager
		 * Primeira vai verificar se a conex�o esta nula, caso n�o tenha sido estabelecida
		 * */
		if(conn == null)
		{
			try 
			{
				/*Declarado uma variavel do tipo Properties que recebe os dados de conex�o do banco de dados
				 * atraves do metodo axuliar LoadProperties(), na sequencia essa variavel chama a fun��o 
				 * getProperties e nela passamos como parametro a url do banco de dados(que esta dentro do arquivo
				 * db.properties), com isso � guardada na variavel do tipo String.
				 * chamamos a API DriverManager passando a url do banco, que � guardada no atributo "conn"
				 * 
				 * DETALHE INPORTANTE: Quando for chamar a API DriverManager.getConnecion devemos passar
				 * como parametro a url E o properties, CASO CONTRARIO VAI ESTOURA A EXCE��O com a seguinte
				 * mensagem: 
				 * Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
					Exception in thread "main" db.DbException: Access denied for user ''@'localhost' (using password: NO)
					at db.DB.getConnection(DB.java:39)
					at application.Program.main(Program.java:11)
				
					Esse erro acontece devido a idenfica��o com o banco de dados, por padr�o ele vai tentar validar o certificado
					SSL do banco (useSSL=true) nessa aplica��o, esta definiada para useSSL=false 
				 * */
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url,props);
			}
			catch(SQLException e)
			{
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	public static void closeResultSet(ResultSet rs)
	{
		//metodo para fechar uma conex�o com ResultSet
		if(rs != null)
		{
			try{
				rs.close();
			} 
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	public static void closeStatement(Statement st)
	{
		//Metodo para fechar uma conex�o com Statment
		if(st != null)
		{
			try {
				st.close();
			} 
			catch (SQLException e){
				throw new DbException(e.getMessage());
			}
		}
	}
	
	public static void closeConnection()
	{
		//metodo para fechar a conex�o com o banco de dados
		if(conn != null)
		{
			try {
				conn.close();
			} 
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	public static Properties loadProperties()
	{
		/*Fun��o auxliar para ler os dados de conex�o com o banco de dados,
		 * vai ler os dados do arquivo db.properties que esta nesse projeto, caso
		 * n�o encontre, ou dos dados estiver errado lan�a a exception personalizda
		 * no arquivo contem algumas informa��es como, url do banco, usuario e senha, e
		 * o uma permiss�o do sistema operacional para a leitura
		 */
		try(FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		}
		catch(IOException e) {
			throw new DbException(e.getMessage());
		}
	}
}

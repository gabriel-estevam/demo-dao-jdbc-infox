package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.OrdemServicoDao;
import model.entities.Client;
import model.entities.Employee;
import model.entities.OrdemServico;
import model.entities.Service;

public class OrdemServicoDaoJDBC implements OrdemServicoDao
{
	private Connection conn;
	
	public OrdemServicoDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(OrdemServico obj) 
	{
		PreparedStatement st = null;
		
		try 
		{
			st = conn.prepareStatement("INSERT INTO tb_ordem_servico(\r\n" + 
					"    Ordem_Inicial_Date,\r\n" + 
					"    Ordem_Final_Date,\r\n" + 
					"    Ordem_Equipamento,\r\n" + 
					"    Ordem_Defeito,\r\n" + 
					"    Ordem_Laudo,\r\n" + 
					"    Ordem_Status,\r\n" + 
					"    Employee_Id,\r\n" + 
					"    Client_Id,\r\n" + 
					"    Service_Id\r\n" + 
					") VALUES(?, ?, ?, ?, \r\n" + 
					"	    ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
			
			st.setDate(1, new java.sql.Date(obj.getDtIncial().getTime()));
			st.setDate(2, new java.sql.Date(obj.getDtFinal().getTime()));
			st.setString(3,obj.getEquipamento());
			st.setString(4, obj.getDefeito());
			st.setString(5, obj.getLaudo());
			st.setString(6, obj.getStatus());
			st.setInt(7, obj.getEmployeeId().getId());
			st.setInt(8, obj.getClientId().getId());
			st.setInt(9, obj.getServiceId().getId());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) 
			{
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpected error! No rows affected");
			}
		} 
		catch (SQLException e)
		{
			throw new DbException(e.getMessage());
		}
		finally 
		{
			DB.closeStatement(st);
		}
	}
	@Override
	public void update(OrdemServico obj) 
	{
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public OrdemServico findById(Integer id) 
	{
		// metodopara retorna uma ordem de serviço pelo seu id
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try 
		{
			st = conn.prepareStatement("SELECT os.Ordem_id,\r\n" + 
					"	   os.Ordem_Equipamento,\r\n" + 
					"       os.Ordem_Defeito,\r\n" + 
					"       os.Ordem_Inicial_Date,\r\n" + 
					"       os.Ordem_Final_Date,\r\n" + 
					"       os.Ordem_Laudo,\r\n" + 
					"       os.Ordem_Status,\r\n" + 
					"       cli.Client_Id,\r\n" + 
					"       cli.Client_Name,\r\n" + 
					"       svr.Service_Id,\r\n" + 
					"       svr.Service_Name,\r\n" + 
					"       svr.Service_Valor,\r\n" + 
					"       emp.Employee_Id,\r\n" + 
					"       emp.Employee_Name\r\n" + 
					"FROM tb_ordem_servico AS os\r\n" + 
					"INNER JOIN tb_client AS cli ON os.Client_Id = cli.Client_Id\r\n" + 
					"INNER JOIN tb_service AS svr ON os.Service_Id = svr.Service_Id\r\n" + 
					"INNER JOIN tb_employee AS emp ON os.Employee_Id = emp.Employee_Id\r\n" + 
					"WHERE os.Ordem_Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) 
			{
				Client client = instantiateClient(rs);
				Service service = instantiateService(rs);
				Employee employee = instantiateEmployee(rs);
				OrdemServico obj = instantiateOrdemServico(rs, employee, service, client);
				return obj;
			}
			
		return null;
		
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally 
		{
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<OrdemServico> findAll() 
	{
		PreparedStatement st = null;
		ResultSet rs = null;
		try 
		{
			st = conn.prepareStatement(
					"SELECT os.*,\r\n" + 
					"		cli.Client_Name,\r\n" + 
					"        svr.*,\r\n" +
					"		  svr.Service_Valor,\r\n"+
					"        emp.Employee_Name\r\n" + 
					"FROM tb_ordem_servico AS os\r\n" + 
					"INNER JOIN tb_client AS cli ON os.Client_Id = cli.Client_Id\r\n" + 
					"INNER JOIN tb_service AS svr ON os.Service_Id = svr.Service_Id\r\n" + 
					"INNER JOIN tb_employee AS emp ON os.Employee_Id = emp.Employee_Id\r\n" + 
					"ORDER BY os.Ordem_Equipamento;");
			rs = st.executeQuery();
			
			List<OrdemServico> list = new ArrayList<>(); //lista que sera retornada
			//estutura map que vamos usar para mapear as classe instanciada
			Map<Integer, Client> mapClient = new HashMap<>();
			Map<Integer, Service> mapService = new HashMap<>();
			Map<Integer, Employee> mapEmployee = new HashMap<>();
			
			while(rs.next()) 
			{
				Client cli = mapClient.get(rs.getInt("Client_Id"));
				Service srv = mapService.get(rs.getInt("Service_Id"));
				Employee emp = mapEmployee.get(rs.getInt("Employee_Id"));
		/*na estrutura abaixo estamos testando se ja existe uma classe ja instanciada com
		 * mesmo id, com isso economizamos instancia no heap da memoria*/	
				if(cli == null) {
					cli = instantiateClient(rs);
					mapClient.put(rs.getInt("Client_Id"), cli);
				}
				 if(srv == null) {
					srv = instantiateService(rs);
					mapService.put(rs.getInt("Service_Id"), srv);
				}
				if(emp == null) {
					emp = instantiateEmployee(rs);
					mapEmployee.put(rs.getInt("Employee_Id"), emp);
				}
				
				OrdemServico os = instantiateOrdemServico(rs, emp, srv, cli);
				list.add(os); 
			}
			return list;
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		finally 
		{
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<OrdemServico> findByEmployeeId(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrdemServico> findByClientId(Client client) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrdemServico> findByServiceId(Service service) {
		// TODO Auto-generated method stub
		return null;
	} 


	private OrdemServico instantiateOrdemServico(ResultSet rs, 
			Employee employee, Service service, Client client) throws SQLException 
	{
		OrdemServico obj = new OrdemServico();
		obj.setId(rs.getInt("Ordem_id"));
		obj.setEquipamento(rs.getString("Ordem_Equipamento"));
		obj.setDefeito(rs.getString("Ordem_Defeito"));
		obj.setDtIncial(rs.getDate("Ordem_Inicial_Date"));
		obj.setDtFinal(rs.getDate("Ordem_Final_Date"));
		obj.setLaudo(rs.getString("Ordem_Laudo"));
		obj.setStatus(rs.getString("Ordem_Status"));
		obj.setEmployeeId(employee);
		obj.setServiceId(service);
		obj.setClientId(client);
		return obj;
	}

	private Employee instantiateEmployee(ResultSet rs) throws SQLException 
	{
		Employee employee = new Employee();
		employee.setId(rs.getInt("Employee_Id"));
		employee.setName(rs.getString("Employee_Name"));
		return employee;
	}

	private Service instantiateService(ResultSet rs) throws SQLException 
	{
		Service service = new Service();
		service.setId(rs.getInt("service_Id"));
		service.setName(rs.getString("Service_Name"));
		service.setValor(rs.getDouble("Service_Valor"));
		return service;
	}

	private Client instantiateClient(ResultSet rs) throws SQLException 
	{
		Client client = new Client();
		client.setId(rs.getInt("Client_Id"));
		client.setName(rs.getString("Client_Name"));
		return client;
	}
	
}

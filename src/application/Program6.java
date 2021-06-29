package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.OrdemServicoDao;
import model.entities.Client;
import model.entities.Department;
import model.entities.Employee;
import model.entities.OrdemServico;
import model.entities.Service;

public class Program6 {

	public static void main(String[] args)
	{
		OrdemServicoDao obj = DaoFactory.createOrdemServicoDao();
		Department dep = new Department(2, null);
		Employee emp = new Employee(3, "Alex grey", "alex@gmail.com",new Date(),null, 2000.00, dep);
		Service sc = new Service(7, null, null, null);
		Client client = new Client(2,null , null, null, null, null, null, null, null);
		
		OrdemServico os = new OrdemServico(null, new Date(), new Date(),"PC Gamer INTEL CORE 9 6 TG RAM","Quebrou a mangueira do cooler","Trocado a mangueira do cooler","Concluida",emp,client,sc);
		
		obj.insert(os);
		
		System.out.println("\n=== Test 4 Insert new OS ===");
		System.out.println("Inserted! New id = " + os.getId());
	}

}
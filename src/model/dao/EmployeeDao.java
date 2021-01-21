package model.dao;

import java.util.List;

import model.entities.Employee;

public interface EmployeeDao
{
	//interface resonsavel pelo acesso ao dados do Employee, contem todos os metodos que fazem acesso ao banco de dados
	void insert(Employee obj); //metodo responsavel pela inserção no banco de dados, temo como parametro um obj do tipo Employee
	void update(Employee obj); //metodo resposnavel pela atualização no banco de dados, tem como parametro um obj do tipo Employee
	void deleteById(Integer id); //metodo responsavel por deletar um registro do banco pelo seu id, tem como  parametro um integer
	Employee findById(Integer id); //metodo responsavel por retorna um registro pelo seu id, tem como parametro um integer
	//Detalhe: Aquilo que conter byId tem como como parametro um inteiro para esse metodo
	List<Employee> findAll(); //metodo responsavel por trazer todos os funcionarios do banco de dados,
	//Detalhe: Aquilo que contem all, temos que armazenar em uma lista (list)
	List<Employee> findByDepartmentId(Integer id);
}

package model.dao;

import java.util.List;

import model.entities.Department;

public interface DepartmentDao
{
	//interface resonsavel pelo acesso ao dados do department, contem todos os metodos que fazem acesso ao banco de dados
	void insert(Department obj); //metodo responsavel pela inserção no banco de dados, temo como parametro um obj do tipo department
	void update(Department obj); //metodo resposnavel pela atualização no banco de dados, tem como parametro um obj do tipo department
	void deleteById(Integer id); //metodo responsavel por deletar um registro do banco pelo seu id, tem como  parametro um integer
	Department findById(Integer id); //metodo responsavel por retorna um registro pelo seu id, tem como parametro um integer
	//Detalhe: Aquilo que conter byId tem como como parametro um inteiro para esse metodo
	List<Department> findAll(); //metodo responsavel por trazer todos os departamentos do banco de dados,
	//Detalhe: Aquilo que contem all, temos que armazenar em uma lista (list)
}

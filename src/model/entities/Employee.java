package model.entities;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String cpf;
	private Date BirthDate;
	private String Email;
	private Double BaseSalary;
	
	private Department departmentId;
	
	public Employee() {
		
	}

	public Employee(Integer id, String name, String cpf, Date birthDate, String email, Double baseSalary, Department departmentId) 
	{
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		BirthDate = birthDate;
		Email = email;
		BaseSalary = baseSalary;
		this.departmentId = departmentId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getBirthDate() {
		return BirthDate;
	}

	public void setBirthDate(Date birthDate) {
		BirthDate = birthDate;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public Double getBaseSalary() {
		return BaseSalary;
	}

	public void setBaseSalary(Double baseSalary) {
		BaseSalary = baseSalary;
	}

	public Department getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Department departmentId) {
		this.departmentId = departmentId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", cpf=" + cpf + ", BirthDate=" + BirthDate + ", Email="
				+ Email + ", BaseSalary=" + BaseSalary + ", departmentId=" + departmentId + "]";
	}
	
	
}

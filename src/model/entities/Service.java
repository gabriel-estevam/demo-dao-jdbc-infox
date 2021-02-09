package model.entities;

import java.io.Serializable;

//classe representa o serviço a ser prestado, no sistema o usuario 
//tera que cadastrar o serviço e seu valor de mão de obra
public class Service implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String descricao;
	private Double valor;
	
	public Service() {
		
	}

	public Service(Integer id, String name, String descricao, Double valor) {
		super();
		this.id = id;
		this.name = name;
		this.descricao = descricao;
		this.valor = valor;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
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
		Service other = (Service) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Service [id=" + id + ", name=" + name + ", descricao=" + descricao + ", valor=" + valor + "]";
	}
	
}

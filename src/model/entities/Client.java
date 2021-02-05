package model.entities;

import java.io.Serializable;

public class Client implements Serializable 
{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String address;
	private String bairro;
	private String city;
	private String estado;
	private String phone;
	private String whatsapp;
	private String email;
	
	public Client() {
		
	}

	public Client(Integer id, String name, String address, String bairro, String city, String estado, String phone,
			String whatsapp, String email) 
	{
		
		this.id = id;
		this.name = name;
		this.address = address;
		this.bairro = bairro;
		this.city = city;
		this.estado = estado;
		this.phone = phone;
		this.whatsapp = whatsapp;
		this.email = email;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		Client other = (Client) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", address=" + address + ", bairro=" + bairro + ", city=" + city
				+ ", estado=" + estado + ", phone=" + phone + ", whatsapp=" + whatsapp + ", email=" + email + "]";
	}
	
	
}

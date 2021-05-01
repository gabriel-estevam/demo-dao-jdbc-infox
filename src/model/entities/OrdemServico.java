package model.entities;

import java.io.Serializable;
import java.util.Date;

public class OrdemServico implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date dtIncial;
	private Date dtFinal;
	private String equipamento;
	private String defeito;
	private String laudo;
	private String status;
	
	private Employee employeeId;
	private Client clientId;
	private Service serviceId;
	
	
	public OrdemServico() {
		
	}


	public OrdemServico(Integer id, Date dtIncial, Date dtFinal, String equipamento, String defeito, String laudo,
			String status, Employee employeeId, Client clientId, Service serviceId) 
	{
		this.id = id;
		this.dtIncial = dtIncial;
		this.dtFinal = dtFinal;
		this.equipamento = equipamento;
		this.defeito = defeito;
		this.laudo = laudo;
		this.status = status;
		this.employeeId = employeeId;
		this.clientId = clientId;
		this.serviceId = serviceId;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Date getDtIncial() {
		return dtIncial;
	}


	public void setDtIncial(Date dtIncial) {
		this.dtIncial = dtIncial;
	}


	public Date getDtFinal() {
		return dtFinal;
	}


	public void setDtFinal(Date dtFinal) {
		this.dtFinal = dtFinal;
	}


	public String getEquipamento() {
		return equipamento;
	}


	public void setEquipamento(String equipamento) {
		this.equipamento = equipamento;
	}


	public String getDefeito() {
		return defeito;
	}


	public void setDefeito(String defeito) {
		this.defeito = defeito;
	}


	public String getLaudo() {
		return laudo;
	}


	public void setLaudo(String laudo) {
		this.laudo = laudo;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Employee getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(Employee employeeId) {
		this.employeeId = employeeId;
	}


	public Client getClientId() {
		return clientId;
	}


	public void setClientId(Client clientId) {
		this.clientId = clientId;
	}


	public Service getServiceId() {
		return serviceId;
	}


	public void setServiceId(Service serviceId) {
		this.serviceId = serviceId;
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
		OrdemServico other = (OrdemServico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "OrdemServico [id=" + id + ", dtIncial=" + dtIncial + ", dtFinal=" + dtFinal + ", equipamento="
				+ equipamento + ", defeito=" + defeito + ", laudo=" + laudo + ", status=" + status + ", employeeId="
				+ employeeId + ", clientId=" + clientId + ", serviceId=" + serviceId + "]";
	}
	
}

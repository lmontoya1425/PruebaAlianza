package com.pruebaalianza.co.dto;

import java.io.Serializable;
import java.util.Date;

public class ClientDTO implements Serializable  {

	private static final long serialVersionUID = 1L;

	private String sharedKey;
	private String businessId;
	private String email;
	private String phone;
	private Date dataAdded;

	public ClientDTO() {
		super();
	}

	public String getSharedKey() {
		return sharedKey;
	}

	public void setSharedKey(String sharedKey) {
		this.sharedKey = sharedKey;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getDataAdded() {
		return dataAdded;
	}

	public void setDataAdded(Date dataAdded) {
		this.dataAdded = dataAdded;
	}
	

	
}

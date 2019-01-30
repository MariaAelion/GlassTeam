package com.proxair.dto;

import com.proxair.persistence.entity.Client;

public class DtoClientMail {

	private String mail;
	
	public DtoClientMail(Client c) {
		this.setMail(c.getMail());		
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
}

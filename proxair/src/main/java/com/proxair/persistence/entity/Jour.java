package com.proxair.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_jour")
public class Jour {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;

	@Column(name = "jourOuvrable", nullable = false)
	private Boolean jourOuvrable;

	@Column(name = "jour", nullable = false)
	private Date jour;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Boolean getJourOuvrable() {
		return jourOuvrable;
	}

	public void setJourOuvrable(Boolean jourOuvrable) {
		this.jourOuvrable = jourOuvrable;
	}

	public Date getJour() {
		return jour;
	}

	public void setJour(Date jour) {
		this.jour = jour;
	}
}

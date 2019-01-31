package com.proxair.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_prixGeneration")
public class PrixGeneration {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name ="id", unique = true, nullable = false)
	private long id;
	
	@Column (name ="prix_de_reference", nullable = false)
	private Double prixDeReference;
	
	@Column (name ="tva_de_reference", nullable = false)
	private Double tvaDeReference;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Double getPrixDeReference() {
		return prixDeReference;
	}

	public void setPrixDeReference(Double prixDeReference) {
		this.prixDeReference = prixDeReference;
	}

	public Double getTvaDeReference() {
		return tvaDeReference;
	}

	public void setTvaDeReference(Double tvaDeReference) {
		this.tvaDeReference = tvaDeReference;
	}	
}

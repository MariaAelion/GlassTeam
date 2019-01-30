package com.proxair.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxair.persistence.entity.Jour;
import com.proxair.persistence.entity.Trajet;
import com.proxair.persistence.entity.Vehicule;
import com.proxair.persistence.repository.JourRepository;
import com.proxair.persistence.repository.TrajetRepository;
import com.proxair.persistence.repository.VehiculeRepository;
import com.proxair.service.IAdminService;

@Service
@Transactional
public class AdminService implements IAdminService {
	
	@Autowired TrajetRepository trajetRepository;
	@Autowired VehiculeRepository vehiculeRepository;
	@Autowired JourRepository jourRepository;
	public Trajet addTrajet(Trajet trajet) {
		return trajetRepository.save(trajet);
	}
	
	public Vehicule addVehicule(Vehicule vehicule) {
		return vehiculeRepository.save(vehicule);
	}
	
	public Jour addJour(Jour jour) {
		return jourRepository.save(jour);
	}
}
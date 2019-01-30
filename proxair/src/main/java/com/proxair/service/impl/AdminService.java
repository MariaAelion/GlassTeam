package com.proxair.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxair.persistence.entity.Trajet;
import com.proxair.persistence.repository.TrajetRepository;
import com.proxair.service.IAdminService;

@Service
@Transactional
public class AdminService implements IAdminService {
	
	@Autowired TrajetRepository trajetRepository;
	public Trajet addTrajet(Trajet trajet) {
		return trajetRepository.save(trajet);
	}
}
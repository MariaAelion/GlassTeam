package com.proxair.service.impl;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxair.dto.DtoCreationTrajet;
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

	@Override
	public DtoCreationTrajet createtravel(DtoCreationTrajet dtotrajet) {
		// Cree le trajet dans la base de donnée
		Trajet trajet = new Trajet();
		trajet.setDate(dtotrajet.getDate());
		// TODO conditionner la visibilité du trajet
		trajet.setEtatReservation("disponible");
		trajet.setEtatTrajet("attente");
		trajet.setHeureDepart(dtotrajet.getHeureDepart());
		trajet.setNbPlacesDispo(dtotrajet.getNbPlacesTotal());
		trajet.setNbPlacesTotal(dtotrajet.getNbPlacesTotal());
		// Recupere le trajet dans la base de donnée
		DtoCreationTrajet dtoCreationTrajet = new DtoCreationTrajet();
		Trajet trajet2 = new Trajet();
		trajet2 = trajetRepository.save(trajet);
		dtoCreationTrajet.setDate(trajet2.getDate());
		dtoCreationTrajet.setHeureDepart(trajet2.getHeureDepart());
		dtoCreationTrajet.setNbPlacesTotal(trajet2.getNbPlacesTotal());

		return dtoCreationTrajet;
	}
}
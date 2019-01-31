package com.proxair.service.impl;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxair.dto.DtoCreationTrajet;
import com.proxair.dto.DtoTournee;
import com.proxair.exception.NotFoundException;
import com.proxair.persistence.entity.Tournee;
import com.proxair.persistence.entity.Trajet;
import com.proxair.persistence.repository.TourneeRepository;
import com.proxair.persistence.repository.TrajetRepository;
import com.proxair.service.IAdminService;
import com.proxair.service.ITrajetService;

@Service
@Transactional
public class AdminService implements IAdminService {
	
	@Autowired TrajetRepository trajetRepository;
	@Autowired TourneeRepository tourneeRepository;
	@Autowired ITrajetService trajetService;

	public Trajet addTrajet(Trajet trajet) {
		return trajetRepository.save(trajet);
	}

	@Override
	public DtoCreationTrajet createtravel(DtoCreationTrajet dtotrajet) {
		
		if (checkRideBetween3(dtotrajet.getDate(),dtotrajet.getHeureDepart())){

			// Cree le trajet dans la base de donnée
			Trajet trajet = new Trajet();
			trajet.setDate(dtotrajet.getDate());
			if (trajetService.checkDate15(dtotrajet.getDate())) {
				trajet.setEtatReservation("disponible");	
			}
			else trajet.setEtatReservation("indisponible");
			trajet.setEtatTrajet("attente");
			trajet.setHeureDepart(dtotrajet.getHeureDepart());
			trajet.setNbPlacesDispo(dtotrajet.getNbPlacesTotal());
			trajet.setNbPlacesTotal(dtotrajet.getNbPlacesTotal());

			// Recupere le trajet dans la base de donnée
			DtoCreationTrajet dtoCreationTrajet = new DtoCreationTrajet();
			Trajet trajet2 = trajetRepository.save(trajet);
			dtoCreationTrajet.setDate(trajet2.getDate());
			dtoCreationTrajet.setHeureDepart(trajet2.getHeureDepart());
			dtoCreationTrajet.setNbPlacesTotal(trajet2.getNbPlacesTotal());
			
			return dtoCreationTrajet;

		} else throw new NotFoundException(" Vous ne pouvez pas creer un trajet qui a un depart 3h avant ou apres");
	}
	
	public boolean checkRideBetween3(Date date, Time time) {
		LocalTime time0 = time.toLocalTime();
		Time time1 = Time.valueOf(time0.minusHours(3));
		Time time2 = Time.valueOf(time0.plusHours(3));
		if (trajetRepository.findRidesBetween(date, time1, time2).isEmpty()) return true;
		else return false;
	}

	public String addTrip(DtoTournee dtoTournee) {
		if (checkTripBetween3(dtoTournee.getHeureTournee()))
		{ 
			Tournee tournee = new Tournee();
			tournee.setHeureTournee(dtoTournee.getHeureTournee());
			tourneeRepository.save(tournee);
		}
		else throw new NotFoundException(" Vous ne pouvez pas créer une tournee qui a un depart 3h avant ou apres");
			return " La tournée a été enregistrée";
	}
	
	public boolean checkTripBetween3(Time time) {
		LocalTime time0 = time.toLocalTime();
		Time time1 = Time.valueOf(time0.minusHours(3));
		Time time2 = Time.valueOf(time0.plusHours(3));
		if (tourneeRepository.findTripBetween(time1, time2).isEmpty()) return true;
		else return false;
	}
}
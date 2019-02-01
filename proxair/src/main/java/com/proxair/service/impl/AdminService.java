package com.proxair.service.impl;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxair.calculs.CalculsDates;
import com.proxair.dto.DtoCreationPrixRef;
import com.proxair.dto.DtoCreationTrajet;
import com.proxair.dto.DtoTournee;
import com.proxair.dto.DtoGenerate;
import com.proxair.exception.NotFoundException;
import com.proxair.persistence.entity.PrixGeneration;
import com.proxair.persistence.entity.Tournee;
import com.proxair.persistence.entity.Trajet;
import com.proxair.persistence.repository.PrixGenerationRepository;
import com.proxair.persistence.repository.TourneeRepository;
import com.proxair.persistence.repository.TrajetRepository;
import com.proxair.service.IAdminService;
import com.proxair.service.ITrajetService;

@Service
@Transactional
public class AdminService implements IAdminService {
	
	@Autowired TrajetRepository trajetRepository;
	@Autowired ITrajetService trajetService;
	@Autowired PrixGenerationRepository prixGenerationRepository;
	@Autowired TourneeRepository tourneeRepository;

	public Trajet addTrajet(Trajet trajet) {
		return trajetRepository.save(trajet);
	}

	@Override
	public DtoCreationTrajet createtravel(DtoCreationTrajet dtotrajet) {
		
		if (checkRideBetween3(dtotrajet.getDate(),dtotrajet.getHeureDepart())){

			// Cree le trajet dans la base de donnée
			Trajet trajet = new Trajet();
			trajet.setDate(dtotrajet.getDate());
			if (checkDate15(dtotrajet.getDate())) {
				trajet.setEtatReservation("disponible");	
			}
			else trajet.setEtatReservation("indisponible");
			trajet.setEtatTrajet("attente");
			trajet.setHeureDepart(dtotrajet.getHeureDepart());
			trajet.setNbPlacesDispo(dtotrajet.getNbPlacesTotal());
			trajet.setNbPlacesTotal(dtotrajet.getNbPlacesTotal());
			
			if (prixGenerationRepository.getPrixGeneration().isPresent()) {
				trajet.setPrix_place(prixGenerationRepository.getPrixGeneration().get().getPrixDeReference());
				trajet.setTva(prixGenerationRepository.getPrixGeneration().get().getTvaDeReference());		
			}else {
				throw new NotFoundException(" Vous devez d'abord entrer un prix de reference !!!!!");
			}
			

			// Recupere le trajet dans la base de donnée
			DtoCreationTrajet dtoCreationTrajet = new DtoCreationTrajet();
			Trajet trajet2 = new Trajet();
			trajet2 = trajetRepository.save(trajet);
			dtoCreationTrajet.setDate(trajet2.getDate());
			dtoCreationTrajet.setHeureDepart(trajet2.getHeureDepart());
			dtoCreationTrajet.setNbPlacesTotal(trajet2.getNbPlacesTotal());
			


			return dtoCreationTrajet;

		}else throw new NotFoundException(" Vous ne pouvez pas creer un trajet qui a un depart 3h avant ou apres");
		


	}
	
	public boolean checkRideBetween3(Date date, Time time) {
		LocalTime time0 = time.toLocalTime();
		Time time1 = Time.valueOf(time0.minusHours(3));
		Time time2 = Time.valueOf(time0.plusHours(3));
		if (trajetRepository.findRidesBetween(date, time1, time2).isEmpty()) return true;
		else return false;
	}

	
	
	
	public void UpdateVisibility() {
		List<Trajet> trajets = trajetRepository.findRidesByDate(getDate15());
		trajets.forEach(a -> a.setEtatReservation("disponible"));
		trajetRepository.saveAll(trajets);
		}
	
	
	
	//Verifie si la date est dans les 15 prochains jours
	@Override
	public boolean checkDate15(Date date) {
		 Calendar cal15 = Calendar.getInstance();
	       Long millis = System.currentTimeMillis();
	       
	       Date date15 = new Date(millis);
	       	cal15.setTime(date15);
		    cal15.add(Calendar.DATE, 15);
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(date);
		    
		    if (cal.getTime().before(cal15.getTime())) {
		    	return true;
		    }
		    else return false;
	}
	 
	
	//donne la date dans 15 jours
	public Date getDate15() {
		Calendar cal15 = Calendar.getInstance();
	       Long millis = System.currentTimeMillis();
	       Date date15 = new Date(millis);
	       	cal15.setTime(date15);
		    cal15.add(Calendar.DATE, 15);
		    
		    Date date = new Date(cal15.getTimeInMillis());
		    
		return date;
	}

	/////////// Generation des Trajets ////////////
	
	public String GenerateTripFromNow(DtoGenerate dtoGenerate) {
		List<DtoTournee> dtoTournees = listTrip();
		if (!dtoTournees.isEmpty()) {
			System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + dtoGenerate.getNombreDeJours());
			if(dtoGenerate.getNombreDeJours() >0) {
				Date date = new Date(System.currentTimeMillis());
				if(dtoGenerate.getDate().after(date)) {
					int i = 0;
					DtoCreationTrajet dtotrajet = new DtoCreationTrajet();
					dtotrajet.setNbPlacesTotal(dtoGenerate.getNombreDePlaces());
					LocalDate localDate = dtoGenerate.getDate().toLocalDate();

					for (i = 0; i < dtoGenerate.getNombreDeJours(); i++) {
						
						if (!CalculsDates.isferie(localDate)&&!CalculsDates.isweekEnd(localDate)) {
							date = Date.valueOf(localDate);
							dtotrajet.setDate(date);
							//
							for (DtoTournee dtoto : dtoTournees) 
							{
								dtotrajet.setHeureDepart(dtoto.getHeureTournee());
								createtravel(dtotrajet);
							}
						}
						localDate = localDate.plusDays(1);
					}
				}
				else throw new NotFoundException(" Vous devez generer une date ulterieure a la date actuelle");	
			}
			else throw new NotFoundException(" Le nombre de jour doit être superieur à 0");			
		}
		else throw new NotFoundException(" Vous ne pouvez pas generer de trajets avec un emploi du temps vide");

		return "Tournee generee ";
	}
	
	/////////////// Tournees //////////////

	public List<DtoTournee> listTrip() {
		List<Tournee> tournees = tourneeRepository.findAllOrdered();
		
		return tournees.stream()
				.map(t -> new DtoTournee(t))
				.collect(Collectors.toList());
	}

	@Override
	public String addTrip(DtoTournee dtoTournee) {
		
		if (checkTripBetween3(dtoTournee.getHeureTournee()))
		{ 
			Tournee tournee = new Tournee();
			tournee.setHeureTournee(dtoTournee.getHeureTournee());
			tourneeRepository.save(tournee);
		}
		else throw new NotFoundException(" Vous ne pouvez pas creer une tournee qui a un depart 3h avant ou apres");
		
		
		// TODO Auto-generated method stub
		return " La tournee a ete enregistree";
	}
	
	public boolean checkTripBetween3(Time time) {
		LocalTime time0 = time.toLocalTime();
		Time time1 = Time.valueOf(time0.minusHours(3));
		Time time2 = Time.valueOf(time0.plusHours(3));
		if (tourneeRepository.findTripBetween(time1, time2).isEmpty()) return true;
		else return false;
	}

	@Override
	public boolean createPriceRef(DtoCreationPrixRef dtoCreationPrixRef) {
		
		PrixGeneration prixgeneration = new PrixGeneration();
		prixgeneration.setId(1);
		prixgeneration.setPrixDeReference(dtoCreationPrixRef.getPrixRef());
		prixgeneration.setTvaDeReference(dtoCreationPrixRef.getTvaRef());
		prixGenerationRepository.save(prixgeneration);
		return prixGenerationRepository.getPrixGeneration().isPresent();
		
	}
}
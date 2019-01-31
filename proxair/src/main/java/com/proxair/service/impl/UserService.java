package com.proxair.service.impl;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.proxair.dto.DtoTrajet;
import com.proxair.exception.NotFoundException;
import com.proxair.persistence.entity.Trajet;
import com.proxair.persistence.repository.TrajetRepository;
import com.proxair.service.IUserService;

@Service
@Transactional
public class UserService implements IUserService {
	
	@Autowired TrajetRepository trajetRepository;
	
	public List<DtoTrajet> findRides(Date date) {
		List<Trajet> trajets = trajetRepository.findRidesByDate(date);
		
		return trajets.stream()
				.map(trajet -> new DtoTrajet(trajet))
				.collect(Collectors.toList());
		}
	
<<<<<<< HEAD
	//methode pour change statut de trajet en valide dans le repo
	@Override
	public void valideTrajet(long id) {
		trajetRepository.valideTrajet(id);
}


		
	}
=======
	
	
	public void Registerreservation() {}
	
	
	
	
	public boolean CheckifReservationIsPossible(Date date, Time time, int nbPlaceAReserver) {
		
		Optional<Trajet> opt = trajetRepository.findRide(date,time);
		if (opt.isPresent()) {
			if(opt.get().getNbPlacesDispo() >= nbPlaceAReserver) {
				return true;
			} else return false;
		
		} else {
			throw new NotFoundException(" Le trajet qui part le" + date.toString() + " à " + time.toString() +" n'a pas été trouvé !");
		}
	}
	
	public boolean CheckifReservationIsPossible(int idTrajet, int nbPlaceAReserver) {
		
		Optional<Trajet> opt = trajetRepository.findRide(idTrajet);
		if (opt.isPresent()) {
			if(opt.get().getNbPlacesDispo() >= nbPlaceAReserver) {
				return true;
			} else return false;
		
		} else {
			throw new NotFoundException(" Le trajet " + idTrajet + " n'a pas été trouvé !");
		}
	}
	
}
>>>>>>> refs/remotes/origin/master

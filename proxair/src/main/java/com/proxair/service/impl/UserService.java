package com.proxair.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxair.dto.DtoTrajet;
import com.proxair.persistence.entity.Trajet;
import com.proxair.persistence.repository.ReservationRepository;
import com.proxair.persistence.repository.TrajetRepository;
import com.proxair.service.IUserService;

@Service
@Transactional
public class UserService implements IUserService {
	
	@Autowired TrajetRepository trajetRepository;
	@Autowired ReservationRepository reservationRepository;
	
	public List<DtoTrajet> findRides(Date date) {
		List<Trajet> trajets = trajetRepository.findRidesByDate(date);
		
		return trajets.stream()
				.map(trajet -> new DtoTrajet(trajet))
				.collect(Collectors.toList());
		}

	@Override
	public void cancelResa(long id) {
		reservationRepository.cancelReservation(id);	
	}

	@Override
	public int availabilitySeat(long idTrajet) {
		return trajetRepository.findById(idTrajet).get().getNbPlacesDispo();
		
	}
}
	
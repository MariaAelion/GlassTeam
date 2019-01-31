package com.proxair.service;

import java.util.Date;
import java.util.List;

import javax.mail.internet.AddressException;

import com.proxair.dto.DtoReservationPlaces;
import com.proxair.dto.DtoTrajet;

public interface IUserService {
	List<DtoTrajet> findRides(Date date);

	DtoReservationPlaces chooseSeats(long idTrajet, int nbrePlaces);

	void cancelResa(long id);

	void saveReservation(String mail, DtoReservationPlaces drp) throws AddressException;
}

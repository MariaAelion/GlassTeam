package com.proxair.service;
import java.sql.Date;
import java.util.List;
import javax.mail.internet.AddressException;
import com.proxair.dto.DtoReservationPlaces;
import com.proxair.dto.DtoDemandedePaiement;
import com.proxair.dto.DtoTrajet;
import com.proxair.dto.DtodemandeAchat;

public interface IUserService {
	public List<DtoTrajet> findRides(Date date);

	public DtoReservationPlaces chooseSeats(long idTrajet, int nbrePlaces);

	public void cancelResa(long id);

	public void saveReservation(String mail, DtoReservationPlaces drp) throws AddressException;

	public DtoDemandedePaiement askReservationFromUser(DtodemandeAchat dtodemandeAchat);

}

package com.proxair.service;

import java.sql.Date;

public interface ITrajetService {
	
	void UpdateVisibility();

	Date getDate15();

	boolean checkDate15(Date date);

	void updateNbPlacesTrajet(long idTrajet);

	void updateEtatReservation(long idTrajet);

}

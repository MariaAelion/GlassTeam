package com.proxair.service;


import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.proxair.dto.DtoDemandedePaiement;
import com.proxair.dto.DtoTrajet;
import com.proxair.dto.DtodemandeAchat;

public interface IUserService {
	List<DtoTrajet> findRides(Date date);
	public DtoDemandedePaiement askReservationFromUser(DtodemandeAchat dtodemandeAchat);
}

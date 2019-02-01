package com.proxair.service;

import java.sql.Date;
import java.util.List;

import com.proxair.dto.DtoCreationPrixRef;
import com.proxair.dto.DtoCreationTrajet;
import com.proxair.dto.DtoGenerate;
import com.proxair.dto.DtoTournee;
import com.proxair.persistence.entity.Trajet;

public interface IAdminService {

	Trajet addTrajet(Trajet trajet);

	DtoCreationTrajet createtravel(DtoCreationTrajet dtotrajet);
	
	public boolean checkDate15(Date date);

	boolean createPriceRef(DtoCreationPrixRef dtoCreationPrixRef);
	
	public void UpdateVisibility();

	String addTrip(DtoTournee dtoTournee);
	
	List<DtoTournee> listTrip();
	
	public String GenerateTripFromNow(DtoGenerate dtoGenerate);
}

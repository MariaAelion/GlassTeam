package com.proxair.service;

import com.proxair.dto.DtoCreationTrajet;
import com.proxair.persistence.entity.Trajet;

public interface IAdminService {

	Trajet addTrajet(Trajet trajet);

	DtoCreationTrajet createtravel(DtoCreationTrajet dtotrajet);
}

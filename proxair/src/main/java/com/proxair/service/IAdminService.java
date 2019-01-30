package com.proxair.service;

import com.proxair.persistence.entity.Jour;
import com.proxair.persistence.entity.Trajet;
import com.proxair.persistence.entity.Vehicule;

public interface IAdminService {

	Trajet addTrajet(Trajet trajet);

	Vehicule addVehicule(Vehicule vehicule);

	Jour addJour(Jour jour);
}

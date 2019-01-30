package com.proxair.service;

import java.util.Date;
import java.util.List;

import com.proxair.dto.DtoTrajet;

public interface IUserService {
	List<DtoTrajet> findRides(Date date);

	void cancelTrajet(long id);
	
	void cancelResa(long id);
}

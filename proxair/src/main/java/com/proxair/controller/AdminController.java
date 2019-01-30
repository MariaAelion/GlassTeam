package com.proxair.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.proxair.dto.DtoTrajet;
import com.proxair.persistence.entity.Jour;
import com.proxair.persistence.entity.Trajet;
import com.proxair.persistence.entity.Vehicule;
import com.proxair.service.IAdminService;
import com.proxair.service.IUserService;

@RestController
@RequestMapping(value="/api/admin")
public class AdminController {

	@Autowired IUserService userService;
	@Autowired IAdminService adminService;


	@GetMapping(value="/trajets")
	@ResponseBody
	public List<DtoTrajet> findRides(@RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
		return userService.findRides(date);
	}
	
	@PostMapping(value="/trajets")
	@ResponseBody
	@ResponseStatus(code=HttpStatus.CREATED)
	public Trajet addTrajet(@RequestBody Trajet trajet) {
		return  adminService.addTrajet(trajet);
	}
	
	@PostMapping(value="/vehicules")
	@ResponseBody
	@ResponseStatus(code=HttpStatus.CREATED)
	public Vehicule addVehicule(@RequestBody Vehicule vehicule) {
		return  adminService.addVehicule(vehicule);
	}
	
	@PostMapping(value="/jours")
	@ResponseBody
	@ResponseStatus(code=HttpStatus.CREATED)
	public Jour addJour(@RequestBody Jour jour) {
		return  adminService.addJour(jour);
	}
}
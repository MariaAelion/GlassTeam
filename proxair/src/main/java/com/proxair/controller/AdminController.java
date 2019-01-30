package com.proxair.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.proxair.dto.DtoCreationTrajet;
import com.proxair.persistence.entity.Trajet;
import com.proxair.service.IAdminService;

@RestController
@RequestMapping(value="/api/admin")
public class AdminController {

	@Autowired IAdminService adminService;
	
	@PostMapping(value="/trajets")
	@ResponseBody
	@ResponseStatus(code=HttpStatus.CREATED)
	public Trajet addTrajet(@RequestBody Trajet trajet) {
		return  adminService.addTrajet(trajet);
	}
	
	
	@PostMapping(value="/createtravel")
	@ResponseBody
	@ResponseStatus(code=HttpStatus.CREATED)
	public DtoCreationTrajet  addTrajet2(@RequestBody DtoCreationTrajet  dtotrajet) {
		
		return  adminService.createtravel(dtotrajet);
	}
	
	
}
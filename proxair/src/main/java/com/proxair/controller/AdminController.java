package com.proxair.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.proxair.dto.DtoCreationPrixRef;
import com.proxair.dto.DtoCreationTrajet;
import com.proxair.dto.DtoGenerate;
import com.proxair.dto.DtoTournee;
import com.proxair.persistence.entity.Trajet;
import com.proxair.service.IAdminService;

@RestController
@RequestMapping(value="/api/admin")
public class AdminController {

	@Autowired IAdminService adminService;
	
	
	@PostMapping(value="/Trajets")
	@ResponseBody
	@ResponseStatus(code=HttpStatus.CREATED)
	public DtoCreationTrajet  addTrajet2(@RequestBody DtoCreationTrajet  dtotrajet) {
		
		return  adminService.createtravel(dtotrajet);
	}
	
	
	@GetMapping(value="/Tournee")
	@ResponseBody
	public List<DtoTournee> listTrip(){
		return adminService.listTrip();
	}
	
	@PostMapping(value="/Tournee")
	@ResponseBody
	@ResponseStatus(code=HttpStatus.CREATED)
	public String  addTrip(@RequestBody DtoTournee  dtoTournee) {
		return  adminService.addTrip(dtoTournee);
	}
	
	@PatchMapping(value="/Tournee")
	@ResponseBody
	public String GenerateTripFromNow(@RequestBody DtoGenerate dtoGenerate) {
		return adminService.GenerateTripFromNow(dtoGenerate);
	}
	

	@PostMapping(value="/Prix")
	@ResponseBody
	@ResponseStatus(code=HttpStatus.CREATED)
	public boolean  addPrixRef(@RequestBody DtoCreationPrixRef  dtoCreationPrixRef) {
		
		return  adminService.createPriceRef(dtoCreationPrixRef);
	}
	
}
package com.proxair.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proxair.dto.DtoTrajet;
import com.proxair.service.IUserService;

@RestController
@RequestMapping(value="/api/user")
public class UserController {

@Autowired IUserService userService;

@GetMapping(value="/trajets")
@ResponseBody
public List<DtoTrajet> findRides(@RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
	return userService.findRides(date);
}


// change statut de trajet pour le mettre en annulé et affiche "trajet annuléé dans body!
@PutMapping(value = "/trajet/annule/{id}")
@ResponseBody
public String cancelTrajet (@PathVariable long id) {
	userService.cancelTrajet(id);
	return "Trajet annulé";
}
}
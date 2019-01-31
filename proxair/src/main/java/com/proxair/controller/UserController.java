package com.proxair.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proxair.dto.DtoReservationPlaces;
import com.proxair.dto.DtoTrajet;
import com.proxair.service.IUserService;

@RestController
@RequestMapping(value="/api/user")
public class UserController {

@Autowired IUserService userService;

@RequestMapping(value="/trajets" , method=RequestMethod.GET)
@ResponseBody 
public List<DtoTrajet> findRides(@RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate fromDate) {
	
	Date date = Date.valueOf(fromDate);
	return userService.findRides(date);
}

@PostMapping(value="/trajets")
@ResponseBody
public DtoReservationPlaces chooseSeats(@RequestParam("idTrajet") long idTrajet, @RequestParam("nbrePlaces") int nbrePlaces) {
	return userService.chooseSeats(idTrajet, nbrePlaces);
}

@PutMapping(value = "/reservation/annule/{id}")
@ResponseBody
public void cancelReservation (@PathVariable long id) {
    userService.cancelResa(id);
}

@PostMapping (value = "/reservation/paiement")
@ResponseBody
public void saveReservation(@RequestParam("mail") String mail, @RequestBody DtoReservationPlaces drp) throws AddressException {
	userService.saveReservation(mail, drp);
}
}
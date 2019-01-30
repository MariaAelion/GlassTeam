package com.proxair.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

@GetMapping(value="/trajets")
@ResponseBody
public List<DtoTrajet> findRides(@RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
	return userService.findRides(date);
}


@PostMapping(value="/trajets")
@ResponseBody
public DtoReservationPlaces chooseSeats(@RequestParam("idTrajet") long idTrajet, @RequestParam("nbrePlaces") int nbrePlaces) {
	return userService.chooseSeats(idTrajet, nbrePlaces);
}

}
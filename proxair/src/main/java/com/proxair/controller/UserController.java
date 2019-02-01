package com.proxair.controller;



import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proxair.dto.DtoDemandedePaiement;
import com.proxair.dto.DtoTrajet;
import com.proxair.dto.DtodemandeAchat;
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

@RequestMapping(value="/trajets/reservation" , method=RequestMethod.GET)
@ResponseBody 
public DtoDemandedePaiement  askReservationFromUser(@RequestBody DtodemandeAchat  dtodemandeAchat) {
	
	return  userService.askReservationFromUser(dtodemandeAchat);
}
}
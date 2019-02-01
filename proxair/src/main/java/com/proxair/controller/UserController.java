package com.proxair.controller;

import java.util.List;

import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.proxair.dto.DtoMail;
import com.proxair.dto.DtoReservationPlaces;
import com.proxair.dto.DtoDemandedePaiement;
import com.proxair.dto.DtoTrajet;
import com.proxair.dto.DtoTrajetsJour;
import com.proxair.dto.DtodemandeAchat;
import com.proxair.service.IUserService;
import com.proxair.service.impl.EmailService;

@RestController
@RequestMapping(value="/api/user")
public class UserController {

@Autowired IUserService userService;
@Autowired EmailService mailService;


@GetMapping(value="/trajets")
@ResponseBody 
public List<DtoTrajet> findRides(@RequestBody DtoTrajetsJour dtoTrajetsJour) {
	return userService.findRides(dtoTrajetsJour.getDate());
}

@PostMapping(value="/trajets")
@ResponseBody
public DtoReservationPlaces chooseSeats(@RequestParam("idTrajet") long idTrajet, @RequestParam("nbrePlaces") int nbrePlaces) {
	return userService.chooseSeats(idTrajet, nbrePlaces);
}

@DeleteMapping(value ="/trajets/reservation/")
@ResponseBody
public void cancelReservation (@RequestBody long idReservation) {
    userService.cancelResa(idReservation);
}

@PostMapping (value = "/trajets/reservation/paiement")
@ResponseBody
public void saveReservation(@RequestParam("mail") String mail, @RequestBody DtoReservationPlaces drp) throws AddressException {
	userService.saveReservation(mail, drp);
}

@PostMapping (value = "/mail")
public void essaiMail(@RequestBody DtoMail mail) {
	mailService.sendMail(mail);
}

@RequestMapping(value="/trajets/reservation" , method=RequestMethod.GET)
@ResponseBody 
public DtoDemandedePaiement  askReservationFromUser(@RequestBody DtodemandeAchat  dtodemandeAchat) {
	return  userService.askReservationFromUser(dtodemandeAchat);
}
}
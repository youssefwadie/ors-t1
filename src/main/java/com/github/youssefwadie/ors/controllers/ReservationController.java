package com.github.youssefwadie.ors.controllers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.youssefwadie.ors.entities.ClassType;
import com.github.youssefwadie.ors.entities.Reservation;
import com.github.youssefwadie.ors.repositories.ReservationRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ReservationController {
	
	private final ReservationRepository repository;
	
	@PostMapping("/reservation/save")
	public String saveReservation(Reservation reservation) {
//		repository.save(reservation);
		System.out.println(reservation);
		return "";
	}
	
	@GetMapping("/reservation/add")
	public ModelAndView addReservation() {
		Map<String, Object> model = new HashMap<>();
		Reservation reservation = new Reservation();
		
		model.put("classTypes", ClassType.values());
		model.put("reservation", reservation);
		return new ModelAndView("reservation_form", model);
	}
}

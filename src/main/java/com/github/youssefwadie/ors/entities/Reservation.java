package com.github.youssefwadie.ors.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "reservations")
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	@Min(value = 8)
	private Integer age;

	@ManyToOne
	@JoinColumn(name = "registered_user_id")
	private User registeredUser;

	@Enumerated(EnumType.STRING)
	private ClassType classType;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate journeyDate;
	private String source;
	private String destination;

}

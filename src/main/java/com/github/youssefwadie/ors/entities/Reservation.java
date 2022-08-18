package com.github.youssefwadie.ors.entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "reservations")
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	@Min(value = 8)
	@Max(value = 72)
	private Integer age;

	@ManyToOne
	@JoinColumn(name = "registered_user_id")
	private User registeredUser;

	@Enumerated(EnumType.STRING)
	private ClassType classType;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@FutureOrPresent
	private LocalDate journeyDate;

	private String source;
	private String destination;

}

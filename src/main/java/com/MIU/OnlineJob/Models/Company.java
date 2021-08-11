package com.MIU.OnlineJob.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.javafaker.Faker;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "company")
@JsonIgnoreProperties({"user","vacancyList"})
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String street;
	private String city;
	private String state;
	private Long zipcode;

	@OneToOne()
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(fetch = FetchType.LAZY)
	private List<Vacancy> vacancyList;

	public Company() {
	}

	public Company(User user) {
		Faker faker = new Faker();
		street = faker.address().streetAddress();
		city = faker.address().city();
		state = faker.address().state();
		zipcode = (long) faker.random().nextInt(1111, 9999);
		this.user = user;
	}



}

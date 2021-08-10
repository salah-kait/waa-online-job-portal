package com.MIU.OnlineJob.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.javafaker.Faker;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "company")
@JsonIgnoreProperties("user")
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getZipcode() {
		return zipcode;
	}

	public void setZipcode(Long zipcode) {
		this.zipcode = zipcode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Vacancy> getVacancyList() {
		return vacancyList;
	}

	public void setVacancyList(List<Vacancy> vacancyList) {
		this.vacancyList = vacancyList;
	}

}

package com.MIU.OnlineJob.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.javafaker.Faker;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "educations")
@JsonIgnoreProperties("jobSeeker")
public class Education {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "Degree is required")
	private String degree;

	@PastOrPresent
	private LocalDate fromDate;

	@FutureOrPresent
	private LocalDate toDate;

	@Positive
	private double gpa ;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "job_seeker_id", referencedColumnName = "id", nullable = false)
	private JobSeeker jobSeeker;


	public Education() {
		Faker faker = new Faker();
		this.gpa = faker.number().randomDouble(2, 2, 4);
	}

	public Education(int fromYear, int toYear, JobSeeker jobSeeker) {
		Faker faker = new Faker();
		this.degree = faker.educator().course();
		this.fromDate = LocalDateTime.now().withYear(fromYear).toLocalDate();
		this.toDate = LocalDateTime.now().withYear(toYear).toLocalDate();
		this.gpa = faker.random().nextInt(1, 4);
		this.jobSeeker = jobSeeker;
	}
	public Education(int fromYear, int toYear) {
		Faker faker = new Faker();
		this.degree = faker.educator().course();
		this.fromDate = LocalDateTime.now().withYear(fromYear).toLocalDate();
		this.toDate = LocalDateTime.now().withYear(toYear).toLocalDate();
		this.gpa = faker.random().nextInt(1, 4);
	}

	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public LocalDate getFromDate() {
		return fromDate;
	}
	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}
	public LocalDate getToDate() {
		return toDate;
	}
	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}

	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}
}

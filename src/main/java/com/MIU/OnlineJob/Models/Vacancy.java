package com.MIU.OnlineJob.Models;

import com.MIU.OnlineJob.Models.enums.Category;
import com.MIU.OnlineJob.Models.enums.VacancyStatus;
import com.github.javafaker.Faker;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "vacancy")
@Data
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String jobDescription;
    private LocalDate postFromDate;
    private LocalDate postToDate;
    private double salaryRangFrom;
    private double salaryRangTo;
    private VacancyStatus vacancyStatus;

    public Vacancy() {
        setVacancyStatus(VacancyStatus.Draft);
    }

    public Vacancy(VacancyStatus vacancyStatus, Company company, int year, int month) {
        this.vacancyStatus = vacancyStatus;
        this.company = company;
        Faker faker = new Faker();
        this.title = faker.job().title();
        this.jobDescription = faker.ancient().titan();
        this.postFromDate = LocalDateTime.now().withYear(year).toLocalDate();
        this.postToDate = LocalDateTime.now().withMonth(month).toLocalDate();
    }

    public VacancyStatus getVacancyStatus() {
        return vacancyStatus;
    }

    public void setVacancyStatus(VacancyStatus vacancyStatus) {
        this.vacancyStatus = vacancyStatus;
    }

    @OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<VacancyApplication> vacancyApplications;

    public void setVacancyApplications(List<VacancyApplication> vacancyApplications) {
        this.vacancyApplications =vacancyApplications;
    }

    public List<VacancyApplication> getVacancyApplications() {
        return this.vacancyApplications;
    }

    @ManyToOne
    private Company company;

    public boolean catigoriesVacancyApplication(JobSeeker jobSeeker, Category category) {
        throw new UnsupportedOperationException();
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobdescription) {
        this.jobDescription = jobdescription;
    }

    public LocalDate getPostFromDate() {
        return postFromDate;
    }

    public void setPostFromDate(LocalDate postFromDate) {
        this.postFromDate = postFromDate;
    }

    public LocalDate getPostToDate() {
        return postToDate;
    }

    public void setPostToDate(LocalDate postToDate) {
        this.postToDate = postToDate;
    }

    public double getSalaryRangFrom() {
        return salaryRangFrom;
    }

    public void setSalaryRangFrom(double salaryRangFrom) {
        this.salaryRangFrom = salaryRangFrom;
    }

    public double getSalaryRangTo() {
        return salaryRangTo;
    }

    public void setSalaryRangTo(double salaryRangTo) {
        this.salaryRangTo = salaryRangTo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}

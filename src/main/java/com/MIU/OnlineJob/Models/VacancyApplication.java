package com.MIU.OnlineJob.Models;

import com.MIU.OnlineJob.Models.enums.Category;
import com.github.javafaker.Faker;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.time.LocalDate;

@Entity
@Table(name = "VacancyApplications")
public class VacancyApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    public void setId(long Id) {
        this.Id =Id;
    }

    public long getId() {
        return this.Id;
    }

    private LocalDate applyDate;
    @Max(value = 10)
    private int companyRating;

    public double getClosedSalary() {
        return closedSalary;
    }

    public void setClosedSalary(double closedSalary) {
        this.closedSalary = closedSalary;
    }

    private double closedSalary;

    private LocalDate interviewDate;
    private Category category;

    public JobSeeker getJobSeeker() {
        return jobSeeker;
    }

    public void setJobSeeker(JobSeeker jobSeeker) {
        this.jobSeeker = jobSeeker;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_seeker_id", referencedColumnName = "id", nullable = false)
    private JobSeeker jobSeeker;

    @ManyToOne(fetch = FetchType.LAZY)
    private Vacancy vacancy;

    public VacancyApplication() {
        Faker faker = new Faker();
        this.companyRating = faker.number().numberBetween(0, 10);
        category = Category.New;
    }

    public LocalDate getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(LocalDate applyDate) {
        this.applyDate = applyDate;
    }

    public int getCompanyRating() {
        return companyRating;
    }

    public void setCompanyRating(int companyRating) {
        this.companyRating = companyRating;
    }

    public LocalDate getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(LocalDate interviewDate) {
        this.interviewDate = interviewDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }
}

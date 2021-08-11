package com.MIU.OnlineJob.Models;

import com.MIU.OnlineJob.Models.enums.Category;
import com.MIU.OnlineJob.Models.enums.VacancyStatus;
import com.github.javafaker.Faker;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "vacancy")
@Data
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String location;
    @Lob
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

    @OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "vacancy")
    private List<VacancyApplication> vacancyApplications;


    @ManyToOne
    private Company company;

    public boolean catigoriesVacancyApplication(JobSeeker jobSeeker, Category category) {
        throw new UnsupportedOperationException();
    }

}

package com.MIU.OnlineJob.Models;

import com.MIU.OnlineJob.Models.enums.Category;
import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class VacancyApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @CreatedDate
    private LocalDate applyDate;

    @Max(value = 10)
    private int companyRating;
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_seeker_id", referencedColumnName = "id", nullable = false)
    private JobSeeker jobSeeker;

    @ManyToOne(fetch = FetchType.LAZY)
    private Vacancy vacancy;

    public VacancyApplication() {
        Faker faker = new Faker();
        this.companyRating = faker.number().numberBetween(0, 10);
        category = Category.New;
        applyDate = LocalDate.now();
    }

}

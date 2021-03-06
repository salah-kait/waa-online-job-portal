package com.MIU.OnlineJob.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Entity
@Table(name = "certificates")
@JsonIgnoreProperties("jobSeeker")
@Getter
@Setter
public class Certificate {
    @Id
    @SequenceGenerator(name = "certificate_sequence", sequenceName = "certificate_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Issued By is required")
    private String issuedBy;

    @PastOrPresent
    private LocalDate issueDate;

    @FutureOrPresent
    private LocalDate expirationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_seeker_id", referencedColumnName = "id", nullable = false)
    private JobSeeker jobSeeker;

    public Certificate() {
        Faker faker = new Faker();
        this.name = faker.name().title();
    }

}

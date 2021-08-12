package com.MIU.OnlineJob.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "job_seekers")
@JsonIgnoreProperties({"vacancyApplications"})
@Getter
@Setter
public class JobSeeker extends AbstractUserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Current Position is required")
    private String currentPosition;

    @NotBlank(message = "Bio is required")
    private String bio;

    @OneToMany(mappedBy = "jobSeeker", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Skill> jobSeekerSkills;

    @OneToMany(mappedBy = "jobSeeker", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<WorkExperience> workExperiences;

    @OneToMany(mappedBy = "jobSeeker", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Certificate> certificates;

    @OneToMany(mappedBy = "jobSeeker", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Education> educations;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jobSeeker")
    private List<VacancyApplication> vacancyApplications;

    private String country;
    private boolean isRemotely;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isRemotely() {
        return isRemotely;
    }

    public JobSeeker() {
        Faker faker = new Faker();
        this.currentPosition = faker.job().position();
        this.country = faker.country().name();
        this.bio = faker.food().dish();
        this.workExperiences=new ArrayList<WorkExperience>();
    }

//    @Override
//    public String toString() {
//        return "JobSeeker{" +
//                "id=" + id +
//                ", currentPosition='" + currentPosition + '\'' +
//                ", bio='" + bio + '\'' +
//                ", jobSeekerSkills=" + jobSeekerSkills +
//                ", workExperiences=" + workExperiences +
//                ", certificates=" + certificates +
//                ", educations=" + educations +
//                ", user=" + user +
//                '}';
//    }
}

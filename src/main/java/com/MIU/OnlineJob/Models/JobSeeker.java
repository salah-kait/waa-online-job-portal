package com.MIU.OnlineJob.Models;

import com.github.javafaker.Faker;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "job_seekers")
public class JobSeeker extends AbstractUserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Current Position is required")
    private String currentPosition;

    @NotBlank(message = "Bio is required")
    private String bio;

    @OneToMany(mappedBy = "jobSeeker", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<JobSeekerSkill> jobSeekerSkills;

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

    public void setRemotely(boolean remotely) {
        isRemotely = remotely;
    }



    public JobSeeker() {
        Faker faker = new Faker();
        this.currentPosition = faker.job().position();
        this.country = faker.country().name();
        this.bio = faker.food().dish();
        this.workExperiences=new ArrayList<WorkExperience>();
    }

    public List<JobSeekerSkill> getJobSeekerSkills() {
        return jobSeekerSkills;
    }

    public void setJobSeekerSkills(List<JobSeekerSkill> jobSeekerSkills) {
        this.jobSeekerSkills = jobSeekerSkills;
    }

    public List<WorkExperience> getWorkExperiences() {
        return workExperiences;
    }

    public void setWorkExperiences(List<WorkExperience> workExperiences) {
        this.workExperiences = workExperiences;
    }

    public List<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }

    public List<Education> getEducations() {
        return educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }


    public long getId() {
        return id;
    }

    public void setCurrentPosition(String currentPosition) {
        this.currentPosition = currentPosition;
    }

    public String getCurrentPosition() {
        return currentPosition;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public User getUser() {
        return this.user ;
    }

    public List<VacancyApplication> getVacancyApplications() {
        return vacancyApplications;
    }

    public void setVacancyApplications(List<VacancyApplication> vacancyApplications) {
        this.vacancyApplications = vacancyApplications;
    }

    @Override
    public String toString() {
        return "JobSeeker{" +
                "id=" + id +
                ", currentPosition='" + currentPosition + '\'' +
                ", bio='" + bio + '\'' +
                ", jobSeekerSkills=" + jobSeekerSkills +
                ", workExperiences=" + workExperiences +
                ", certificates=" + certificates +
                ", educations=" + educations +
                ", user=" + user +
                '}';
    }
}

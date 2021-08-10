package com.MIU.OnlineJob.Payload;

import java.time.LocalDate;
import java.util.Date;

public class VacancyRequest {

    private String title;
    private String jobDescription;
    private LocalDate postFromDate;
    private LocalDate postToDate;
    private double salaryRangFrom;
    private double salaryRangTo;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
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
}

package com.MIU.OnlineJob.Payload.Requests;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class VacancyRequest {

    private String title;
    private String jobDescription;
    private String location;
    private LocalDate postFromDate;
    private LocalDate postToDate;
    private double salaryRangFrom;
    private double salaryRangTo;

}

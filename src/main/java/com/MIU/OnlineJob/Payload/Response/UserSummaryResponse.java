package com.MIU.OnlineJob.Payload.Response;

import com.MIU.OnlineJob.Models.JobSeeker;
import com.MIU.OnlineJob.Models.enums.RoleName;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserSummaryResponse {
    private Long id;
    private String username;
    private String name;
    private RoleName role;
    private JobSeeker jobSeeker;

    public UserSummaryResponse(Long id, String username, String name,RoleName role,JobSeeker jobSeeker) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.role = role;
        this.jobSeeker = jobSeeker;
    }

}

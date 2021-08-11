package com.MIU.OnlineJob.Payload.Response;

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

    public UserSummaryResponse(Long id, String username, String name,RoleName role) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.role = role;
    }

}

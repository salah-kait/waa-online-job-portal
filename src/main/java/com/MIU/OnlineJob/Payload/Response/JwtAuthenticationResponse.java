package com.MIU.OnlineJob.Payload.Response;

import com.MIU.OnlineJob.Security.UserPrincipal;
import lombok.Getter;

@Getter
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private UserPrincipal user ;

    public JwtAuthenticationResponse(String accessToken, UserPrincipal userDetails) {
        this.accessToken = accessToken;
        this.user = userDetails;
    }

}

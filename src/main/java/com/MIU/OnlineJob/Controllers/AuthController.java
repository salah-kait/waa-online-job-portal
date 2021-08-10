package com.MIU.OnlineJob.Controllers;

import com.MIU.OnlineJob.Exception.AppException;
import com.MIU.OnlineJob.Factories.Enums.NotificationType;
import com.MIU.OnlineJob.Models.Company;
import com.MIU.OnlineJob.Models.JobSeeker;
import com.MIU.OnlineJob.Models.Role;
import com.MIU.OnlineJob.Models.enums.RoleName;
import com.MIU.OnlineJob.Models.User;
import com.MIU.OnlineJob.Payload.Response.ApiResponse;
import com.MIU.OnlineJob.Payload.Response.JwtAuthenticationResponse;
import com.MIU.OnlineJob.Payload.Requests.LoginRequest;
import com.MIU.OnlineJob.Payload.Requests.SignUpRequest;
import com.MIU.OnlineJob.Repositories.RoleRepository;
import com.MIU.OnlineJob.Repositories.UserRepository;
import com.MIU.OnlineJob.Security.JwtTokenProvider;
import com.MIU.OnlineJob.Services.CompanyService;
import com.MIU.OnlineJob.Services.JobSeekerService;
import com.MIU.OnlineJob.Services.Notifiers.NotificationServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.Objects;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    CompanyService companyService;

    @Autowired
    JobSeekerService jobSeekerService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    NotificationServiceFactory notificationServiceFactory;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        RoleName userRoleName = Objects.equals(signUpRequest.getRole(), "company") ?RoleName.ROLE_COMPANY:RoleName.ROLE_JOBSEEKER;
        System.out.println("==========");
        System.out.println(userRoleName);
        Role userRole = roleRepository.findByName(userRoleName)
                .orElseThrow(() -> new AppException("User Role not set."+signUpRequest.getRole()));

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        notificationServiceFactory.getService(NotificationType.Email).sendNotification(result,"Welcome to MIU Job Portal","Welcome "+result.getName()+" to MIU Job Portal");

        if(userRoleName == RoleName.ROLE_COMPANY){
            Company comp = new Company();
            comp.setUser(result);
            companyService.save(comp);
        }else if(userRoleName == RoleName.ROLE_JOBSEEKER){
            JobSeeker js = new JobSeeker();
            js.setBio("First Job Seeker");
            js.setCurrentPosition("Software Engineer");
            js.setUser(result);
            jobSeekerService.save(js);
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}

package com.MIU.OnlineJob.Controllers;

import com.MIU.OnlineJob.Models.Role;
import com.MIU.OnlineJob.Models.User;
import com.MIU.OnlineJob.Models.enums.RoleName;
import com.MIU.OnlineJob.Payload.ApiResponse;
import com.MIU.OnlineJob.Payload.CreateUserRequest;
import com.MIU.OnlineJob.Payload.UserSummaryResponse;
import com.MIU.OnlineJob.Security.CurrentUser;
import com.MIU.OnlineJob.Security.UserPrincipal;
import com.MIU.OnlineJob.Services.RoleService;
import com.MIU.OnlineJob.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/user")
public class UserController {

    PasswordEncoder passwordEncoder;

    private RoleService roleService;
    private UserService userService;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @PostMapping("create-admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createPoll(@Valid @RequestBody CreateUserRequest createUserRequest) {
        if(userService.existsByUsername(createUserRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userService.existsByEmail(createUserRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(createUserRequest.getName(), createUserRequest.getUsername(),
                createUserRequest.getEmail(), createUserRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleService.findRoleByName(RoleName.ROLE_ADMIN);

        user.setRoles(Collections.singleton(userRole));

        User result = userService.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }

    @GetMapping("/me")
    public UserSummaryResponse getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummaryResponse userSummary = new UserSummaryResponse(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
        return userSummary;
    }
}

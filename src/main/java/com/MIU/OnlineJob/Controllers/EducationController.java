package com.MIU.OnlineJob.Controllers;

import com.MIU.OnlineJob.Exception.ResourceNotFoundException;
import com.MIU.OnlineJob.Models.JobSeeker;
import com.MIU.OnlineJob.Models.Education;
import com.MIU.OnlineJob.Payload.Response.ApiResponse;
import com.MIU.OnlineJob.Security.CurrentUser;
import com.MIU.OnlineJob.Security.UserPrincipal;
import com.MIU.OnlineJob.Services.JobSeekerService;
import com.MIU.OnlineJob.Services.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/educations")
@PreAuthorize("hasRole('JOBSEEKER')")
public class EducationController {

    private final EducationService educationService;
    private final JobSeekerService jobSeekerService;

    @Autowired
    public EducationController(EducationService educationService, JobSeekerService jobSeekerService) {
        this.educationService = educationService;
        this.jobSeekerService = jobSeekerService;
    }

    @GetMapping("/")
    List<Education> all() {
        return this.educationService.findAll();
    }

    @PostMapping("/")
    Education newEducation(@RequestBody Education newEducation,@CurrentUser UserPrincipal currentUser) {

        JobSeeker js = jobSeekerService.findByUserId(currentUser.getId());
        newEducation.setJobSeeker(js);
        return this.educationService.save(newEducation);
    }

    @GetMapping("/{id}")
    Education getEducation(@PathVariable Long id) throws ResourceNotFoundException {
        return this.educationService.findById(id);
    }

    @PutMapping("/{id}")
    Education replaceEducation(@RequestBody Education newEducation, @PathVariable Long id) throws ResourceNotFoundException {
        return this.educationService.replaceEducation(newEducation, id);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse> deleteEducation(@PathVariable Long id) {
        try{
            Education education = educationService.findById(id);
        }catch (ResourceNotFoundException e){
            return new ResponseEntity(new ApiResponse(false, "Invalid education Id"),
                    HttpStatus.BAD_REQUEST);
        }

        this.educationService.delete(id);
        return new ResponseEntity(new ApiResponse(true, "Deleted"),
                HttpStatus.OK);

    }
}

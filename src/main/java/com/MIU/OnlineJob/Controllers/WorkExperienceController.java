package com.MIU.OnlineJob.Controllers;

import com.MIU.OnlineJob.Exception.ResourceNotFoundException;
import com.MIU.OnlineJob.Models.JobSeeker;
import com.MIU.OnlineJob.Models.WorkExperience;
import com.MIU.OnlineJob.Payload.ApiResponse;
import com.MIU.OnlineJob.Security.CurrentUser;
import com.MIU.OnlineJob.Security.UserPrincipal;
import com.MIU.OnlineJob.Services.JobSeekerService;
import com.MIU.OnlineJob.Services.WorkExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/work_experiences")
@PreAuthorize("hasRole('JOBSEEKER')")
public class WorkExperienceController {

    private final WorkExperienceService workExperienceService;
    private final JobSeekerService jobSeekerService;

    @Autowired
    public WorkExperienceController(WorkExperienceService workExperienceService, JobSeekerService jobSeekerService) {
        this.workExperienceService = workExperienceService;
        this.jobSeekerService = jobSeekerService;
    }

    @GetMapping("/")
    List<WorkExperience> all() {
        return this.workExperienceService.findAll();
    }

    @PostMapping("/new")
    WorkExperience newWorkExperience(@RequestBody WorkExperience newWorkExperience, @CurrentUser UserPrincipal currentUser) {
        JobSeeker jobSeeker = jobSeekerService.findByUserId(currentUser.getId());
        newWorkExperience.setJobSeeker(jobSeeker);
        return this.workExperienceService.save(newWorkExperience);
    }

    @GetMapping("/{id}")
    WorkExperience getWorkExperience(@PathVariable Long id) throws Exception {
        try{
            return this.workExperienceService.findById(id);
        }catch (Exception e){
            return new WorkExperience();
        }
    }

    @PutMapping("/{id}")
    WorkExperience replaceWorkExperience(@RequestBody WorkExperience newSkill, @PathVariable Long id) throws ResourceNotFoundException {
        return this.workExperienceService.replaceWorkExperience(newSkill, id);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse> deleteWorkExperience(@PathVariable Long id) throws Exception {
        try{
            WorkExperience workExperience = workExperienceService.findById(id);
        }catch (Exception e){
            return new ResponseEntity(new ApiResponse(false, "Invalid Work Experience Id"),
                    HttpStatus.BAD_REQUEST);
        }

        this.workExperienceService.delete(id);
        return new ResponseEntity(new ApiResponse(true, "Deleted"),
                HttpStatus.OK);

    }
}

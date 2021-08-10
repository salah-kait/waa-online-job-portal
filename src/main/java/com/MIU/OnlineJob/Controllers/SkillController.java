package com.MIU.OnlineJob.Controllers;

import com.MIU.OnlineJob.Exception.ResourceNotFoundException;
import com.MIU.OnlineJob.Models.JobSeeker;
import com.MIU.OnlineJob.Models.Skill;
import com.MIU.OnlineJob.Payload.ApiResponse;
import com.MIU.OnlineJob.Security.CurrentUser;
import com.MIU.OnlineJob.Security.UserPrincipal;
import com.MIU.OnlineJob.Services.JobSeekerService;
import com.MIU.OnlineJob.Services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/skills")
@PreAuthorize("hasRole('JOBSEEKER')")
public class SkillController {

    private final SkillService skillService;
    private final JobSeekerService jobSeekerService;

    @Autowired
    public SkillController(SkillService skillService, JobSeekerService jobSeekerService) {
        this.skillService = skillService;
        this.jobSeekerService = jobSeekerService;
    }

    @GetMapping("/")
    List<Skill> all() {
        return this.skillService.findAll();
    }

    @PostMapping("/new")
    Skill newSkill(@RequestBody Skill newSkill,@CurrentUser UserPrincipal currentUser) {

        JobSeeker js = jobSeekerService.findByUserId(currentUser.getId());
        //newSkill.setJobSeeker(js);
        return this.skillService.save(newSkill);
    }

    @GetMapping("/{id}")
    Skill getSkill(@PathVariable Long id) throws ResourceNotFoundException {
        return this.skillService.findById(id);
    }

    @PutMapping("/{id}")
    Skill replaceSkill(@RequestBody Skill newSkill, @PathVariable Long id) throws ResourceNotFoundException {
        return this.skillService.replaceSkill(newSkill, id);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse> deleteSkill(@PathVariable Long id) {
        try{
            Skill skill = skillService.findById(id);
        }catch (ResourceNotFoundException e){
            return new ResponseEntity(new ApiResponse(false, "Invalid skill Id"),
                    HttpStatus.BAD_REQUEST);
        }

        //todo check owner user

        this.skillService.delete(id);
        return new ResponseEntity(new ApiResponse(true, "Deleted"),
                HttpStatus.OK);

    }
}

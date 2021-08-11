package com.MIU.OnlineJob.Controllers;

import com.MIU.OnlineJob.Exception.ResourceNotFoundException;
import com.MIU.OnlineJob.Models.Certificate;
import com.MIU.OnlineJob.Models.JobSeeker;
import com.MIU.OnlineJob.Payload.Response.ApiResponse;
import com.MIU.OnlineJob.Security.CurrentUser;
import com.MIU.OnlineJob.Security.UserPrincipal;
import com.MIU.OnlineJob.Services.CertificateService;
import com.MIU.OnlineJob.Services.JobSeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/certificates")
@PreAuthorize("hasRole('JOBSEEKER')")
public class CertificateController {

    private final CertificateService certificateService;
    private final JobSeekerService jobSeekerService;

    @Autowired
    public CertificateController(CertificateService certificateService, JobSeekerService jobSeekerService) {
        this.certificateService = certificateService;
        this.jobSeekerService = jobSeekerService;
    }

    @GetMapping("/")
    List<Certificate> all() {
        return this.certificateService.findAll();
    }

    @PostMapping("/")
    Certificate newCertificate(@RequestBody Certificate newCertificate, @CurrentUser UserPrincipal currentUser) {
        JobSeeker js = jobSeekerService.findByUserId(currentUser.getId());
        newCertificate.setJobSeeker(js);
        return this.certificateService.save(newCertificate);
    }

    @GetMapping("/{id}")
    Certificate getCertificate(@PathVariable Long id) throws ResourceNotFoundException {
        return this.certificateService.findById(id);
    }

    @PutMapping("/{id}")
    Certificate replaceCertificate(@RequestBody Certificate newCertificate, @PathVariable Long id) throws ResourceNotFoundException {
        return this.certificateService.replaceCertificate(newCertificate, id);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse> deleteCertificate(@PathVariable Long id) {
        try{
            Certificate certificate = certificateService.findById(id);
        }catch (ResourceNotFoundException e){
            return new ResponseEntity(new ApiResponse(false, "Invalid Certificate Id"),
                    HttpStatus.BAD_REQUEST);
        }

        this.certificateService.delete(id);
        return new ResponseEntity(new ApiResponse(true, "Deleted"),
                HttpStatus.OK);

    }
}

package com.MIU.OnlineJob.Controllers;


import com.MIU.OnlineJob.Exception.AppException;
import com.MIU.OnlineJob.Exception.ResourceNotFoundException;
import com.MIU.OnlineJob.Models.Company;
import com.MIU.OnlineJob.Models.Skill;
import com.MIU.OnlineJob.Models.Vacancy;
import com.MIU.OnlineJob.Models.enums.VacancyStatus;
import com.MIU.OnlineJob.Payload.Requests.ChangeVacancyStatusRequest;
import com.MIU.OnlineJob.Payload.Requests.VacancyRequest;
import com.MIU.OnlineJob.Payload.Response.ApiResponse;
import com.MIU.OnlineJob.Security.CurrentUser;
import com.MIU.OnlineJob.Security.UserPrincipal;
import com.MIU.OnlineJob.Services.CompanyService;
import com.MIU.OnlineJob.Services.UserService;
import com.MIU.OnlineJob.Services.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vacancy")
public class VacancyController {

    @Autowired
    private  VacancyService vacancyService;
    @Autowired
    private  CompanyService companyService;

    @GetMapping("/{id}")
    public Vacancy getVacancy(@PathVariable Long id){
        return vacancyService.getVacancy(id);
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('COMPANY')")
    public List<Vacancy> getVacancies(@CurrentUser UserPrincipal currentUse){
        Company company = this.companyService.findByUser(currentUse.getId());
        if (company == null){
            throw new AppException("Do not have a company");
        }
        return vacancyService.getCompanyVacancies(company);
    }


    @PostMapping
    @PreAuthorize("hasRole('COMPANY')")
    public Vacancy saveVacancy(@RequestBody VacancyRequest vacancyRequest, @CurrentUser UserPrincipal currentUser){
        Company company = this.companyService.findByUser(currentUser.getId());
        if (company == null){
            throw new AppException("Cannot post vaccancy against");
        }
        Vacancy vacancy = new Vacancy();
        vacancy.setJobDescription(vacancyRequest.getJobDescription());
        vacancy.setPostFromDate(vacancyRequest.getPostFromDate());
        vacancy.setPostToDate(vacancyRequest.getPostToDate());
        vacancy.setTitle(vacancyRequest.getTitle());
        vacancy.setSalaryRangFrom(vacancyRequest.getSalaryRangFrom());
        vacancy.setSalaryRangTo(vacancyRequest.getSalaryRangTo());
        vacancy.setLocation(vacancyRequest.getLocation());

        //Company company = this.companyService.findById(vacancyRequest.getCompanyId().longValue());

        vacancy.setCompany(company);

        return vacancyService.save(vacancy);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('COMPANY')")
    public Vacancy updateVacancy(@PathVariable Long id,@RequestBody VacancyRequest vacancyRequest, @CurrentUser UserPrincipal currentUser){

        Company company = this.companyService.findByUser(currentUser.getId());
        if (company == null){
            throw new AppException("Cannot post vaccancy against");
        }
        return vacancyService.update(vacancyRequest,id);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('COMPANY')")
    //todo disable this action if it has applications
    public void deleteVacancy(@PathVariable Long id){
        vacancyService.delete(id);
    }


    @GetMapping("/approved")
    public List<Vacancy> getApprovedVacancies(){
        return vacancyService.getVacanciesByStatus(VacancyStatus.Published);
    }

    @PostMapping("/apply/{id}")
    @PreAuthorize("hasRole('JOBSEEKER')")
    public ResponseEntity<ApiResponse> apply(@PathVariable Long id, @CurrentUser UserPrincipal currentUser){
        try{
            this.vacancyService.apply(id,currentUser.getId());

            return new ResponseEntity<>(
                    new ApiResponse(true, "Application Submitted successfully"),
                    HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity(new ApiResponse(false, e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/change-status/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> approve(@PathVariable Long id, @RequestBody ChangeVacancyStatusRequest changeStatusRequest, @CurrentUser UserPrincipal currentUser){
        try{
            Vacancy vacancy = this.vacancyService.findById(id);
            vacancy.setVacancyStatus(changeStatusRequest.getStatus().toLowerCase().equals("Published")?VacancyStatus.Published:VacancyStatus.Canceled);
            this.vacancyService.save(vacancy);
            return new ResponseEntity<>(
                    new ApiResponse(true, "Vacancy Status Updates successfully"),
                    HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity(new ApiResponse(false, e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/pending-approval/")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Vacancy>  pendingApproval(){
        return vacancyService.getVacanciesByStatus(VacancyStatus.Draft);
    }
}

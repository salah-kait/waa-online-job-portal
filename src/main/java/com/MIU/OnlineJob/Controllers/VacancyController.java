package com.MIU.OnlineJob.Controllers;


import com.MIU.OnlineJob.Exception.AppException;
import com.MIU.OnlineJob.Exception.ResourceNotFoundException;
import com.MIU.OnlineJob.Models.Company;
import com.MIU.OnlineJob.Models.Skill;
import com.MIU.OnlineJob.Models.Vacancy;
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
    public List<Vacancy> getVacancies(){
        return vacancyService.getAllVacancies();
    }

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
    //todo filter approved jobs by admin
    public List<Vacancy> getApprovedVacancies(){
        return vacancyService.getAllVacancies();
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
}

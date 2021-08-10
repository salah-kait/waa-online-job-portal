package com.MIU.OnlineJob.Controllers;


import com.MIU.OnlineJob.Exception.AppException;
import com.MIU.OnlineJob.Models.Company;
import com.MIU.OnlineJob.Models.Vacancy;
import com.MIU.OnlineJob.Payload.VacancyRequest;
import com.MIU.OnlineJob.Security.CurrentUser;
import com.MIU.OnlineJob.Security.UserPrincipal;
import com.MIU.OnlineJob.Services.CompanyService;
import com.MIU.OnlineJob.Services.UserService;
import com.MIU.OnlineJob.Services.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vacancy")
public class VacancyController {

    private final VacancyService vacancyService;
    private final CompanyService companyService;

    @Autowired
    public VacancyController(VacancyService vacancyService, CompanyService companyService, UserService userService) {
        this.vacancyService = vacancyService;
        this.companyService = companyService;
    }

    @GetMapping("/{id}")
    public Vacancy getVacancy(@PathVariable Long id){
        return vacancyService.getVacancy(id);
    }

    @GetMapping("/list")
    public List<Vacancy> getVacancies(){
        return vacancyService.getAllVacancies();
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

        //Company company = this.companyService.findById(vacancyRequest.getCompanyId().longValue());

        vacancy.setCompany(company);

        return vacancyService.save(vacancy);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('COMPANY')")
    public void deleteVacancy(@PathVariable Long id){
        vacancyService.delete(id);
    }
}

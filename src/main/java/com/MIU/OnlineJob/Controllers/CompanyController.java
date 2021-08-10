package com.MIU.OnlineJob.Controllers;

import com.MIU.OnlineJob.Models.Company;
import com.MIU.OnlineJob.Payload.CompanyResponse;
import com.MIU.OnlineJob.Payload.CompanyRequestModel;
import com.MIU.OnlineJob.Payload.UpdateCompanyRequest;
import com.MIU.OnlineJob.Security.CurrentUser;
import com.MIU.OnlineJob.Security.UserPrincipal;
import com.MIU.OnlineJob.Services.CompanyService;
import com.MIU.OnlineJob.Services.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/company")
public class CompanyController {

    CompanyService companyService;
    UserService userService;

    @Autowired
    public CompanyController(CompanyService companyService, UserService userService) {
        this.companyService = companyService;
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public CompanyResponse addCompany(@RequestBody CompanyRequestModel cm, BindingResult br, @CurrentUser UserPrincipal userPrincipal) {
        CompanyResponse cr = new CompanyResponse();
        System.out.println(cm);
        if (br.hasErrors()) {
            cr.setStatusCode(500);
            cr.setResponseMessage("Wrong information");
            return cr;
        }
        Company comp = new Company();
        comp.setCity(cm.getCity());
        comp.setState(cm.getState());
        comp.setZipcode(cm.getZipcode());
        comp.setStreet(cm.getStreet());

        try {
            comp.setUser(this.userService.getUserById(userPrincipal.getId()));
        } catch (NotFoundException e) {
            cr.setStatusCode(404);
            cr.setResponseMessage("Company not found.");
            return cr;
        }

        comp = companyService.save(comp);

        cr.setStatusCode(200);
        cr.setResponseMessage("Student with an id " + comp.getId() + " is created succesfully!!");

        return cr;

    }

    @GetMapping("list")
    public List<Company> getAll() {
        return companyService.findAll();
    }

    @GetMapping("/{id}")
    public Company getCompanyInfo(@PathVariable Long id){
        return companyService.getCompanyById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('COMPANY')")
    public Company updateCompanyInfo(@Valid @RequestBody UpdateCompanyRequest request, @PathVariable Long id){
        Company company =  companyService.getCompanyById(id);
        company.setCity(request.getCity());
        company.setStreet(request.getStreet());
        company.setState(request.getState());
        company.setZipcode(request.getZipcode());
        companyService.save(company);
        return company;
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Long id){
        companyService.deleteById(id);
    }


}

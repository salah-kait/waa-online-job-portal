package com.MIU.OnlineJob.Services;

import com.MIU.OnlineJob.Exception.ResourceNotFoundException;
import com.MIU.OnlineJob.Models.Company;
import com.MIU.OnlineJob.Models.JobSeeker;
import com.MIU.OnlineJob.Models.VacancyApplication;
import com.MIU.OnlineJob.Models.enums.VacancyStatus;
import com.MIU.OnlineJob.Payload.Requests.VacancyRequest;
import com.MIU.OnlineJob.Repositories.UserRepository;
import com.MIU.OnlineJob.Repositories.VacancyApplicationRepository;
import com.MIU.OnlineJob.Repositories.VacancyRepository;
import com.MIU.OnlineJob.Exception.AppException;
import com.MIU.OnlineJob.Models.Vacancy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VacancyService {

    VacancyRepository vacancyRepository;
    VacancyApplicationRepository vacancyApplicationRepository;

    @Autowired
    JobSeekerService jobSeekerService;

    @Autowired
    public VacancyService(VacancyRepository vacancyRepository,VacancyApplicationRepository vacancyApplicationRepository) {
        this.vacancyRepository = vacancyRepository;
        this.vacancyApplicationRepository = vacancyApplicationRepository;
    }

    public Vacancy getVacancy(Long id) {
        return vacancyRepository.findById(id).orElseThrow(() -> new AppException("Vacancy with id:"+ id + " doesn't exist"));
    }

    public Vacancy save(Vacancy vacancy) {
        return vacancyRepository.save(vacancy);
    }

    public void delete(Long id) {
        vacancyRepository.deleteById(id);
    }

    public List<Vacancy> getAllVacancies() {
        return vacancyRepository.findAll();
    }

    public Vacancy update(VacancyRequest vacancyRequest, Long id) {
        Optional<Vacancy> vacancy = vacancyRepository.findById(id);
        if(vacancy.isPresent()){
            Vacancy vacancy1 = vacancy.get();
            vacancy1.setJobDescription(vacancyRequest.getJobDescription());
            vacancy1.setPostFromDate(vacancyRequest.getPostFromDate());
            vacancy1.setPostToDate(vacancyRequest.getPostToDate());
            vacancy1.setTitle(vacancyRequest.getTitle());
            vacancy1.setSalaryRangFrom(vacancyRequest.getSalaryRangFrom());
            vacancy1.setSalaryRangTo(vacancyRequest.getSalaryRangTo());
            vacancy1.setLocation(vacancyRequest.getLocation());
            return vacancyRepository.save(vacancy1);
        }else{
            throw new ResourceNotFoundException("Vacancy", "id", id);
        }
    }

    public Vacancy findById(Long id) {
        Optional<Vacancy> vacancy = this.vacancyRepository.findById(id);
        if(vacancy.isPresent()){
            return vacancy.get();
        }
        throw new ResourceNotFoundException(Vacancy.class.toString(),"id",id);
    }


    public void apply(Long vacancyId,Long userId){
        JobSeeker jobSeeker = jobSeekerService.findByUserId(userId);
        Vacancy vacancy = this.findById(vacancyId);
        Optional<VacancyApplication> vao = this.vacancyApplicationRepository.findByVacancyAndJobSeeker(vacancy,jobSeeker);
        if(vao.isPresent()){
            throw new AppException("Already applied");
        }else{
            VacancyApplication vacancyApplication = new VacancyApplication();
            vacancyApplication.setJobSeeker(jobSeeker);
            vacancyApplication.setVacancy(vacancy);
            this.vacancyApplicationRepository.save(vacancyApplication);
        }
    }

    public List<Vacancy> getCompanyVacancies(Company company) {
        return vacancyRepository.findAllByCompany(company);
    }

    public List<Vacancy> getVacanciesByStatus(VacancyStatus vacancyStatus) {
        return vacancyRepository.findAllByVacancyStatus(vacancyStatus);
    }
}

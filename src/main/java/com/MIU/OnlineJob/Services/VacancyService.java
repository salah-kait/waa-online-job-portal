package com.MIU.OnlineJob.Services;

import com.MIU.OnlineJob.Exception.ResourceNotFoundException;
import com.MIU.OnlineJob.Payload.Requests.VacancyRequest;
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

    @Autowired
    public VacancyService(VacancyRepository vacancyRepository) {
        this.vacancyRepository = vacancyRepository;
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
}

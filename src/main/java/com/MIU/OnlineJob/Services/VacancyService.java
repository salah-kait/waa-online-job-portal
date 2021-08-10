package com.MIU.OnlineJob.Services;

import com.MIU.OnlineJob.Repositories.VacancyRepository;
import com.MIU.OnlineJob.Exception.AppException;
import com.MIU.OnlineJob.Models.Vacancy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}

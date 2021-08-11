package com.MIU.OnlineJob.Repositories;

import com.MIU.OnlineJob.Models.JobSeeker;
import com.MIU.OnlineJob.Models.Vacancy;
import com.MIU.OnlineJob.Models.VacancyApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VacancyApplicationRepository extends JpaRepository<VacancyApplication, Long> {
    Optional<VacancyApplication> findByVacancyAndJobSeeker(Vacancy vacancy, JobSeeker jobSeeker);
    List<VacancyApplication> findAllByVacancy(Vacancy vacancy);
    Long countByVacancy(Vacancy vacancy);

}

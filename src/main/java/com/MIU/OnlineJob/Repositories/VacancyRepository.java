package com.MIU.OnlineJob.Repositories;

import com.MIU.OnlineJob.Models.Company;
import com.MIU.OnlineJob.Models.Vacancy;
import com.MIU.OnlineJob.Models.enums.VacancyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
    @Query("SELECT v FROM Vacancy v WHERE (v.title LIKE %:term% or  v.jobDescription like %:term%) and v.vacancyStatus = 1")
    List<Vacancy> search(@Param("term") String term);
    List<Vacancy> findAllByVacancyStatus(VacancyStatus vacancyStatus);
    List<Vacancy> findAllByCompany(Company company);
    Optional<Vacancy> findByCompanyAndId(Company company,Long id);

}

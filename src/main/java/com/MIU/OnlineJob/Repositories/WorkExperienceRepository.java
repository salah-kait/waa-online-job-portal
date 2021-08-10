package com.MIU.OnlineJob.Repositories;

import com.MIU.OnlineJob.Models.Vacancy;
import com.MIU.OnlineJob.Models.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Long> {

    @Query("SELECT we FROM WorkExperience we INNER JOIN JobSeeker js ON js.id = we.jobSeeker.id WHERE js.user.id = :userId")
    List<WorkExperience> findAllByUserId(@Param("userId") Long userId);
}


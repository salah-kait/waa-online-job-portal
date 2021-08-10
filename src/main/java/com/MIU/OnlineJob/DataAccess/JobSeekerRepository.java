package com.MIU.OnlineJob.DataAccess;

import com.MIU.OnlineJob.Models.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobSeekerRepository extends JpaRepository<JobSeeker, Long>  {
    JobSeeker findJobSeekerByUserId(Long userId);
}

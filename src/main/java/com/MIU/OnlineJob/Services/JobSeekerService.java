package com.MIU.OnlineJob.Services;


import com.MIU.OnlineJob.DataAccess.JobSeekerRepository;
import com.MIU.OnlineJob.Exception.ResourceNotFoundException;
import com.MIU.OnlineJob.Models.JobSeeker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobSeekerService {
    private JobSeekerRepository _JobSeekerRepository;

    @Autowired
    public JobSeekerService(JobSeekerRepository jobSeekerRepository) {
        this._JobSeekerRepository = jobSeekerRepository;
    }

    public JobSeekerRepository get_JobSeekerRepository() {
        return this._JobSeekerRepository;
    }

    public List<JobSeeker> findAll() {
        return this._JobSeekerRepository.findAll();
    }

    public JobSeeker findById(long id) throws ResourceNotFoundException {
        return this._JobSeekerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job Seeker", "ID", id));
    }

    public JobSeeker replaceJobSeeker(JobSeeker newJobSeeker, long id) {
        return this._JobSeekerRepository
                .findById(id)
                .map(jobSeeker -> {
                    jobSeeker.setBio(newJobSeeker.getBio());
                    jobSeeker.setCurrentPosition(newJobSeeker.getCurrentPosition());
                    return this._JobSeekerRepository.save(jobSeeker);
                })
                .orElseGet(() -> {
                    newJobSeeker.setId(id);
                    return this._JobSeekerRepository.save(newJobSeeker);
                });
    }

    public JobSeeker save(JobSeeker jobSeeker) {
        return this._JobSeekerRepository.save(jobSeeker);
    }

    public JobSeeker findByUserId(Long userId) {
        return this._JobSeekerRepository.findJobSeekerByUserId(userId);
    }

    public void delete(long id) {
       this. _JobSeekerRepository.deleteById(id);
    }
}

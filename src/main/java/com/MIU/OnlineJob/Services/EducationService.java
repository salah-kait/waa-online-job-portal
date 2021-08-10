package com.MIU.OnlineJob.Services;

import com.MIU.OnlineJob.DataAccess.EducationRepository;
import com.MIU.OnlineJob.Exception.ResourceNotFoundException;
import com.MIU.OnlineJob.Models.Education;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {
    private EducationRepository educationRepository;

    @Autowired
    public EducationService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    public List<Education> findAll() {
        return educationRepository.findAll();
    }

    public Education findById(long id) throws ResourceNotFoundException {
        return educationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Education", "ID", id));
    }

    public Education replaceEducation(Education newEducation, long id) {
        return educationRepository
                .findById(id)
                .map(education -> {
                    education.setDegree(newEducation.getDegree());
                    education.setFromDate(newEducation.getFromDate());
                    education.setToDate(newEducation.getToDate());
                    education.setGpa(newEducation.getGpa());
                    return this.educationRepository.save(education);
                })
                .orElseGet(() -> {
                    newEducation.setId(id);
                    return this.educationRepository.save(newEducation);
                });
    }

    public Education save(Education education) {
        educationRepository.save(education);
        return education;
    }

    public void delete(long id) {
        educationRepository.deleteById(id);
    }
    
}

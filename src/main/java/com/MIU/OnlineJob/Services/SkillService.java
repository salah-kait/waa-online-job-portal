package com.MIU.OnlineJob.Services;

import com.MIU.OnlineJob.Repositories.JobSeekerRepository;
import com.MIU.OnlineJob.Repositories.SkillRepository;
import com.MIU.OnlineJob.Exception.ResourceNotFoundException;
import com.MIU.OnlineJob.Models.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {
	private SkillRepository skillRepository;
	private JobSeekerRepository jobSeekerRepository;

	@Autowired
	public SkillService(SkillRepository skillRepository, JobSeekerRepository jobSeekerRepository) {
		this.jobSeekerRepository = jobSeekerRepository;
		this.skillRepository = skillRepository;
	}

	public SkillRepository getSkillRepository() {
		return skillRepository;
	}

	public List<Skill> findAll() {
		return skillRepository.findAll();
	}

	public Skill findById(long id) throws ResourceNotFoundException {
		return skillRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Skill", "ID", id));
	}

	public Skill replaceSkill(Skill newSkill, long id) {
		return skillRepository
				.findById(id)
				.map(skill -> {
					skill.setName(newSkill.getName());
					return this.skillRepository.save(skill);
				})
				.orElseGet(() -> {
					newSkill.setId(id);
					return this.skillRepository.save(newSkill);
				});
	}

	public Skill save(Skill skill) throws ResourceNotFoundException {
		return skillRepository.save(skill);
	}

	public void delete(long id) {
		skillRepository.deleteById(id);
	}
}

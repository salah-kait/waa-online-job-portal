package com.MIU.OnlineJob.Controllers;

import com.MIU.OnlineJob.Exception.ResourceNotFoundException;
import com.MIU.OnlineJob.Models.JobSeeker;
import com.MIU.OnlineJob.Payload.Requests.UpdateJobseekerRequest;
import com.MIU.OnlineJob.Services.JobSeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/job-seeker")
public class JobSeekerController {

	private JobSeekerService jobSeekerService;

	@Autowired
	public JobSeekerController(JobSeekerService jobSeekerService) {
		this.jobSeekerService = jobSeekerService;
	}

	public JobSeekerService getJobSeekerServices() {
		return jobSeekerService;
	}

	@GetMapping("/{id}")
	JobSeeker getJobSeeker(@PathVariable Long id) throws ResourceNotFoundException {
		return this.jobSeekerService.findById(id);
	}

	@GetMapping("/list")
	List<JobSeeker> all() {
		return this.jobSeekerService.findAll();
	}

	@PostMapping("/new")
	JobSeeker newJobSeeker(@RequestBody JobSeeker newJobSeeker) {
		return this.jobSeekerService.save(newJobSeeker);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('JOBSEEKER')")
	public JobSeeker UpdateJobSeekerInfo(@Valid @RequestBody UpdateJobseekerRequest request, @PathVariable Long id){
		JobSeeker js =  this.jobSeekerService.findById(id);
		js.setBio(request.getBio());
		js.setCurrentPosition(request.getCurrent_position());
		jobSeekerService.save(js);
		return js;
	}


	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('JOBSEEKER')")
	void deleteJobSeeker(@PathVariable Long id) {
		this.jobSeekerService.delete(id);
	}
}


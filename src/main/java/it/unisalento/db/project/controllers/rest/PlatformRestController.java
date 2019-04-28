package it.unisalento.db.project.controllers.rest;

import it.unisalento.db.project.models.dto.HiredJobsDTO;
import it.unisalento.db.project.services.JobService;
import it.unisalento.db.project.services.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/platform")
public class PlatformRestController{

	@Autowired private PlatformService platformService;
	@Autowired private JobService jobService;

	@GetMapping("/findHiredJobs")
	public List<HiredJobsDTO> findHiringJob(){
		return platformService.findHiredJobs();
	}


	@GetMapping("/checkJobs")
	public void checkJobs(){
		jobService.checkJobs();
	}

}

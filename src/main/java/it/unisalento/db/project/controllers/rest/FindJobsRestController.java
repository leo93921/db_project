package it.unisalento.db.project.controllers.rest;

import it.unisalento.db.project.services.GlassdoorJobsListService;
import it.unisalento.db.project.services.LinkedinJobsListService;
import it.unisalento.db.project.services.MonsterJobsListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("findJobs")
public class FindJobsRestController{

	@Autowired
	private LinkedinJobsListService linkedinJobsListService;

	@Autowired
	private MonsterJobsListService monsterJobsListService;

	@Autowired
	private GlassdoorJobsListService glassdoorJobsListService;

	@PostMapping(value = "/linkedin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public boolean parseLinkedInLink(@RequestBody String url) throws IOException{
		return linkedinJobsListService.saveJobs(url);
	}

	@PostMapping(value = "/monster", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public boolean parseMonsterLink(@RequestParam("url") String url) throws IOException {
		return monsterJobsListService.saveJobs(url);
	}

	@PostMapping(value = "/glassdoor", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public boolean parseGlassdoorLink(@RequestBody String url) throws Exception {
		return glassdoorJobsListService.saveJobs(url);
	}

}

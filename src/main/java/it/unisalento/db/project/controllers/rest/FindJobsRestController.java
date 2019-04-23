package it.unisalento.db.project.controllers.rest;

import it.unisalento.db.project.models.dto.MonsterJobDetails;
import it.unisalento.db.project.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

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
	public boolean parseLinkedInLink(@RequestParam("url") String url) throws IOException{
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

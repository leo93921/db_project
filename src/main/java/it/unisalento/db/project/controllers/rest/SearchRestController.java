package it.unisalento.db.project.controllers.rest;

import it.unisalento.db.project.models.dto.JobDto;
import it.unisalento.db.project.models.dto.SearchString;
import it.unisalento.db.project.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/search")
public class SearchRestController{

	@Autowired
	SearchService searchService;

	@PostMapping(value = "/requirements", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Page<JobDto> findByRequirements(@RequestBody SearchString params){
		System.out.println(params.getRequirements()[0]);
		return searchService.findAllByRequirements(params, 0);

	}



}

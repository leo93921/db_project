package it.unisalento.db.project.controllers.rest;

import it.unisalento.db.project.models.domain.Job;
import it.unisalento.db.project.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@RequestMapping("/jobs")
public class JobsRestController {

    @Autowired private JobService jobService;

    @GetMapping(value = "/findAll")
    public Page<Job> findJobs(@Param("page-start") Integer page) {
        return this.jobService.getJobs(page);
    }

}
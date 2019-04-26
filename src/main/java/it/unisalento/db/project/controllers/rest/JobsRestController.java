package it.unisalento.db.project.controllers.rest;

import it.unisalento.db.project.exceptions.JobNotFoundException;
import it.unisalento.db.project.models.dto.JobDto;
import it.unisalento.db.project.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/jobs")
public class JobsRestController {

    @Autowired private JobService jobService;

    @GetMapping
    public Page<JobDto> findJobs(@Param("page") Integer page) {
        return this.jobService.getJobs(page);
    }

    @GetMapping("/{id}")
    public JobDto getJobByID(@PathVariable("id") String id) throws JobNotFoundException {
        return this.jobService.getByID(id);
    }

    @GetMapping("/from-company/{id}")
    public Page<JobDto> findByCompany(@PathVariable("id") String id, @Param("page") Integer page) {
        return this.jobService.findByCompanyID(id, page);
    }

    @GetMapping("/count")
    public long countJobs(){
        return this.jobService.countJobs();
    }
}

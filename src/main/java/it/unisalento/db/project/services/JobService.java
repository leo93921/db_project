package it.unisalento.db.project.services;

import it.unisalento.db.project.models.domain.Job;
import it.unisalento.db.project.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class JobService {

    private final Integer PAGE_SIZE = 10;

    @Autowired
    private JobRepository jobRepository;

    // Page is zero-indexed
    public Page<Job> getJobs(Integer page) {
        List<Job> jobs = jobRepository.findAll();
        System.out.println(jobs.size());
        return this.jobRepository.findAll(PageRequest.of(page, PAGE_SIZE));
    }
}

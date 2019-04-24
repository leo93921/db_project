package it.unisalento.db.project.services;

import it.unisalento.db.project.models.domain.Job;
import it.unisalento.db.project.models.dto.JobDto;
import it.unisalento.db.project.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class JobService {

    private final Integer PAGE_SIZE = 10;

    @Autowired
    private JobRepository repository;


    public Page<JobDto> getJobs(Integer page) {
        Page<Job> jobPage = this.repository.findAll(PageRequest.of(page, PAGE_SIZE));

        List<JobDto> dtos = new ArrayList<>();
        for (Job j : jobPage.getContent()) {
            JobDto dto = new JobDto();
            dto.setId(j.get_id().toString());
            dto.setFirstVisit(j.getPosted());
            dto.setLastVisit(j.getHiringDate());
            dto.setRequirements(j.getRequirements());
            dto.setResponsibilities(j.getResponsibilities());
            dto.setLink(j.getLink());
            dtos.add(dto);
        }

        return new PageImpl<>(dtos, jobPage.getPageable(), jobPage.getTotalElements());
    }

}

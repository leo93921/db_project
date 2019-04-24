package it.unisalento.db.project.services;

import it.unisalento.db.project.exceptions.JobNotFoundException;
import it.unisalento.db.project.models.domain.Job;
import it.unisalento.db.project.models.dto.JobDto;
import it.unisalento.db.project.repository.JobRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class JobService {

    private final Integer PAGE_SIZE = 10;

    @Autowired
    private JobRepository repository;


    public Page<JobDto> getJobs(Integer page) {
        Page<Job> jobPage = repository.findAll(PageRequest.of(page, PAGE_SIZE));

        return createPageOfDto(jobPage);
    }

    public JobDto getByID(String id) throws JobNotFoundException {
        Optional<Job> byId = repository.findById(id);

        return byId.map(this::toDto).orElseThrow(JobNotFoundException::new);
    }

    public Page<JobDto> findByCompanyID(String id, Integer page) {
        Page<Job> daosByCompany = repository.findAllByCompany(new ObjectId(id), PageRequest.of(page, PAGE_SIZE));
        return createPageOfDto(daosByCompany);
    }

    private Page<JobDto> createPageOfDto(Page<Job> daosByCompany) {
        List<JobDto> dtos = new ArrayList<>();
        for (Job j : daosByCompany.getContent()) {
            JobDto dto = toDto(j);
            dtos.add(dto);
        }

        return new PageImpl<>(dtos, daosByCompany.getPageable(), daosByCompany.getTotalElements());
    }

    private JobDto toDto(Job job) {
        JobDto dto = new JobDto();
        dto.setId(job.get_id().toString());
        dto.setFirstVisit(job.getPosted());
        dto.setLastVisit(job.getHiringDate());
        dto.setRequirements(job.getRequirements());
        dto.setResponsibilities(job.getResponsibilities());
        dto.setLink(job.getLink());
        return dto;
    }
}

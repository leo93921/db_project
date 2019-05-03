package it.unisalento.db.project.services;

import it.unisalento.db.project.models.domain.Job;
import it.unisalento.db.project.models.dto.CompanyDto;
import it.unisalento.db.project.models.dto.JobDto;
import it.unisalento.db.project.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService{
	@Autowired
	JobRepository jobRepository;

	private final Integer PAGE_SIZE = 10;

	public Page<JobDto> findAllByRequirements(String[] requirements, Integer page){
		Page<Job> jobs = jobRepository.findAllByRequirements(requirements, PageRequest.of(page, PAGE_SIZE));
		System.out.println(jobs.getTotalElements());
		return createPageOfDto(jobs);
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
		dto.setName(job.getName());
		dto.setFirstVisit(job.getFirstFind());
		dto.setLastVisit(job.getUpdated());
		dto.setRequirements(job.getRequirements());
		dto.setResponsibilities(job.getResponsibilities());
		dto.setLink(job.getLink());
		if (job.getPlatform() != null) {
			dto.setPlatform(job.getPlatform().getName());
		}
		dto.setCompany(new CompanyDto(job.getCompany().get_id().toString(), job.getCompany().getName(), job.getCompany().getFirstFind()));
		return dto;
	}

}

package it.unisalento.db.project.services;

import it.unisalento.db.project.models.dto.JobDto;
import it.unisalento.db.project.models.dto.SearchString;
import it.unisalento.db.project.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class SearchService extends BaseService{
	@Autowired
	JobRepository jobRepository;

	private final Integer PAGE_SIZE = 10;

	public Page<JobDto> findAllByRequirements(SearchString params, Integer page){


		if(params.getRequirements() != null && params.getJobName() != null){
			return createPageOfDto(jobRepository.findAllByRequirementAndName(params.getRequirements(),
					params.getJobName(), PageRequest.of(page, PAGE_SIZE)));
		}else{
			if(params.getJobName() != null){
				return createPageOfDto(jobRepository.findAllByJobName(params.getJobName(), PageRequest.of(page, PAGE_SIZE)));
			}else if(params.getRequirements() != null){
				return createPageOfDto(jobRepository.findAllByRequirements(params.getRequirements(),
						PageRequest.of(page, PAGE_SIZE)));
			}
			else{
				return null;
			}
		}
	}




}

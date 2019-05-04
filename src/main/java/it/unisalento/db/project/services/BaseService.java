package it.unisalento.db.project.services;

import it.unisalento.db.project.models.data.adapter.MongoAdapter;
import it.unisalento.db.project.models.domain.Company;
import it.unisalento.db.project.models.domain.Job;
import it.unisalento.db.project.models.domain.Location;
import it.unisalento.db.project.models.domain.Platform;
import it.unisalento.db.project.models.dto.CompanyDto;
import it.unisalento.db.project.models.dto.JobDto;
import it.unisalento.db.project.repository.CompanyRepository;
import it.unisalento.db.project.repository.JobRepository;
import it.unisalento.db.project.repository.LocationRepository;
import it.unisalento.db.project.repository.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class BaseService{

	@Autowired
	private JobRepository jobRepository;
	@Autowired
	private LocationRepository locationRepository;
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private PlatformRepository platformRepository;



	BaseService(){}

	boolean saveJobs(List<MongoAdapter> mongoAdapterList) {

		if(mongoAdapterList.size() == 0) return false;

		try{
			for(MongoAdapter job : Objects.requireNonNull(mongoAdapterList)){

				if(job.getCompany() !=  null && job.getJob() != null &&
						job.getLocation() != null && job.getPlatform() != null){

					Platform platform = platformRepository.findByName(job.getPlatform().getName());
					if(platform == null) platform = platformRepository.save(job.getPlatform());

					Company company = companyRepository.findByName(job.getCompany().getName());
					if(company == null){
						company = companyRepository.save(new Company(job.getCompany().getName(), new Date()));
					}

					Location location = locationRepository.findByName(job.getLocation().getName());
					if(location == null) location = locationRepository.save(job.getLocation());

					Job existingJob = jobRepository.findByPlatformAndLink(platform, job.getJob().getLink());
					if(existingJob != null){
						existingJob.setUpdated(new Date());
						jobRepository.save(existingJob);
					}else{
						jobRepository.save(new Job(job.getJob().getPosted(), job.getJob().getName(), null,
								location, platform, company, job.getJob().getResponsibilities(),
								job.getJob().getRequirements(), job.getJob().getLink(), new Date(), new Date(),
								job.getJob().getDescription()));
					}

				}

			}

			return true;

		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	Page<JobDto> createPageOfDto(Page<Job> daosByCompany) {
		List<JobDto> dtos = new ArrayList<>();
		for(Job j : daosByCompany.getContent()){
			JobDto dto = toDto(j);
			dtos.add(dto);
		}
		return new PageImpl<>(dtos, daosByCompany.getPageable(), daosByCompany.getTotalElements());
	}

	JobDto toDto(Job job) {
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
		dto.setDescription(job.getDescription());
		dto.setCompany(new CompanyDto(job.getCompany().get_id().toString(), job.getCompany().getName(), job.getCompany().getFirstFind()));
		return dto;
	}

}

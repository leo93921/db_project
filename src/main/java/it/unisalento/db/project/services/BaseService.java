package it.unisalento.db.project.services;

import it.unisalento.db.project.models.data.adapter.MongoAdapter;
import it.unisalento.db.project.models.domain.Company;
import it.unisalento.db.project.models.domain.Job;
import it.unisalento.db.project.models.domain.Location;
import it.unisalento.db.project.models.domain.Platform;
import it.unisalento.db.project.repository.CompanyRepository;
import it.unisalento.db.project.repository.JobRepository;
import it.unisalento.db.project.repository.LocationRepository;
import it.unisalento.db.project.repository.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	void saveJobs(List<MongoAdapter> mongoAdapterList) {

		for(MongoAdapter job: Objects.requireNonNull(mongoAdapterList)) {

			Platform platform = platformRepository.findByName(job.getPlatform().getName());
			if(platform == null) platform = platformRepository.save(job.getPlatform());

			Company company = companyRepository.findByName(job.getCompany().getName());
			if(company == null) company = companyRepository.save(job.getCompany());

			Location location = locationRepository.findByName(job.getLocation().getName());
			if(location == null) location = locationRepository.save(job.getLocation());

			System.out.println(job.getLocation().getName());
			System.out.println(job.getPlatform().getName());
			System.out.println(job.getCompany().getName());
			jobRepository.save(new Job(job.getJob().getPosted(), null,
					location.get_id(), platform.get_id(), company.get_id(), job.getJob().getResponsibilities(),
					job.getJob().getRequirements(), job.getJob().getLink()));

		}

	}

}

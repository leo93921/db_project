package it.unisalento.db.project.repository;

import it.unisalento.db.project.models.domain.Job;
import it.unisalento.db.project.models.domain.Platform;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("job-repository")
public interface JobRepository extends MongoRepository<Job, String>{

    Page<Job> findAllByCompany(ObjectId id, PageRequest of);
	Job findByPlatformAndLink(Platform platform, String link);
	long countByCompany(ObjectId id);

	@Query(value = "{hiringDate: {$exists: true}}")
	Page<Job> findHiredJobs(PageRequest of);
}

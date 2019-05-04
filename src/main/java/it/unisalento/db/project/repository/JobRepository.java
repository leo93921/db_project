package it.unisalento.db.project.repository;

import it.unisalento.db.project.models.domain.Job;
import it.unisalento.db.project.models.domain.Platform;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("job-repository")
public interface JobRepository extends MongoRepository<Job, String>{

    Page<Job> findAllByCompany(ObjectId id, PageRequest of);
	Job findByPlatformAndLink(Platform platform, String link);
	long countByCompany(ObjectId id);

	@Query(value = "{hiringDate: {$exists: true}}")
	Page<Job> findHiredJobs(PageRequest of);

	@Query(value = "{requirements: {$all: ?0}, name: {$regex: ?1, $options: \"i\"}}")
	Page<Job> findAllByRequirementAndName(String[] query1, String query2,  PageRequest of);

	@Query(value = "{requirements: {$all: ?0}}")
	Page<Job> findAllByRequirements(String[] requirements, PageRequest of);

	@Query(value = "{name: {$regex: ?0, $options: \"i\"}}")
	Page<Job> findAllByJobName(String jobName, PageRequest of);
}

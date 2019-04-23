package it.unisalento.db.project.repository;

import it.unisalento.db.project.models.domain.Job;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("job-repository")
public interface JobRepository extends MongoRepository<Job, String>{

}

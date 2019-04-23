package it.unisalento.db.project.repository;

import it.unisalento.db.project.models.domain.Platform;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("platform-repository")
public interface PlatformRepository extends MongoRepository<Platform, String>{
	Platform findByName(String name);
}

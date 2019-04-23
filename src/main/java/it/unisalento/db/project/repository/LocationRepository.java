package it.unisalento.db.project.repository;

import it.unisalento.db.project.models.domain.Location;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("location-repository")
public interface LocationRepository extends MongoRepository<Location, String>{
	Location findByName(String name);
}

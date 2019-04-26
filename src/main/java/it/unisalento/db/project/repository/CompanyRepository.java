package it.unisalento.db.project.repository;

import it.unisalento.db.project.models.domain.Company;
import it.unisalento.db.project.models.dto.CompanyDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("company-repository")
public interface CompanyRepository extends MongoRepository<Company, String>{
	Company findByName(String name);
}

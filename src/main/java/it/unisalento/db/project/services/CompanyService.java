package it.unisalento.db.project.services;

import it.unisalento.db.project.models.domain.Company;
import it.unisalento.db.project.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final Integer PAGE_SIZE = 10;

    @Autowired private CompanyRepository repository;

    public Page<Company> findAll(Integer page) {
        return this.repository.findAll(PageRequest.of(page, PAGE_SIZE));
    }
}

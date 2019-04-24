package it.unisalento.db.project.controllers.rest;

import it.unisalento.db.project.models.domain.Company;
import it.unisalento.db.project.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/companies")
public class CompaniesRestController {

    @Autowired private CompanyService companyService;

    @GetMapping
    public Page<Company> getAllCompanies(@Param("page") Integer page) {
        return this.companyService.findAll(page);
    }
}

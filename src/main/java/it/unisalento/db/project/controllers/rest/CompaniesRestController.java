package it.unisalento.db.project.controllers.rest;

import it.unisalento.db.project.exceptions.CompanyNotFoundException;
import it.unisalento.db.project.models.dto.CompanyDto;
import it.unisalento.db.project.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/companies")
public class CompaniesRestController {

    @Autowired private CompanyService companyService;

    @GetMapping
    public Page<CompanyDto> getAllCompanies(@Param("page") Integer page) {
        return this.companyService.findAll(page);
    }

    @GetMapping("/{id}")
    public  CompanyDto getById(@PathVariable("id") String id) throws CompanyNotFoundException {
        return this.companyService.findById(id);
    }
}

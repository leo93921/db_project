package it.unisalento.db.project.controllers.rest;

import it.unisalento.db.project.exceptions.CompanyNotFoundException;
import it.unisalento.db.project.models.dto.CompanyDto;
import it.unisalento.db.project.models.dto.CompanyWithJobsCountDto;
import it.unisalento.db.project.models.dto.TrackingHistoryItemDto;
import it.unisalento.db.project.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/companies")
public class CompaniesRestController {

    @Autowired private CompanyService companyService;

    @GetMapping
    public Page<CompanyWithJobsCountDto> getAllCompanies(@Param("page") Integer page) {
        return this.companyService.findAllWithCount(page);
    }

    @GetMapping("/{id}")
    public  CompanyDto getById(@PathVariable("id") String id) throws CompanyNotFoundException {
        return this.companyService.findById(id);
    }

    @GetMapping("/count/{id}")
    public long countCompaniesInsertion(@PathVariable("id") String id){
        return this.companyService.countCompanyInsertion(id);
    }

    @GetMapping("/count")
    public long countCompany(){
        return this.companyService.countCompany();
    }

    @GetMapping("/history")
    public List<TrackingHistoryItemDto> getHistory() {
        return this.companyService.getCompaniesHistory();
    }
}

package it.unisalento.db.project.services;

import it.unisalento.db.project.exceptions.CompanyNotFoundException;
import it.unisalento.db.project.models.domain.Company;
import it.unisalento.db.project.models.dto.CompanyDto;
import it.unisalento.db.project.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final Integer PAGE_SIZE = 10;

    @Autowired private CompanyRepository repository;

    public Page<CompanyDto> findAll(Integer page) {
        Page<Company> daoPage = this.repository.findAll(PageRequest.of(page, PAGE_SIZE));

        List<CompanyDto> dtos = new ArrayList<>();

        for (Company c : daoPage.getContent()) {
            CompanyDto dto = toDto(c);
            dtos.add(dto);
        }

        return new PageImpl<>(dtos, daoPage.getPageable(), daoPage.getTotalElements());
    }

    public CompanyDto findById(String id) throws CompanyNotFoundException {
        Optional<Company> dao = this.repository.findById(id);

        return dao.map(this::toDto).orElseThrow(CompanyNotFoundException::new);
    }

    private CompanyDto toDto(Company dao) {
        CompanyDto dto = new CompanyDto();
        dto.setId(dao.get_id().toString());
        dto.setName(dao.getName());
        return dto;
    }
}

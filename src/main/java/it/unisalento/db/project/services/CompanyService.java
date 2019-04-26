package it.unisalento.db.project.services;

import it.unisalento.db.project.exceptions.CompanyNotFoundException;
import it.unisalento.db.project.models.domain.Company;
import it.unisalento.db.project.models.domain.Job;
import it.unisalento.db.project.models.dto.CompanyDto;
import it.unisalento.db.project.models.dto.CompanyWithJobsCountDto;
import it.unisalento.db.project.repository.CompanyRepository;
import it.unisalento.db.project.repository.JobRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final Integer PAGE_SIZE = 10;

    @Autowired private CompanyRepository repository;
    @Autowired private MongoTemplate mongoTemplate;
    @Autowired private JobRepository jobRepository;

    public Page<CompanyDto> findAll(Integer page) {
        Page<Company> daoPage = this.repository.findAll(PageRequest.of(page, PAGE_SIZE));

        List<CompanyDto> dtos = new ArrayList<>();

        for (Company c : daoPage.getContent()) {
            CompanyDto dto = toDto(c);
            dtos.add(dto);
        }

        return new PageImpl<>(dtos, daoPage.getPageable(), daoPage.getTotalElements());
    }

    public Page<CompanyWithJobsCountDto> findAllWithCount(Integer page) {
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.group("company").count().as("jobCount"),
                Aggregation.project("jobCount").and(
                        ArrayOperators.ArrayElemAt.arrayOf(
                                ObjectOperators.ObjectToArray.valueOfToArray("_id")
                        ).elementAt(1)
                ).as("companyId"),
                Aggregation.lookup("Company", "companyId.v", "_id", "companyInfo"),
                Aggregation.project("jobCount").and(
                        ArrayOperators.ArrayElemAt.arrayOf("companyInfo").elementAt(0)
                ).as("company"),
                Aggregation.project("jobCount").and("company.name").as("name").and("company._id").as("_id"),
                Aggregation.skip(new Long(page * PAGE_SIZE)),
                Aggregation.limit(PAGE_SIZE)
        );
        AggregationResults<CompanyWithJobsCountDto> results = mongoTemplate.aggregate(agg, Job.class, CompanyWithJobsCountDto.class);

        long count = repository.count();

        return new PageImpl<>(results.getMappedResults(), PageRequest.of(page, PAGE_SIZE), count);
    }

    public CompanyDto findById(String id) throws CompanyNotFoundException {
        Optional<Company> dao = this.repository.findById(id);

        return dao.map(this::toDto).orElseThrow(CompanyNotFoundException::new);
    }

    public long countCompanyInsertion(String id){
        return jobRepository.countByCompany(new ObjectId(id));
    }

    public long countCompany(){
        return repository.count();
    }

    private CompanyDto toDto(Company dao) {
        CompanyDto dto = new CompanyDto();
        dto.setId(dao.get_id().toString());
        dto.setName(dao.getName());
        dto.setFirstVisit(dao.getFirstFind());
        return dto;
    }
}

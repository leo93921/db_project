package it.unisalento.db.project.services;

import it.unisalento.db.project.exceptions.JobNotFoundException;
import it.unisalento.db.project.models.domain.Job;
import it.unisalento.db.project.models.dto.GlassdoorJobDetail;
import it.unisalento.db.project.models.dto.JobDto;
import it.unisalento.db.project.models.dto.MonsterJobDetails;
import it.unisalento.db.project.models.dto.TrackingHistoryItemDto;
import it.unisalento.db.project.repository.JobRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class JobService extends BaseService{

    private final Integer PAGE_SIZE = 10;

    @Autowired
    private JobRepository repository;
    @Autowired private TrackingHistoryService trackingHistoryService;

    @Autowired private MonsterParserService monsterParserService;
    @Autowired private GlassdoorParserService glassdoorParserService;
    @Autowired private LinkedinParserService linkedinParserService;

    public Page<JobDto> getJobs(Integer page) {
        Page<Job> jobPage = repository.findAll(PageRequest.of(page, PAGE_SIZE));

        return createPageOfDto(jobPage);
    }

    public JobDto getByID(String id) throws JobNotFoundException {
        Optional<Job> byId = repository.findById(id);

        return byId.map(this::toDto).orElseThrow(JobNotFoundException::new);
    }

    public Page<JobDto> findByCompanyID(String id, Integer page) {
        Page<Job> daosByCompany = repository.findAllByCompany(new ObjectId(id), PageRequest.of(page, PAGE_SIZE));
        return createPageOfDto(daosByCompany);
    }


    public List<TrackingHistoryItemDto> getJobHistory() {
        return trackingHistoryService.getHistory("Job");
    }


    public long countJobs(){
        return this.repository.count();
    }


    public void checkJobs(){
        List<Job> jobs = repository.findAll();
        for(Job job: jobs){
            System.out.println(job.getPlatform().getName());

            if(job.getHiringDate() != null){
                switch(job.getPlatform().getName()){
                    case "Monster":
                        try{
                            MonsterJobDetails monsterJob = monsterParserService.parse(job.getLink());
                            System.out.println("monster: " + monsterJob);
                            if(monsterJob == null){
                                job.setHiringDate(new Date());
                                repository.save(job);
                            }else{
                                job.setUpdated(new Date());
                                repository.save(job);
                            }

                        }catch(Exception e){
                            System.err.println("errore");
                        }
                        break;

                    case "Linkedin":
                        try{
                            Map<String, String> linkedinJob =
                                    linkedinParserService.parse(job.getPlatform().getSearchBaseUrl() + job.getLink());

                            System.out.println("linkedin: " + linkedinJob.isEmpty());

                            if(! linkedinJob.isEmpty()){
                                job.setUpdated(new Date());
                                repository.save(job);
                            }
                        }catch(Exception e){
                            System.err.println("errore");
                            job.setHiringDate(new Date());
                            repository.save(job);
                            break;
                        }
                        break;

                    case "Glassdoor":
                        try{
                            GlassdoorJobDetail glassdoorJob = glassdoorParserService.parse(job.getLink());
                            System.out.println("glassdoor: " + glassdoorJob);
                            if(glassdoorJob == null){
                                job.setHiringDate(new Date());
                                repository.save(job);
                            }else{
                                job.setUpdated(new Date());
                                repository.save(job);
                            }
                        }catch(Exception e){
                            System.err.println("errore");
                        }
                        break;

                }
            }
        }
    }

}

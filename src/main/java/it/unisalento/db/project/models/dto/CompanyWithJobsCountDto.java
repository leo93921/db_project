package it.unisalento.db.project.models.dto;

import java.util.Date;

public class CompanyWithJobsCountDto {

    private String _id;
    private String name;
    private Integer jobCount;
    private Date firstVisit;

    public String  get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getJobCount() {
        return jobCount;
    }

    public void setJobCount(Integer jobCount) {
        this.jobCount = jobCount;
    }

    public Date getFirstVisit(){
        return firstVisit;
    }

    public void setFirstVisit(Date firstVisit){
        this.firstVisit = firstVisit;
    }
}

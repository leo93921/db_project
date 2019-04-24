package it.unisalento.db.project.models.dto;

import java.util.Date;

public class CompanyDto {

    private String id;
    private String name;
    private Date firstVisit;

    public CompanyDto() {
    }

    public CompanyDto(String id, String name, Date firstVisit) {
        this.id = id;
        this.name = name;
        this.firstVisit = firstVisit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFirstVisit() {
        return firstVisit;
    }

    public void setFirstVisit(Date firstVisit) {
        this.firstVisit = firstVisit;
    }
}

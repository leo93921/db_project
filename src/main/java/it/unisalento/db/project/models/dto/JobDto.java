package it.unisalento.db.project.models.dto;

import java.util.Date;

public class JobDto {

    private String id;
    private Date firstVisit;
    private Date lastVisit;
    private String responsibilities;
    private String requirements;
    private String link;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFirstVisit() {
        return firstVisit;
    }

    public void setFirstVisit(Date firstVisit) {
        this.firstVisit = firstVisit;
    }

    public Date getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(Date lastVisit) {
        this.lastVisit = lastVisit;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

package it.unisalento.db.project.models.dto;

public class JobSearchQuery {
	private String[] requirements;
	private String jobName;

	public String[] getRequirements(){
		return requirements;
	}

	public void setRequirements(String[] requirements){
		this.requirements = requirements;
	}

	public String getJobName(){
		return jobName;
	}

	public void setJobName(String jobName){
		this.jobName = jobName;
	}
}
package it.unisalento.db.project.models.dto;

public class JobsFoundPerPlatformDTO{

	private Integer jobsFound;
	private String _id;
	private String platformName;

	public Integer getJobsFound(){
		return jobsFound;
	}

	public void setJobsFound(Integer jobsFound){
		this.jobsFound = jobsFound;
	}

	public String get_id(){
		return _id;
	}

	public void set_id(String _id){
		this._id = _id;
	}

	public String getPlatformName(){
		return platformName;
	}

	public void setPlatformName(String platformName){
		this.platformName = platformName;
	}
}

package it.unisalento.db.project.models.dto;

public class HiredJobsDTO{
	private String days;
	private String _id;
	private Integer hiredJobs;
	private String platformName;


	public String getdays(){
		return days;
	}

	public void setdays(String weeks){
		this.days = weeks;
	}

	public String get_id(){
		return _id;
	}

	public void set_id(String _id){
		this._id = _id;
	}

	public Integer getHiredJobs(){
		return hiredJobs;
	}

	public void setHiredJobs(Integer hiredJobs){
		this.hiredJobs = hiredJobs;
	}

	public String getPlatformName(){
		return platformName;
	}

	public void setPlatformName(String platformName){
		this.platformName = platformName;
	}
}

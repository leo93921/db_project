package it.unisalento.db.project.models.data.adapter;

import it.unisalento.db.project.models.domain.Company;
import it.unisalento.db.project.models.domain.Job;
import it.unisalento.db.project.models.domain.Location;
import it.unisalento.db.project.models.domain.Platform;

public interface MongoDataAdapter{
	public Company getCompany();
	public Job getJob();
	public Location getLocation();
	public Platform getPlatform();
}

package it.unisalento.db.project.models.data.adapter;

import it.unisalento.db.project.models.domain.Company;
import it.unisalento.db.project.models.domain.Job;
import it.unisalento.db.project.models.domain.Location;
import it.unisalento.db.project.models.domain.Platform;

import java.text.ParseException;

public interface IAdapter{
	Company getCompany();
	Job getJob() throws ParseException;
	Location getLocation();
	Platform getPlatform();
}

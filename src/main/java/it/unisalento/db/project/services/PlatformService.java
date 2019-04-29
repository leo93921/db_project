package it.unisalento.db.project.services;

import it.unisalento.db.project.models.domain.Job;
import it.unisalento.db.project.models.dto.HiredJobsDTO;
import it.unisalento.db.project.models.dto.JobsFoundPerPlatformDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatformService{

	@Autowired
	private MongoTemplate mongoTemplate;


	public List<HiredJobsDTO> findHiredJobs(){

		int millisecondsToDay = 1000*3600*24;

		Aggregation agg = Aggregation.newAggregation(
				Aggregation.match(new Criteria("hiringDate").exists(true)),
				Aggregation.project( "platform").and(
						ArithmeticOperators.Divide
								.valueOf(ArithmeticOperators.Subtract.valueOf("hiringDate").subtract("firstFind"))
								.divideBy(millisecondsToDay)).as("daysLeft"),
				Aggregation.project( "platform", "daysLeft").and(
						ConditionalOperators.Cond.when(
								ComparisonOperators.Gte.valueOf("daysLeft").greaterThanEqualToValue(30)
						).then("30+")
								.otherwise(ConditionalOperators.Cond.when(
										ComparisonOperators.Gte.valueOf("daysLeft").greaterThanEqualToValue(14)
								).then("15-29")
										.otherwise(ConditionalOperators.Cond.when(
												ComparisonOperators.Gte.valueOf("daysLeft").greaterThanEqualToValue(8)
										).then("8-14")
												.otherwise(ConditionalOperators.Cond.when(
														ComparisonOperators.Gte.valueOf("daysLeft").greaterThanEqualToValue(0)
												).then("1-7")
														.otherwise("NA"))))
				).as("days"),
				Aggregation.group("days", "platform").count().as("hiredJobs"),
				Aggregation.project("hiredJobs").and(
						ArrayOperators.ArrayElemAt.arrayOf(ObjectOperators.ObjectToArray.valueOfToArray("_id.platform"))
								.elementAt(1))
						.as("platformId")
						.and("_id.days").as("days"),
				Aggregation.lookup("Platform","platformId.v","_id","platName"),
				Aggregation.project( "hiredJobs", "days").and(
						ArrayOperators.ArrayElemAt.arrayOf("platName.name").elementAt(0)).as("platformName")
						.and(ArrayOperators.ArrayElemAt.arrayOf("platName._id").elementAt(0)).as("_id")
		);

		AggregationResults<HiredJobsDTO> results = mongoTemplate.aggregate(agg, Job.class, HiredJobsDTO.class);

		return results.getMappedResults();
	}


	public List<JobsFoundPerPlatformDTO> findJobsPerPlatform(){
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.group("platform").count().as("jobsFound"),
				Aggregation.project("jobsFound").and(
						ArrayOperators.ArrayElemAt.arrayOf(ObjectOperators.ObjectToArray.valueOfToArray("_id"))
								.elementAt(1))
						.as("platformId"),
				Aggregation.lookup("Platform","platformId.v","_id","platName"),
				Aggregation.project("jobsFound").and(
						ArrayOperators.ArrayElemAt.arrayOf("platName.name").elementAt(0)).as("platformName")
				.and("platformId.v").as("_id")
		);

		AggregationResults<JobsFoundPerPlatformDTO> results =
				mongoTemplate.aggregate(aggregation, Job.class, JobsFoundPerPlatformDTO.class);


		return results.getMappedResults();
	}

}

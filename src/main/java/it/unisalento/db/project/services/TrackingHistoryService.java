package it.unisalento.db.project.services;

import it.unisalento.db.project.models.dto.TrackingHistoryItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackingHistoryService {

    @Autowired private MongoTemplate mongoTemplate;

    public List<TrackingHistoryItemDto> getHistory(String collectionName) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.project("_id")
                        .andExpression("dayOfMonth(firstFind)").as("day")
                        .andExpression("month(firstFind)").as("month")
                        .andExpression("year(firstFind)").as("year"),
                Aggregation.group(Aggregation.fields().and("day").and("month").and("year"))
                        .count().as("count"),
                Aggregation.project("count")
                        .and(DateOperators.DateFromParts.dateFromParts()
                                .yearOf("_id.year").monthOf("_id.month").dayOf("_id.day")).as("date"),
                Aggregation.sort(Sort.Direction.ASC, "date")
        );

        AggregationResults<TrackingHistoryItemDto> aggregationResults = mongoTemplate.aggregate(aggregation, collectionName, TrackingHistoryItemDto.class);

        return aggregationResults.getMappedResults();
    }
}

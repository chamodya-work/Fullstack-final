package com.movie.fullstack.repository;

import com.movie.fullstack.model.TVSeries;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TVSeriesRepository extends MongoRepository<TVSeries, String> {
}

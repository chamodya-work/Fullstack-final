package com.movie.fullstack.service;

import com.movie.fullstack.model.DatabaseSequence;
import com.movie.fullstack.model.TVSeries;
import com.movie.fullstack.repository.TVSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;


@Service// Marks this class as a Spring service component
public class TVSeriesService {
    @Autowired
    private TVSeriesRepository tvSeriesRepository;

     /**
     * Adds a new TV series to the database.
     * Generates a unique sequence ID before saving.
     * @param tvSeries - TVSeries object to be added
     * @return Saved TVSeries object
     */
    public TVSeries addSeries(TVSeries tvSeries){
        tvSeries.setSeries_id(generateSequence(TVSeries.SEQUENCE_NAME));
        return tvSeriesRepository.save(tvSeries);
    }

    public List<TVSeries> getAllSeries(){
        return tvSeriesRepository.findAll();
    }

     /**
     * Retrieves a specific TV series by its ID.
     * Throws a RuntimeException if the series is not found.
     * @param series_id - The unique ID of the TV series
     * @return TVSeries object if found
     */
    public TVSeries getSeriesById(String series_id){
        Optional<TVSeries> tvSeries = tvSeriesRepository.findById(series_id);
        if(tvSeries.isPresent()){
            return tvSeries.get();
        }
        else{
            throw new RuntimeException();
        }
    }

     /**
     * Generates a unique sequence ID for TVSeries.
     * Uses MongoDB's atomic update operation to maintain unique sequential IDs.
     * @param seqName - Sequence name for TVSeries
     * @return Generated sequence ID prefixed with "T"
     */

    @Autowired
    private MongoOperations mongoOperations;

    public String generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return "T" + (!Objects.isNull(counter) ? counter.getSeq() : 1);
    }
}

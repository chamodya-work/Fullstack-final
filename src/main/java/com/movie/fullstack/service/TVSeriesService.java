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

@Service
public class TVSeriesService {
    @Autowired
    private TVSeriesRepository tvSeriesRepository;

    public TVSeries addSeries(TVSeries tvSeries){
        tvSeries.setSeries_id(generateSequence(TVSeries.SEQUENCE_NAME));
        return tvSeriesRepository.save(tvSeries);
    }

    public List<TVSeries> getAllSeries(){
        return tvSeriesRepository.findAll();
    }

    public TVSeries getSeriesById(String series_id){
        Optional<TVSeries> tvSeries = tvSeriesRepository.findById(series_id);
        if(tvSeries.isPresent()){
            return tvSeries.get();
        }
        else{
            throw new RuntimeException();
        }
    }

    @Autowired
    private MongoOperations mongoOperations;

    public String generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return "T" + (!Objects.isNull(counter) ? counter.getSeq() : 1);
    }
}

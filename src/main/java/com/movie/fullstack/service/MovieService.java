package com.movie.fullstack.service;

import com.movie.fullstack.model.DatabaseSequence;
import com.movie.fullstack.model.Movie;
import com.movie.fullstack.repository.MovieRepository;
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
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie addMovie(Movie movie){
        movie.setMovie_id(generateSequence(Movie.SEQUENCE_NAME));
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public Movie getMovieById(String movie_id){
        Optional<Movie> movie = movieRepository.findById(movie_id);
        if(movie.isPresent()){
            return movie.get();
        }
        else{
            throw new RuntimeException();
        }
    }

//    public Movie getMovieById(String movie_id){
//        Movie existingMovie = movieRepository.findById(movie_id).orElseThrow(RuntimeException::new);
//        existingMovie =
//    }

    @Autowired
    private MongoOperations mongoOperations;

    public String generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return "M" + (!Objects.isNull(counter) ? counter.getSeq() : 1);
    }
}

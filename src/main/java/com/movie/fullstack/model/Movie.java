package com.movie.fullstack.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The Movie class represents a movie entity in the application.
 * It extends the Content class, inheriting its properties.
 */

@EqualsAndHashCode(callSuper = true)
@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie extends Content{

     /**
     * A transient field that defines the sequence name for generating unique IDs.
     * This field is not persisted in the database.
     */
    @Transient
    public static final String SEQUENCE_NAME = "movie_sequence";

    @Id
    private String movie_id;
    public int watch_time;
}


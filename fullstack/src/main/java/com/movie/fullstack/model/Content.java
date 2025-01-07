package com.movie.fullstack.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Content {
    public String name;
    public String overview;
    private String[] genres;
    private String backdrop_path;
    private String poster_path;
    public boolean adult;
    public String original_language;
    public String release_date;
    private float vote_average;
    public String trailer;
}


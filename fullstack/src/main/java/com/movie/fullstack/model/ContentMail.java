package com.movie.fullstack.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContentMail {
    private String subject;
    private String name;

    private String nameContent;
    private String Content;
    private String overview;
    private String original_language;
    private String release_date;
    private float vote_average;
    private String trailer;
}


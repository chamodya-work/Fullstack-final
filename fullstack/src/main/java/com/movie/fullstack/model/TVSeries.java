package com.movie.fullstack.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TVSeries extends Content{

    @Transient
    public static final String SEQUENCE_NAME = "tvSeries_sequence";

    @Id
    private String series_id;
    public int num_of_seasons;
    public int num_of_episodes;
}

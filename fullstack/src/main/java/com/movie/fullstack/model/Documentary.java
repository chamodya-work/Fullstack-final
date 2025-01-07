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
public class Documentary extends Content{

    @Transient
    public static final String SEQUENCE_NAME = "documentary_sequence";

    @Id
    private String doc_id;
    public int watch_time;
}


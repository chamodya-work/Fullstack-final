package com.movie.fullstack.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@Document(collection = "reviews")
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Transient
    public static final String SEQUENCE_NAME = "review_sequence";

    @Id
    private String review_id;

    private String user_id;
    private String contentId;
    private String reviewText;
    private LocalDate timeStamp;
}
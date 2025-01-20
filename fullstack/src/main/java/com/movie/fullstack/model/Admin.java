package com.movie.fullstack.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Admin extends User{

    @Transient
    public static final String SEQUENCE_NAME="admin_sequence";

    @Id
    private String admin_id;

}


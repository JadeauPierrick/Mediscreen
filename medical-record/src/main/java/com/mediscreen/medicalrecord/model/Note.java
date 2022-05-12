package com.mediscreen.medicalrecord.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "note")
public class Note implements Serializable {

    @Transient
    public static final String SEQUENCE_NAME = "notes_sequence";

    @Id
    private long id;

    @NotNull(message = "The patient id is required")
    @Field(name = "patientId")
    private int patientId;

    @CreatedDate
    @Field(name = "createdAt")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Field(name = "updatedAt")
    private LocalDateTime updatedAt;

    @NotBlank(message = "The content is required")
    @Field(name = "content")
    private String content;
}

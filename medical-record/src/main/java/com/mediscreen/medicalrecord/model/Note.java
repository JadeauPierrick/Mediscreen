package com.mediscreen.medicalrecord.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "note")
public class Note {

    @Transient
    public static final String SEQUENCE_NAME = "notes_sequence";

    @Id
    private int id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private int patientId;

    private String content;
}

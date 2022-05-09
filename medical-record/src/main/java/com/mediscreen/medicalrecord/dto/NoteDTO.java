package com.mediscreen.medicalrecord.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NoteDTO implements Serializable {

    long id;

    int patientId;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    String content;
}

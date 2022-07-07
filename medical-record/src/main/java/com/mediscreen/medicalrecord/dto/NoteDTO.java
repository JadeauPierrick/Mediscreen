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

    private static final long serialVersionUID = 1L;

    Long id;

    int patientId;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    String content;
}

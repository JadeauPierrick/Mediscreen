package com.mediscreen.patient.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mediscreen.patient.constants.Gender;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientDTO implements Serializable {

    int id;

    @NotBlank
    String lastName;

    @NotBlank
    String firstName;

    @NotBlank
    LocalDate birthdate;

    @NotBlank
    Gender sex;

    String address;

    String phone;
}

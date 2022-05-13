package com.mediscreen.assessment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mediscreen.assessment.constants.Gender;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientDTO {

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

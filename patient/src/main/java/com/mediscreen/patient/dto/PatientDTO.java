package com.mediscreen.patient.dto;

import com.mediscreen.patient.constants.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

    private int id;

    private String lastName;

    private String firstName;

    private LocalDate birthdate;

    private Gender sex;

    private String address;

    private String phone;
}

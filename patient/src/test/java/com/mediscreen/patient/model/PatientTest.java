package com.mediscreen.patient.model;

import com.mediscreen.patient.constants.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class PatientTest {

    private Patient patient;


    @Test
    public void testPatient(){
        patient = new Patient(11L,"Dale","Jacob", LocalDate.now(), Gender.M,"80 St Road","888-000-000");

        assertThat(patient.getLastName()).isEqualTo("Dale");
        assertThat(patient.getFirstName()).isEqualTo("Jacob");
        assertThat(patient.getBirthdate()).isEqualTo(LocalDate.now());
        assertThat(patient.getSex()).isEqualTo(Gender.M);
        assertThat(patient.getAddress()).isEqualTo("80 St Road");
        assertThat(patient.getPhone()).isEqualTo("888-000-000");
    }
}

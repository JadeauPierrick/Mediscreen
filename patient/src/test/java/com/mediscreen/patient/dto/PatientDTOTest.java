package com.mediscreen.patient.dto;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PatientDTOTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.simple().forClass(PatientDTO.class).verify();
    }
}




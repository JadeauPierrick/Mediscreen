package com.mediscreen.medicalrecord.dto;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NoteDTOTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.simple().forClass(NoteDTO.class).verify();
    }
}

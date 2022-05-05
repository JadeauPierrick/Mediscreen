package com.mediscreen.patient.service;

import com.mediscreen.patient.constants.Gender;
import com.mediscreen.patient.dto.PatientDTO;
import com.mediscreen.patient.exceptions.PatientAlreadyExistingException;
import com.mediscreen.patient.exceptions.PatientNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    private PatientDTO patient;

    @BeforeAll
    private void setUp() {
        patient = new PatientDTO();
        patient.setLastName("Garrix");
        patient.setFirstName("Martin");
        patient.setBirthdate(LocalDate.of(1996, 5,14));
        patient.setSex(Gender.M);
    }

    @Test
    @Order(1)
    public void addPatient() throws PatientAlreadyExistingException {
        patientService.addPatient(patient);
        List<PatientDTO> patients = patientService.getPatients();

        assertNotNull(patient.getId());
        assertThat(patients.get(5).getLastName()).isEqualTo("Garrix");
    }

    @Test
    @Order(2)
    public void savePatientWithExistingPatient() {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setLastName("Cartier");
        patientDTO.setFirstName("Denis");
        patientDTO.setBirthdate(LocalDate.of(1982,5,3));
        patientDTO.setSex(Gender.M);

        try {
            patientService.addPatient(patientDTO);
        } catch (PatientAlreadyExistingException e) {
            assertThat(e.getMessage()).contains("The patient is already exist");
        }
    }

    @Test
    @Order(3)
    public void getPatients() {
        List<PatientDTO> patients = patientService.getPatients();
        assertThat(patients.size()).isEqualTo(6);
    }

    @Test
    @Order(4)
    public void getPatientById() throws PatientNotFoundException {
        PatientDTO patientDTO = patientService.getPatientById(6);
        assertThat(patientDTO.getId()).isEqualTo(6);
    }

    @Test
    @Order(5)
    public void getPatientWithNullId() {
        try {
            patientService.getPatientById(10);
        } catch (PatientNotFoundException e) {
            assertThat(e.getMessage()).contains("The patient with the id : " + 10 + " was not found");
        }
    }

    @Test
    @Order(6)
    public void getPatientByLastNameAndFirstNameAndBirthdate() throws PatientNotFoundException {
        PatientDTO patientDTO = patientService.getPatientByLastNameAndFirstNameAndBirthdate(patient.getLastName(), patient.getFirstName(), patient.getBirthdate());
        assertThat(patientDTO.getFirstName()).isEqualTo(patient.getFirstName());
    }

    @Test
    @Order(7)
    public void getPatientByLastNameAndFirstNameAndBirthdateWithNullLastName() {
        try {
            patientService.getPatientByLastNameAndFirstNameAndBirthdate("Carment", patient.getLastName(), patient.getBirthdate());
        } catch (PatientNotFoundException e) {
            assertThat(e.getMessage()).contains("The patient with these informations : " + "Carment" + " " + patient.getLastName() + " " + patient.getBirthdate() + " was not found");
        }
    }

    @Test
    @Order(8)
    public void updatePatient() throws PatientNotFoundException {
        PatientDTO patientDTO = patientService.getPatientById(6);
        patientDTO.setAddress("6 St Sun");

        patientService.updatePatient(6,patientDTO);
        List<PatientDTO> patients = patientService.getPatients();

        assertThat(patients.get(5).getAddress()).isEqualTo("6 St Sun");
    }

    @Test
    @Order(9)
    public void updatePatientWithNullId() {
        try {
            patientService.updatePatient(10,patient);
        } catch (PatientNotFoundException e) {
            assertThat(e.getMessage()).contains("The patient with the id : " + 10 + " was not found");
        }
    }

    @Test
    @Order(10)
    public void deletePatient() throws PatientNotFoundException {
        patientService.deletePatientById(6);
        List<PatientDTO> patients = patientService.getPatients();

        assertThat(patients.size()).isEqualTo(5);
    }

    @Test
    @Order(11)
    public void deletePatientWithNullId() {
        try {
            patientService.deletePatientById(10);
        } catch (PatientNotFoundException e) {
            assertThat(e.getMessage()).contains("The patient with the id : " + 10 + " was not found");
        }
    }
}

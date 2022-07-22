package com.mediscreen.patient.service;

import com.mediscreen.patient.constants.Gender;
import com.mediscreen.patient.dto.PatientDTO;
import com.mediscreen.patient.exceptions.PatientAlreadyExistingException;
import com.mediscreen.patient.exceptions.PatientNotFoundException;
import com.mediscreen.patient.mapper.PatientMapper;
import com.mediscreen.patient.model.Patient;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PatientServiceTest {


    private final PatientService patientService;

    private final PatientMapper patientMapper;

    @Autowired
    public PatientServiceTest(PatientService patientService, PatientMapper patientMapper) {
        this.patientService = patientService;
        this.patientMapper = patientMapper;
    }

    private PatientDTO patient;

    @BeforeAll
    private void setUp() {
        patient = PatientDTO.builder()
                .id(21L)
                .lastName("Garrix")
                .firstName("Martin")
                .birthdate(LocalDate.of(1996,5,14))
                .sex(Gender.M)
                .address("5 St Holland")
                .phone("999-999-999")
                .build();
    }

    @Test
    @Order(1)
    public void addPatient() throws PatientAlreadyExistingException {
        patientService.addPatient(patient);
        List<PatientDTO> patients = patientService.getPatients();

        assertThat(patients.get(4).getLastName()).isEqualTo("Garrix");
    }

    @Test
    @Order(2)
    public void savePatientWithExistingPatient() {
        try {
            patientService.addPatient(patient);
        } catch (PatientAlreadyExistingException e) {
            assertThat(e.getMessage()).contains("The patient is already exist");
        }
    }

    @Test
    @Order(3)
    public void getPatients() {
        List<PatientDTO> patients = patientService.getPatients();
        assertThat(patients.size()).isEqualTo(5);
    }

    @Test
    @Order(4)
    public void getPatientById() throws PatientNotFoundException {
        PatientDTO patientDTO = patientService.getPatientById(5L);
        assertThat(patientDTO.getId()).isEqualTo(5);
    }

    @Test
    @Order(5)
    public void getPatientWithNullId() {
        try {
            patientService.getPatientById(10L);
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
            assertThat(e.getMessage()).contains("The patient with these information : " + "Carment" + " " + patient.getLastName() + " " + patient.getBirthdate() + " was not found");
        }
    }

    @Test
    @Order(8)
    public void getAllByLastName() {
        List<PatientDTO> patientFamilyList = patientService.getAllByLastName(patient.getLastName());
        assertThat(patientFamilyList.size()).isEqualTo(1);
    }

    @Test
    @Order(9)
    public void updatePatient() throws PatientNotFoundException {
        PatientDTO patientDTO = patientService.getPatientById(5L);
        Patient patient = patientMapper.fromDTO(patientDTO);
        patient.setAddress("6 St Sun");

        patientService.updatePatient(patient.getId(),patientMapper.toDTO(patient));
        List<PatientDTO> patients = patientService.getPatients();

        assertThat(patients.get(4).getAddress()).isEqualTo("6 St Sun");
    }

    @Test
    @Order(10)
    public void updatePatientWithNullId() {
        try {
            patientService.updatePatient(10L,patient);
        } catch (PatientNotFoundException e) {
            assertThat(e.getMessage()).contains("The patient with the id : " + 10 + " was not found");
        }
    }

    @Test
    @Order(11)
    public void deletePatient() throws PatientNotFoundException {
        patientService.deletePatientById(5L);
        List<PatientDTO> patients = patientService.getPatients();

        assertThat(patients.size()).isEqualTo(4);
    }

    @Test
    @Order(12)
    public void deletePatientWithNullId() {
        try {
            patientService.deletePatientById(10L);
        } catch (PatientNotFoundException e) {
            assertThat(e.getMessage()).contains("The patient with the id : " + 10 + " was not found");
        }
    }
}

package com.mediscreen.patient.service;

import com.mediscreen.patient.constants.Gender;
import com.mediscreen.patient.dto.PatientDTO;
import com.mediscreen.patient.exceptions.PatientAlreadyExistingException;
import com.mediscreen.patient.exceptions.PatientNotFoundException;
import com.mediscreen.patient.mapper.PatientMapper;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.repository.PatientRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PatientServiceTest {

    @MockBean
    private PatientRepository patientRepository;

    private final PatientService patientService;

    private final PatientMapper patientMapper;

    @Autowired
    public PatientServiceTest(PatientService patientService, PatientMapper patientMapper) {
        this.patientService = patientService;
        this.patientMapper = patientMapper;
    }

    private Patient patient;
    private PatientDTO patientDTO;
    private List<Patient> patientList = new ArrayList<>();

    @BeforeAll
    private void setUp() {

        patient = new Patient();
        patient.setId(22L);
        patient.setLastName("Poupe");
        patient.setFirstName("Paul");
        patient.setBirthdate(LocalDate.now());
        patientList.add(patient);

        patientDTO = PatientDTO.builder()
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
    public void getPatients() {
        when(patientRepository.findAll()).thenReturn(patientList);
        List<PatientDTO> patientDTOList = patientList.stream().map(patientMapper::toDTO).collect(Collectors.toList());
        assertThat(patientService.getPatients()).isEqualTo(patientDTOList);
    }

    @Test
    public void getPatientById() throws PatientNotFoundException {
        when(patientRepository.findById(patient.getId())).thenReturn(Optional.of(patient));
        assertThat(patientService.getPatientById(patient.getId())).isEqualTo(patientMapper.toDTO(patient));
    }

    @Test
    public void getPatientWithNullId() {
        when(patientRepository.findById(patient.getId())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> patientService.getPatientById(patient.getId())).isInstanceOf(PatientNotFoundException.class);
    }

    @Test
    public void getPatientByLastNameAndFirstNameAndBirthdate() throws PatientNotFoundException {
        when(patientRepository.findByLastNameAndFirstNameAndBirthdate(patient.getLastName(), patient.getFirstName(), patient.getBirthdate()))
                .thenReturn(Optional.of(patient));
        assertThat(patientService.getPatientByLastNameAndFirstNameAndBirthdate(patient.getLastName(), patient.getFirstName(), patient.getBirthdate()))
                .isEqualTo(patientMapper.toDTO(patient));
    }

    @Test
    public void getPatientByLastNameAndFirstNameAndBirthdateWithNullLastName() {
        when(patientRepository.findByLastNameAndFirstNameAndBirthdate(patient.getLastName(), patient.getFirstName(), patient.getBirthdate()))
                .thenReturn(Optional.empty());
        assertThatThrownBy(() -> patientService.getPatientByLastNameAndFirstNameAndBirthdate(patient.getLastName(), patient.getFirstName(), patient.getBirthdate()))
                .isInstanceOf(PatientNotFoundException.class);
    }

    @Test
    public void getAllByLastName() {
        when(patientRepository.findAllByLastName(patient.getLastName())).thenReturn(patientList);
        List<PatientDTO> patientDTOList = patientList.stream().map(patientMapper::toDTO).collect(Collectors.toList());
        assertThat(patientService.getAllByLastName(patient.getLastName())).isEqualTo(patientDTOList);
    }

    @Test
    public void addPatient() throws PatientAlreadyExistingException {
        patientService.addPatient(patientDTO);
        verify(patientRepository, Mockito.times(1)).save(patientMapper.fromDTO(patientDTO));
    }

    @Test
    public void updatePatient() throws PatientNotFoundException {
        when(patientRepository.findById(patientDTO.getId())).thenReturn(Optional.of(patientMapper.fromDTO(patientDTO)));
        patientService.updatePatient(patientDTO.getId(), patientDTO);
        verify(patientRepository, Mockito.times(1)).save(patientMapper.fromDTO(patientDTO));
    }

    @Test
    public void updatePatientWithNullId() {
        when(patientRepository.findById(patientDTO.getId())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> patientService.updatePatient(patientDTO.getId(), patientDTO)).isInstanceOf(PatientNotFoundException.class);
    }

    @Test
    public void deletePatient() throws PatientNotFoundException {
        when(patientRepository.findById(patient.getId())).thenReturn(Optional.of(patient));
        patientService.deletePatientById(patient.getId());
        verify(patientRepository, Mockito.times(1)).deleteById(patient.getId());
    }

    @Test
    public void deletePatientWithNullId() {
        doThrow(EmptyResultDataAccessException.class).when(patientRepository).deleteById(patientDTO.getId());
        assertThatThrownBy(() -> patientService.deletePatientById(patientDTO.getId())).isInstanceOf(PatientNotFoundException.class);
    }
}

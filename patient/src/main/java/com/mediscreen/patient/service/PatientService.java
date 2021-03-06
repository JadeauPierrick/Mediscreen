package com.mediscreen.patient.service;

import com.mediscreen.patient.dto.PatientDTO;
import com.mediscreen.patient.exceptions.PatientAlreadyExistingException;
import com.mediscreen.patient.exceptions.PatientNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface PatientService {

    List<PatientDTO> getPatients();

    PatientDTO getPatientById(Long id) throws PatientNotFoundException;

    PatientDTO getPatientByLastNameAndFirstNameAndBirthdate(String lastName, String firstName, LocalDate birthdate) throws PatientNotFoundException;

    List<PatientDTO> getAllByLastName(String lastName);

    PatientDTO addPatient(PatientDTO patientDTO) throws PatientAlreadyExistingException;

    PatientDTO updatePatient(Long id, PatientDTO patientDTO) throws PatientNotFoundException;

    void deletePatientById(Long id) throws PatientNotFoundException;
}

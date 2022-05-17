package com.mediscreen.patient.service;

import com.mediscreen.patient.dto.PatientDTO;
import com.mediscreen.patient.exceptions.PatientAlreadyExistingException;
import com.mediscreen.patient.exceptions.PatientNotFoundException;
import com.mediscreen.patient.mapper.PatientMapper;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
public class PatientServiceImpl implements PatientService{

    private final PatientRepository patientRepository;

    private final PatientMapper patientMapper;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    @Override
    public List<PatientDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(patientMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public PatientDTO getPatientById(Long id) throws PatientNotFoundException {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient == null) {
            throw new PatientNotFoundException("The patient with the id : " + id + " was not found");
        }
        log.info("The patient was successfully found with the id : {}", id);
        return patientMapper.toDTO(patient);
    }

    @Override
    public PatientDTO getPatientByLastNameAndFirstNameAndBirthdate(String lastName, String firstName, LocalDate birthdate) throws PatientNotFoundException {
        Patient patient = patientRepository.findByLastNameAndFirstNameAndBirthdate(lastName,firstName,birthdate).orElse(null);
        if (patient == null) {
            throw new PatientNotFoundException("The patient with these information : " + lastName + " " + firstName + " " + birthdate + " was not found");
        }
        log.info("The patient was successfully found with these information : {} {} {}", lastName, firstName, birthdate);
        return patientMapper.toDTO(patient);
    }

    @Override
    public List<PatientDTO> getAllByLastName(String lastName) {
        List<Patient> patients = patientRepository.findAllByLastName(lastName);
        return patients.stream().map(patientMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public PatientDTO addPatient(PatientDTO patientDTO) throws PatientAlreadyExistingException {
        Optional<Patient> currentPatient = patientRepository.findByLastNameAndFirstNameAndBirthdate(patientDTO.getLastName(), patientDTO.getFirstName(), patientDTO.getBirthdate());
        if (currentPatient.isPresent()) {
            throw new PatientAlreadyExistingException("The patient is already exist");
        }
        Patient newPatient = patientMapper.fromDTO(patientDTO);
        log.info("The patient was successfully created");
        return patientMapper.toDTO(patientRepository.save(newPatient));
    }

    @Override
    public PatientDTO updatePatient(Long id, PatientDTO patientDTO) throws PatientNotFoundException {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient == null) {
            throw new PatientNotFoundException("The patient with the id : " + id + " was not found");
        }
        Patient patientUpdated = patientMapper.fromDTO(patientDTO);
        log.info("The patient was successfully updated");
        return patientMapper.toDTO(patientRepository.save(patientUpdated));
    }

    @Override
    public void deletePatientById(Long id) throws PatientNotFoundException {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient == null) {
            throw new PatientNotFoundException("The patient with the id : " + id + " was not found");
        }
        log.info("The patient was successfully deleted");
        patientRepository.deleteById(id);
    }
}

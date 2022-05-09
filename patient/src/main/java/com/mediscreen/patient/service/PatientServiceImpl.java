package com.mediscreen.patient.service;

import com.mediscreen.patient.dto.PatientDTO;
import com.mediscreen.patient.exceptions.PatientAlreadyExistingException;
import com.mediscreen.patient.exceptions.PatientNotFoundException;
import com.mediscreen.patient.mapper.PatientMapper;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
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
    public PatientDTO getPatientById(Integer id) throws PatientNotFoundException {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isPresent()) {
            return patientMapper.toDTO(patient.get());
        }else {
            throw new PatientNotFoundException("The patient with the id : " + id + " was not found");
        }
    }

    @Override
    public PatientDTO getPatientByLastNameAndFirstNameAndBirthdate(String lastName, String firstName, LocalDate birthdate) throws PatientNotFoundException {
        Optional<Patient> patient = patientRepository.findByLastNameAndFirstNameAndBirthdate(lastName,firstName,birthdate);
        if (patient.isPresent()) {
            return patientMapper.toDTO(patient.get());
        }else {
            throw new PatientNotFoundException("The patient with these informations : " + lastName + " " + firstName + " " + birthdate + " was not found");
        }
    }

    @Override
    public PatientDTO addPatient(PatientDTO patientDTO) throws PatientAlreadyExistingException {
        Optional<Patient> currentPatient = patientRepository.findByLastNameAndFirstNameAndBirthdate(patientDTO.getLastName(), patientDTO.getFirstName(), patientDTO.getBirthdate());
        if (currentPatient.isPresent()) {
            throw new PatientAlreadyExistingException("The patient is already exist");
        } else {
            Patient newPatient = patientMapper.fromDTO(patientDTO);
            patientRepository.save(newPatient);
            return patientDTO;
        }
    }

    @Override
    public PatientDTO updatePatient(Integer id, PatientDTO patientDTO) throws PatientNotFoundException {
        Optional<Patient> patient = patientRepository.findById(id);
        if (!patient.isPresent()) {
            throw new PatientNotFoundException("The patient with the id : " + id + " was not found");
        } else {
            Patient patientUpdated = patientMapper.fromDTO(patientDTO);
            patientRepository.save(patientUpdated);
            return patientDTO;
        }
    }

    @Override
    public void deletePatientById(Integer id) throws PatientNotFoundException {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isPresent()) {
            patientRepository.deleteById(id);
        } else {
            throw new PatientNotFoundException("The patient with the id : " + id + " was not found");
        }
    }
}

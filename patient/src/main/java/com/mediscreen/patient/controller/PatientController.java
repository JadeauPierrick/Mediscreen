package com.mediscreen.patient.controller;

import com.mediscreen.patient.dto.PatientDTO;
import com.mediscreen.patient.exceptions.PatientAlreadyExistingException;
import com.mediscreen.patient.exceptions.PatientNotFoundException;
import com.mediscreen.patient.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/patient")
@Slf4j
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PatientDTO>> getPatients() {
        List<PatientDTO> patients = patientService.getPatients();
        if (patients.isEmpty()) {
            log.error("There is no patient");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(patients,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable("id") Long id) {
        try {
            PatientDTO patient = patientService.getPatientById(id);
            return new ResponseEntity<>(patient,HttpStatus.OK);
        } catch (PatientNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/family/{familyName}")
    public ResponseEntity<List<PatientDTO>> getAllByLastName(@PathVariable("familyName") String familyName) {
        List<PatientDTO> patients = patientService.getAllByLastName(familyName);
        if (patients.isEmpty()) {
            log.error("There is no patient for this family name : {}", familyName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(patients,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<PatientDTO> addPatient(@Valid @RequestBody PatientDTO patientDTO) {
        try {
            PatientDTO newPatient = patientService.addPatient(patientDTO);
            return new ResponseEntity<>(newPatient,HttpStatus.OK);
        } catch (PatientAlreadyExistingException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable("id") Long id, @Valid @RequestBody PatientDTO patientDTO) {
        try {
            PatientDTO patientUpdated = patientService.updatePatient(id, patientDTO);
            return new ResponseEntity<>(patientUpdated,HttpStatus.OK);
        } catch (PatientNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePatientById(@PathVariable("id") Long id) {
        try {
            patientService.deletePatientById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (PatientNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

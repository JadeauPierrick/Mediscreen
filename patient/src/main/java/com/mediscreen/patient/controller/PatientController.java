package com.mediscreen.patient.controller;

import com.mediscreen.patient.dto.PatientDTO;
import com.mediscreen.patient.exceptions.PatientAlreadyExistingException;
import com.mediscreen.patient.exceptions.PatientNotFoundException;
import com.mediscreen.patient.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PatientDTO>> getPatients() {
        List<PatientDTO> patients = patientService.getPatients();
        if (patients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(patients,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable("id") Integer id) {
        try {
            PatientDTO patient = patientService.getPatientById(id);
            return new ResponseEntity<>(patient,HttpStatus.OK);
        } catch (PatientNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<PatientDTO> addPatient(@RequestBody PatientDTO patientDTO) {
        try {
            PatientDTO newPatient = patientService.addPatient(patientDTO);
            return new ResponseEntity<>(newPatient,HttpStatus.OK);
        } catch (PatientAlreadyExistingException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable("id") Integer id, @RequestBody PatientDTO patientDTO) throws Exception {
        try {
            PatientDTO patientUpdated = patientService.updatePatient(id, patientDTO);
            return new ResponseEntity<>(patientUpdated,HttpStatus.OK);
        } catch (PatientNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePatientById(@PathVariable("id") Integer id) {
        try {
            patientService.deletePatientById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (PatientNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

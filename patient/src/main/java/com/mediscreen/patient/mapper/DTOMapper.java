package com.mediscreen.patient.mapper;

import com.mediscreen.patient.dto.PatientDTO;
import com.mediscreen.patient.model.Patient;
import org.springframework.stereotype.Component;

@Component
public class DTOMapper {

    public PatientDTO mapPatientToDTO(Patient patient) {
        return new PatientDTO(patient.getId(), patient.getLastName(), patient.getFirstName(), patient.getBirthdate(),
                patient.getSex(), patient.getAddress(), patient.getPhone());
    }

    public Patient mapDTOToPatient(PatientDTO patientDTO) {
        return new Patient(patientDTO.getId(), patientDTO.getLastName(), patientDTO.getFirstName(),
                patientDTO.getBirthdate(), patientDTO.getSex(), patientDTO.getAddress(), patientDTO.getPhone());
    }
}

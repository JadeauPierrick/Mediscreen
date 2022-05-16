package com.mediscreen.assessment.service;

import com.mediscreen.assessment.dto.Assessment;
import com.mediscreen.assessment.dto.NoteDTO;
import com.mediscreen.assessment.dto.PatientDTO;
import com.mediscreen.assessment.model.RiskLevel;
import com.mediscreen.assessment.model.TriggerTerms;

import java.util.List;

public interface AssessmentService {

    Assessment getAssessmentByPatientId(Long patientId);

    List<TriggerTerms> getPatientTriggerTerms(List<NoteDTO> notes);

    RiskLevel getRiskLevel(PatientDTO patient, int numberOfTriggers);
}

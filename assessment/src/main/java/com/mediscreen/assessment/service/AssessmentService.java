package com.mediscreen.assessment.service;

import com.mediscreen.assessment.dto.Assessment;

import java.util.List;


public interface AssessmentService {

    Assessment getAssessmentByPatientId(Long patientId);

    List<Assessment> getAssessmentByFamilyName(String familyName);

}

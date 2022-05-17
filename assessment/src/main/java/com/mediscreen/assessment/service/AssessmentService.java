package com.mediscreen.assessment.service;

import com.mediscreen.assessment.dto.Assessment;


public interface AssessmentService {

    Assessment getAssessmentByPatientId(Long patientId);

}

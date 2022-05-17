package com.mediscreen.assessment.controller;

import com.mediscreen.assessment.dto.Assessment;
import com.mediscreen.assessment.service.AssessmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assessment")
public class AssessmentController {

    private final AssessmentService assessmentService;

    public AssessmentController(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Assessment> getPatientAssessment(@PathVariable("id") Long id) {
        Assessment assessment = assessmentService.getAssessmentByPatientId(id);
        return new ResponseEntity<>(assessment, HttpStatus.OK);
    }
}
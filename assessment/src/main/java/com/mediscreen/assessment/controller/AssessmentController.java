package com.mediscreen.assessment.controller;

import com.mediscreen.assessment.dto.Assessment;
import com.mediscreen.assessment.service.AssessmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/assessment")
@Api(tags = "Assessment's data API")
public class AssessmentController {

    private final AssessmentService assessmentService;

    public AssessmentController(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    @GetMapping("/{id}")
    @ApiOperation("Get the risk level to have diabetes of the patient")
    public ResponseEntity<Assessment> getPatientAssessment(@ApiParam(value = "The id of the patient", example = "1")
                                                               @PathVariable("id") Long id) {
        Assessment assessment = assessmentService.getAssessmentByPatientId(id);
        return new ResponseEntity<>(assessment, HttpStatus.OK);
    }

    @GetMapping("/family/{familyName}")
    @ApiOperation("Get all risk levels of all members of a family by their last name")
    public ResponseEntity<List<Assessment>> getFamilyAssessments(@ApiParam(value = "The family name", example = "Carter")
                                                                     @PathVariable("familyName") String familyName) {
        List<Assessment> familyAssessments = assessmentService.getAssessmentByFamilyName(familyName);
        return new ResponseEntity<>(familyAssessments, HttpStatus.OK);
    }
}

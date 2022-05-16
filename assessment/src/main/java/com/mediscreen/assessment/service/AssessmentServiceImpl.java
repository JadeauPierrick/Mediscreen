package com.mediscreen.assessment.service;

import com.mediscreen.assessment.constants.Gender;
import com.mediscreen.assessment.dto.Assessment;
import com.mediscreen.assessment.dto.NoteDTO;
import com.mediscreen.assessment.dto.PatientDTO;
import com.mediscreen.assessment.model.RiskLevel;
import com.mediscreen.assessment.model.TriggerTerms;
import com.mediscreen.assessment.proxies.MedicalRecordProxy;
import com.mediscreen.assessment.proxies.PatientProxy;
import com.mediscreen.assessment.utils.AgeCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssessmentServiceImpl implements AssessmentService{

    private final MedicalRecordProxy medicalRecordProxy;

    private final PatientProxy patientProxy;

    private final AgeCalculator ageCalculator;

    @Autowired
    public AssessmentServiceImpl(MedicalRecordProxy medicalRecordProxy, PatientProxy patientProxy, AgeCalculator ageCalculator) {
        this.medicalRecordProxy = medicalRecordProxy;
        this.patientProxy = patientProxy;
        this.ageCalculator = ageCalculator;
    }

    @Override
    public Assessment getAssessmentByPatientId(Long patientId) {
        PatientDTO patient = patientProxy.getPatientById(patientId);
        int age = ageCalculator.calculateAge(patient.getBirthdate());
        List<NoteDTO> notes = medicalRecordProxy.getAllNotesByPatientId(patientId);
        List<TriggerTerms> terms = getPatientTriggerTerms(notes);
        RiskLevel riskLevel = getRiskLevel(patient, notes.size());

        Assessment assessment = Assessment.builder()
                .patientId(patientId)
                .patientLastName(patient.getLastName())
                .patientFirstName(patient.getFirstName())
                .age(age)
                .riskLevel(riskLevel)
                .terms(terms)
                .build();

        return assessment;
    }

    @Override
    public List<TriggerTerms> getPatientTriggerTerms(List<NoteDTO> notes) {
        List<TriggerTerms> result = new ArrayList<>();
        EnumSet<TriggerTerms> terms = EnumSet.allOf(TriggerTerms.class);

        terms.forEach(triggerTerms -> {
            notes.forEach(noteDTO -> {
                if (StringUtils.containsIgnoreCase(noteDTO.getContent(), triggerTerms.getTerm())) {
                    result.add(triggerTerms);
                }
            });
        });
        return result.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public RiskLevel getRiskLevel(PatientDTO patient, int numberOfTriggers) {
        RiskLevel riskLevel = RiskLevel.NONE;
        int age = ageCalculator.calculateAge(patient.getBirthdate());
        Gender sex = patient.getSex();

        if (age < 30) {
            if(sex.equals(Gender.M)) {
                if (numberOfTriggers >= 3 && numberOfTriggers < 5) {
                    riskLevel = RiskLevel.IN_DANGER;
                } else if (numberOfTriggers >= 5) {
                    riskLevel = RiskLevel.EARLY_ONSET;
                }
            } else {
                if (numberOfTriggers >= 4 && numberOfTriggers < 7) {
                    riskLevel = RiskLevel.IN_DANGER;
                } else if (numberOfTriggers >= 7) {
                    riskLevel = RiskLevel.EARLY_ONSET;
                }
            }
        } else {
            if (numberOfTriggers >= 2 && numberOfTriggers < 6) {
                riskLevel = RiskLevel.BORDERLINE;
            } else if (numberOfTriggers >= 6 && numberOfTriggers < 8) {
                riskLevel = RiskLevel.IN_DANGER;
            } else if (numberOfTriggers >= 8) {
                riskLevel = RiskLevel.EARLY_ONSET;
            }
        }
        return riskLevel;
    }
}

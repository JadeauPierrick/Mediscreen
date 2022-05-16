package com.mediscreen.assessment.dto;

import com.mediscreen.assessment.model.RiskLevel;
import com.mediscreen.assessment.model.TriggerTerms;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class Assessment {

    Long patientId;

    String patientLastName;

    String patientFirstName;

    int age;

    RiskLevel riskLevel;

    List<TriggerTerms> terms;
}

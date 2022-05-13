package com.mediscreen.assessment.model;

import lombok.Getter;

@Getter
public enum TriggerTerms {
    HEMOGLOBIN_A1C("hemoglobin A1C"),
    MICROALBUMIN("microalbumin"),
    HEIGHT("height"),
    WEIGHT("weight"),
    SMOKER("smoker"),
    ABNORMAL("abnormal"),
    CHOLESTEROL("cholesterol"),
    DIZZINESS("dizziness"),
    RELAPSE("relapse"),
    REACTION("reaction"),
    ANTIBODIES("antibodies");

    private final String term;

    TriggerTerms(String term) {
        this.term = term;
    }
}

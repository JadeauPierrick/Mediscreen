package com.mediscreen.patient.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FOUND, reason = "Patient not found")
public class PatientNotFoundException extends Exception{

    public PatientNotFoundException(String message) {
        super(message);
    }
}

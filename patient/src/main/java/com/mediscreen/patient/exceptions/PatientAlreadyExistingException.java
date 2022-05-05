package com.mediscreen.patient.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Patient already exists")
public class PatientAlreadyExistingException extends Exception{

    public PatientAlreadyExistingException(String message) {
        super(message);
    }
}

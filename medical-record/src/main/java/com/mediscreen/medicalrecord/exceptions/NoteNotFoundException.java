package com.mediscreen.medicalrecord.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FOUND, reason = "Note not found")
public class NoteNotFoundException extends Exception{

    public NoteNotFoundException(String message) {
        super(message);
    }
}

package com.mediscreen.medicalrecord.controller;

import com.mediscreen.medicalrecord.dto.NoteDTO;
import com.mediscreen.medicalrecord.exceptions.NoteNotFoundException;
import com.mediscreen.medicalrecord.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<NoteDTO>> getNotes() {
        List<NoteDTO> notes = noteService.getNotes();
        if (notes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(notes,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteDTO> getNoteById(@PathVariable("id") Long id) {
        try {
            NoteDTO noteDTO = noteService.getNoteById(id);
            return new ResponseEntity<>(noteDTO,HttpStatus.OK);
        } catch (NoteNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<NoteDTO>> getAllNotesByPatientId(@PathVariable("patientId") Long patientId) {
        List<NoteDTO> notes = noteService.getAllNotesByPatientId(patientId);
        return new ResponseEntity<>(notes,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<NoteDTO> addNote(@Valid @RequestBody NoteDTO noteDTO) {
        NoteDTO noteCreated = noteService.addNote(noteDTO);
        return new ResponseEntity<>(noteCreated,HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<NoteDTO> updateNote(@PathVariable("id") Long id, @Valid @RequestBody NoteDTO noteDTO) {
        try {
            NoteDTO noteUpdated = noteService.updateNote(id,noteDTO);
            return new ResponseEntity<>(noteUpdated,HttpStatus.OK);
        } catch (NoteNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable("id") Long id) {
        try {
            noteService.deleteNoteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoteNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

package com.mediscreen.medicalrecord.controller;

import com.mediscreen.medicalrecord.dto.NoteDTO;
import com.mediscreen.medicalrecord.exceptions.NoteNotFoundException;
import com.mediscreen.medicalrecord.service.NoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/notes")
@Api(tags = "Medical record's data API")
@Slf4j
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("")
    @ApiOperation("Get all notes")
    public ResponseEntity<List<NoteDTO>> getNotes() {
        List<NoteDTO> notes = noteService.getNotes();
        if (notes.isEmpty()) {
            log.error("There is no note");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(notes,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get note information")
    public ResponseEntity<NoteDTO> getNoteById(@ApiParam(value = "The id of the note", example = "1")
                                                   @PathVariable("id") Long id) {
        try {
            NoteDTO noteDTO = noteService.getNoteById(id);
            return new ResponseEntity<>(noteDTO,HttpStatus.OK);
        } catch (NoteNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/patient/{patientId}")
    @ApiOperation("Get all the note of the patient by the patient id")
    public ResponseEntity<List<NoteDTO>> getAllNotesByPatientId(@ApiParam(value = "The id of the patient", example = "1")
                                                                    @PathVariable("patientId") Long patientId) {
        List<NoteDTO> notes = noteService.getAllNotesByPatientId(patientId);
        if (notes.isEmpty()) {
            log.error("There is no note for this patient id : {}", patientId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(notes,HttpStatus.OK);
    }

    @PostMapping("/add")
    @ApiOperation("Create a new note")
    public ResponseEntity<NoteDTO> addNote(@Valid @RequestBody NoteDTO noteDTO) {
        NoteDTO noteCreated = noteService.addNote(noteDTO);
        return new ResponseEntity<>(noteCreated,HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @ApiOperation("Update the note information")
    public ResponseEntity<NoteDTO> updateNote(@ApiParam(value = "The id of the note", example = "1")
                                                  @PathVariable("id") Long id, @Valid @RequestBody NoteDTO noteDTO) {
        try {
            NoteDTO noteUpdated = noteService.updateNote(id,noteDTO);
            return new ResponseEntity<>(noteUpdated,HttpStatus.OK);
        } catch (NoteNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Delete a note")
    public ResponseEntity<Void> deleteNote(@ApiParam(value = "The id of the note", example = "1")
                                               @PathVariable("id") Long id) {
        try {
            noteService.deleteNoteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoteNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

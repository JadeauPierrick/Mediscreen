package com.mediscreen.medicalrecord.service;

import com.mediscreen.medicalrecord.dto.NoteDTO;
import com.mediscreen.medicalrecord.exceptions.NoteNotFoundException;

import java.util.List;

public interface NoteService {

    List<NoteDTO> getNotes();

    NoteDTO getNoteById(Long id) throws NoteNotFoundException;

    List<NoteDTO> getAllNotesByPatientId(Long patientId);

    NoteDTO addNote(NoteDTO noteDTO);

    NoteDTO updateNote(Long id, NoteDTO noteDTO) throws NoteNotFoundException;

    void deleteNoteById(Long id) throws NoteNotFoundException;
}

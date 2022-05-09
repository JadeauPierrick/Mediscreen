package com.mediscreen.medicalrecord.service;

import com.mediscreen.medicalrecord.dto.NoteDTO;
import com.mediscreen.medicalrecord.exceptions.NoteNotFoundException;
import com.mediscreen.medicalrecord.mapper.NoteMapper;
import com.mediscreen.medicalrecord.model.Note;
import com.mediscreen.medicalrecord.repository.NoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class NoteServiceImpl implements NoteService{

    private final NoteRepository noteRepository;

    private final NoteMapper noteMapper;

    private final SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository, NoteMapper noteMapper, SequenceGeneratorService sequenceGeneratorService) {
        this.noteRepository = noteRepository;
        this.noteMapper = noteMapper;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Override
    public List<NoteDTO> getNotes() {
        return noteRepository.findAll().stream().map(noteMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public NoteDTO getNoteById(Long id) throws NoteNotFoundException {
        Note note = noteRepository.findById(id).orElse(null);
        if (note == null) {
            throw new NoteNotFoundException("The note with the id : " + id + " was not found");
        }
        log.info("The note was successfully found with the id : {}", id);
        return noteMapper.toDto(note);
    }

    @Override
    public List<NoteDTO> getAllNotesByPatientId(Long patientId) {
        List<Note> notes = noteRepository.findAllByPatientId(patientId);
        if (notes.isEmpty()) {
            log.info("There are no notes found for this patient id : {}", patientId);
        } else {
            log.info("Get the notes for this patient id : {}", patientId);
        }
        return notes.stream().map(noteMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public NoteDTO addNote(NoteDTO noteDTO) {
        Note noteCreated = noteMapper.fromDTO(noteDTO);
        noteCreated.setId(sequenceGeneratorService.generateSequence(Note.SEQUENCE_NAME));
        log.info("The note was successfully created");
        return noteMapper.toDto(noteRepository.save(noteCreated));
    }

    @Override
    public NoteDTO updateNote(Long id, NoteDTO noteDTO) throws NoteNotFoundException {
        Note note = noteRepository.findById(id).orElse(null);
        if (note == null) {
            throw new NoteNotFoundException("The note with the id : " + id + " was not found");
        }
        Note noteUpdated = noteMapper.fromDTO(noteDTO);
        log.info("The note was successfully updated");
        return noteMapper.toDto(noteRepository.save(noteUpdated));
    }

    @Override
    public void deleteNoteById(Long id) throws NoteNotFoundException {
        Note note = noteRepository.findById(id).orElse(null);
        if (note == null) {
            throw new NoteNotFoundException("The note with the id : " + id + " was not found");
        }
        log.info("The note was successfully deleted");
        noteRepository.deleteById(id);
    }
}

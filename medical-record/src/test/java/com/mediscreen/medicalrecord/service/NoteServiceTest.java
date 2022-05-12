package com.mediscreen.medicalrecord.service;

import com.mediscreen.medicalrecord.dto.NoteDTO;
import com.mediscreen.medicalrecord.exceptions.NoteNotFoundException;
import com.mediscreen.medicalrecord.mapper.NoteMapper;
import com.mediscreen.medicalrecord.model.Note;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NoteServiceTest {

    private final NoteService noteService;

    private final NoteMapper noteMapper;

    private NoteDTO noteDTO;

    @Autowired
    public NoteServiceTest(NoteService noteService, NoteMapper noteMapper) {
        this.noteService = noteService;
        this.noteMapper = noteMapper;
    }

    @BeforeAll
    private void setUp() {
        noteDTO = NoteDTO.builder()
                .patientId(9)
                .createdAt(LocalDateTime.now(ZoneOffset.UTC).withNano(0))
                .updatedAt(LocalDateTime.now(ZoneOffset.UTC).withNano(0))
                .content("The patient is in perfect health")
                .build();
    }

    @Test
    @Order(1)
    public void addNote() {
        noteService.addNote(noteDTO);
        List<NoteDTO> notes = noteService.getNotes();

        assertThat(notes.get(9).getPatientId()).isEqualTo(9);
    }

    @Test
    @Order(2)
    public void getNotes() {
        List<NoteDTO> notes = noteService.getNotes();

        assertThat(notes.size()).isEqualTo(10);
    }

    @Test
    @Order(3)
    public void getNoteById() throws NoteNotFoundException {
        NoteDTO note = noteService.getNoteById(10L);

        assertThat(note.getPatientId()).isEqualTo(noteDTO.getPatientId());
    }

    @Test
    @Order(4)
    public void getNoteByNullId() {
        try {
            noteService.getNoteById(99L);
        } catch (NoteNotFoundException e) {
            assertThat(e.getMessage()).contains("The note with the id : " + 99L + " was not found");
        }
    }

    @Test
    @Order(5)
    public void getAllNotesByPatientId() {
        List<NoteDTO> notes = noteService.getAllNotesByPatientId(9L);

        assertThat(notes.size()).isEqualTo(1);
    }

    @Test
    @Order(6)
    public void updateNote() throws NoteNotFoundException {
        NoteDTO note = noteService.getNoteById(10L);
        Note n = noteMapper.fromDTO(note);
        n.setContent("All is ok");

        noteService.updateNote(10L,noteMapper.toDto(n));
        List<NoteDTO> allNotes = noteService.getNotes();

        assertThat(allNotes.get(9).getContent()).isEqualTo("All is ok");
    }

    @Test
    @Order(7)
    public void updateNoteWithNullId() {
        try {
            noteService.updateNote(99L,noteDTO);
        } catch (NoteNotFoundException e) {
            assertThat(e.getMessage()).contains("The note with the id : " + 99L + " was not found");
        }
    }

    @Test
    @Order(8)
    public void deleteNote() throws NoteNotFoundException {
        noteService.deleteNoteById(10L);
        List<NoteDTO> notes = noteService.getNotes();

        assertThat(notes.size()).isEqualTo(9);
    }

    @Test
    @Order(9)
    public void deleteNoteWithNullId() {
        try {
            noteService.deleteNoteById(99L);
        } catch (NoteNotFoundException e) {
            assertThat(e.getMessage()).contains("The note with the id : " + 99L + " was not found");
        }
    }
}

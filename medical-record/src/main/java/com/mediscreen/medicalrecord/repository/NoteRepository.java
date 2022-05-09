package com.mediscreen.medicalrecord.repository;

import com.mediscreen.medicalrecord.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends MongoRepository<Note, Long> {

    List<Note> findAllByPatientId(Long id);
}

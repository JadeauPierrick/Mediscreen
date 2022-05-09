package com.mediscreen.medicalrecord.repository;

import com.mediscreen.medicalrecord.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends MongoRepository<Note, Integer> {
}

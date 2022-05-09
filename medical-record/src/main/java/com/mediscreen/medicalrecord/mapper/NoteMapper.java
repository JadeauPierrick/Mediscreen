package com.mediscreen.medicalrecord.mapper;

import com.mediscreen.medicalrecord.dto.NoteDTO;
import com.mediscreen.medicalrecord.model.Note;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoteMapper {

    NoteDTO toDto(Note note);

    Note fromDTO(NoteDTO noteDTO);
}

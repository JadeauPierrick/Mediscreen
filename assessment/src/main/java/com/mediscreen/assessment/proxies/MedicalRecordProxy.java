package com.mediscreen.assessment.proxies;

import com.mediscreen.assessment.dto.NoteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "medical-record-api", url = "${medical-record-api.url}")
public interface MedicalRecordProxy {

    @GetMapping("/notes/patient/{patientId}")
    List<NoteDTO> getAllNotesByPatientId(@PathVariable("patientId") Long patientId);
}

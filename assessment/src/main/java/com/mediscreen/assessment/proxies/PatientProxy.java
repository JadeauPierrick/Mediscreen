package com.mediscreen.assessment.proxies;

import com.mediscreen.assessment.dto.PatientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "patient-api", url = "${patient-api.url}")
public interface PatientProxy {

    @GetMapping(value = "/all")
    List<PatientDTO> getPatients();

    @GetMapping("/{id}")
    PatientDTO getPatientById(@PathVariable("id") Long id);

    @GetMapping("/family/{familyName}")
    List<PatientDTO> getAllByLastName(@PathVariable("familyName") String familyName);

}

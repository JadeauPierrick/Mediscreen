package com.mediscreen.patient.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext web;

    @BeforeEach
    private void setUpPerTest() {
        mockMvc = MockMvcBuilders.webAppContextSetup(web).build();
    }


    @Test
    @Order(1)
    public void getPatients() throws Exception {
        mockMvc.perform(get("/patients"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    public void getPatientById() throws Exception {
        mockMvc.perform(get("/patients/1"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    public void getPatientByNullId() throws Exception {
        mockMvc.perform(get("/patients/8"))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(4)
    public void getAllByLastName() throws Exception {
        mockMvc.perform(get("/patients/family/TestNone"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    public void getAllByNullLastName() throws Exception {
        mockMvc.perform(get("/patients/family/Testion"))
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(6)
    public void addPatient() throws Exception {
        mockMvc.perform(post("/patients/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"lastName\":\"Poupe\", \"firstName\":\"Paul\", \"birthdate\":\"1996-07-08\", \"sex\":\"M\", \"address\":\"3 Walk St\", \"phone\":\"222-222-333\" }")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Order(7)
    public void addPatientAlreadyExisting() throws Exception {
        mockMvc.perform(post("/patients/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"lastName\":\"Poupe\", \"firstName\":\"Paul\", \"birthdate\":\"1996-07-08\", \"sex\":\"M\", \"address\":\"3 Walk St\", \"phone\":\"222-222-333\" }")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    @Order(8)
    public void updatePatient() throws Exception {
        mockMvc.perform(put("/patients/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"lastName\":\"TestNone\", \"firstName\":\"Test\", \"birthdate\":\"1994-09-08\", \"sex\":\"M\", \"address\":\"3 Run St\", \"phone\":\"222-111-333\" }")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Order(9)
    public void updatePatientWithNullId() throws Exception {
        mockMvc.perform(put("/patients/update/8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"lastName\":\"TestNone\", \"firstName\":\"Test\", \"birthdate\":\"1994-09-08\", \"sex\":\"M\", \"address\":\"3 Run St\", \"phone\":\"222-111-333\" }")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(10)
    public void deletePatientById() throws Exception {
        mockMvc.perform(delete("/patients/delete/5"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(11)
    public void deletePatientByNullId() throws Exception {
        mockMvc.perform(delete("/patients/delete/8"))
                .andExpect(status().isNotFound());
    }


}







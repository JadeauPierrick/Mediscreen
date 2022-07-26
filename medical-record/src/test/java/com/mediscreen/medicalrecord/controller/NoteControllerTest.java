package com.mediscreen.medicalrecord.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NoteControllerTest {

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
    public void getNotes() throws Exception {
        mockMvc.perform(get("/notes"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    public void getNoteById() throws Exception {
        mockMvc.perform(get("/notes/1"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    public void getNoteByNullId() throws Exception {
        mockMvc.perform(get("/notes/15"))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(4)
    public void getAllNotesByPatientId() throws Exception {
        mockMvc.perform(get("/notes/patient/1"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    public void addNote() throws Exception {
        mockMvc.perform(post("/notes/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"patientId\":\"1\", \"createdAt\":\"2022-07-25T21:01:07.866\", \"updatedAt\":\"2022-07-25T21:01:07.866\", \"content\":\"MMMMMMMMMMMMMMMMMMM\" }")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Order(8)
    public void updateNote() throws Exception {
        mockMvc.perform(put("/notes/update/10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"patientId\":\"1\", \"createdAt\":\"2022-07-25T21:01:07.866\", \"updatedAt\":\"2022-07-25T21:01:07.866\", \"content\":\"BBBBBBBBBBBBBB\" }")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Order(9)
    public void updateNoteWithNullId() throws Exception {
        mockMvc.perform(put("/notes/update/15")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(10)
    public void deleteNoteById() throws Exception {
        mockMvc.perform(delete("/notes/delete/10"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(11)
    public void deleteNoteByNullId() throws Exception {
        mockMvc.perform(delete("/notes/delete/15"))
                .andExpect(status().isNotFound());
    }
}

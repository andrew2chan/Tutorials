package com.test.secondlesson;

import com.test.secondlesson.controllers.DogController;
import com.test.secondlesson.service.DogService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DogController.class)
public class ApplicationTestUnit {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DogService dogService;

    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    public void getDogBreeds() throws Exception {
        mockMvc.perform(get("/retrieveBreeds"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));

        verify(dogService, times(1)).retrieveDogBreed();
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    public void getDogBreedById() throws Exception {
        mockMvc.perform(get("/retrieveById/1"))
                .andExpect(status().isOk());

        verify(dogService, times(1)).retrieveDogBreedById(1L);
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    public void getDogNames() throws Exception {
        mockMvc.perform(get("/retrieveNames"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));

        verify(dogService, times(1)).retrieveDogNames();
    }
}

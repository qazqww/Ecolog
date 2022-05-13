package com.thedebuggers.backend.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
public class PostControllerTest {

    private MockMvc mvc;

    @Test
    void getTest() throws Exception {
        mvc.perform(get("/api/v1/community/1/post/15"))
                .andExpect(status().isOk());
//                .andExpect(content().string(""));
    }

}

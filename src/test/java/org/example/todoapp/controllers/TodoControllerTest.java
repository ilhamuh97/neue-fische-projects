package org.example.todoapp.controllers;

import org.example.todoapp.dtos.TodoDto;
import org.example.todoapp.enums.Status;
import org.example.todoapp.records.TodoRecord;
import org.example.todoapp.repos.TodoRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoRepo todoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createTodo_thenGetById() throws Exception {

        TodoDto dto = TodoDto.builder()
                .description("Test todo")
                .status(Status.OPEN)
                .build();

        // CREATE
        String location = mockMvc.perform(post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("/api/todo/")))
                .andReturn()
                .getResponse()
                .getHeader("Location");

        // GET
        assert location != null;
        mockMvc.perform(get(location))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    TodoRecord response = objectMapper.readValue(
                            result.getResponse().getContentAsString(),
                            TodoRecord.class
                    );

                    assert response.description().equals("Test todo");
                });
    }

    @Test
    void updateTodo() throws Exception {

        TodoRecord saved = todoRepository.save(
                new TodoRecord(null, "Old", Status.OPEN)
        );

        TodoDto update = TodoDto.builder()
                .description("Updated")
                .status(Status.IN_PROGRESS)
                .build();

        mockMvc.perform(put("/api/todo/{id}", saved.id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    TodoRecord response = objectMapper.readValue(
                            result.getResponse().getContentAsString(),
                            TodoRecord.class
                    );

                    assert response.description().equals("Updated");
                    assert response.status().equals(Status.IN_PROGRESS);
                });
    }

    @Test
    void deleteTodo() throws Exception {

        TodoRecord saved = todoRepository.save(
                new TodoRecord(null, "To Delete", Status.OPEN)
        );

        mockMvc.perform(delete("/api/todo/{id}", saved.id()))
                .andExpect(status().isNoContent());
    }
}
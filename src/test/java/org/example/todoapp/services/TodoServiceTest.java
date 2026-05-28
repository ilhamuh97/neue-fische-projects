package org.example.todoapp.services;

import org.example.todoapp.dtos.TodoDto;
import org.example.todoapp.enums.Status;
import org.example.todoapp.records.TodoRecord;
import org.example.todoapp.repos.TodoRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {
    private final String ID_123 = "123";
    private final String DESCRIPTION_TEST = "Test description";
    private final Status STATUS_OPEN = Status.OPEN;
    private final Status STATUS_IN_PROGRESS = Status.IN_PROGRESS;

    @Mock
    TodoRepo todoRepo;

    @InjectMocks
    TodoService todoService;

    @Test
    void getTodo() {
        // GIVEN
        TodoRecord expectedTodoRecord = TodoRecord
                .builder()
                .id(ID_123)
                .description(DESCRIPTION_TEST)
                .status(STATUS_OPEN)
                .build();

        when(todoRepo.findAll()).thenReturn(List.of(expectedTodoRecord));

        // WHEN
        List<TodoRecord> response = todoService.getTodo();

        // THEN
        verify(todoRepo, times(1)).findAll();
        assertEquals(List.of(expectedTodoRecord), response);
    }

    @Test
    void getTodoById() {
        // GIVEN
        TodoRecord expectedTodoRecord = TodoRecord
                .builder()
                .id(ID_123)
                .description(DESCRIPTION_TEST)
                .status(STATUS_OPEN)
                .build();

        when(todoRepo.findById(any(String.class))).thenReturn(Optional.of(expectedTodoRecord));

        // WHEN
        TodoRecord response = todoService.getTodoById(ID_123);

        // THEN
        verify(todoRepo, times(1)).findById(any(String.class));
        assertEquals(expectedTodoRecord, response);
    }

    @Test
    void createTodo() {
        // GIVEM
        TodoRecord savedFromDBTodoRecord = TodoRecord
                .builder()
                .id(ID_123)
                .description(DESCRIPTION_TEST)
                .status(STATUS_OPEN)
                .build();

        TodoDto todoDto = TodoDto
                .builder()
                .description(DESCRIPTION_TEST)
                .status(STATUS_OPEN)
                .build();
        when(todoRepo.save(any(TodoRecord.class))).thenReturn(savedFromDBTodoRecord);

        // WHEN
        TodoRecord response = todoService.createTodo(todoDto);

        // THEN
        verify(todoRepo, times(1)).save(any(TodoRecord.class));
        assertEquals(savedFromDBTodoRecord, response);
    }

    @Test
    void updateTodoById() {
        // GIVEN
        TodoRecord oldTodoRecord = TodoRecord.builder()
                .id(ID_123)
                .description(DESCRIPTION_TEST)
                .status(STATUS_OPEN)
                .build();

        TodoDto todoDto = TodoDto.builder()
                .description(DESCRIPTION_TEST)
                .status(STATUS_IN_PROGRESS)
                .build();

        TodoRecord savedTodo = TodoRecord.builder()
                .id(ID_123)
                .description(DESCRIPTION_TEST)
                .status(STATUS_IN_PROGRESS)
                .build();

        when(todoRepo.findById(ID_123))
                .thenReturn(Optional.of(oldTodoRecord));

        when(todoRepo.save(any(TodoRecord.class)))
                .thenReturn(savedTodo);

        // WHEN
        TodoRecord response = todoService.updateTodoById(ID_123, todoDto);

        // THEN
        assertEquals(savedTodo, response);
        verify(todoRepo, times(1)).findById(any(String.class));
        verify(todoRepo, times(1)).save(any(TodoRecord.class));
    }

    @Test
    void deleteTodoById() {
        // WHEN
        todoService.deleteTodoById(ID_123);

        // THEN
        verify(todoRepo).deleteById(ID_123);
    }
}
package org.example.todoapp.services;

import lombok.AllArgsConstructor;
import org.example.todoapp.dtos.TodoDto;
import org.example.todoapp.records.TodoRecord;
import org.example.todoapp.repos.TodoRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {
    private final TodoRepo todoRepo;

    public List<TodoRecord> getTodo() {
        return todoRepo.findAll();
    }

    public TodoRecord getTodoById(String id) {
        return todoRepo.findById(id).orElseThrow();
    }

    public TodoRecord createTodo(@RequestBody TodoDto todoDto) {
        TodoRecord todoRecord = TodoRecord
                .builder()
                .description(todoDto.description())
                .status(todoDto.status())
                .build();
        return todoRepo.save(todoRecord);
    }

    public TodoRecord updateTodoById(String id, @RequestBody TodoDto todoDto) {
        TodoRecord todoRecord = todoRepo.findById(id).orElseThrow();

        TodoRecord newTodoRecord = todoRecord
                .withStatus(todoDto.status())
                .withDescription(todoDto.description());

        return todoRepo.save(newTodoRecord);
    }

    public void deleteTodoById(String id) {
        todoRepo.deleteById(id);
    }

}

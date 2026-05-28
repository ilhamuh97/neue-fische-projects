package org.example.todoapp.controllers;

import lombok.AllArgsConstructor;
import org.example.todoapp.dtos.TodoDto;
import org.example.todoapp.records.TodoRecord;
import org.example.todoapp.services.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/todo")
@AllArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<List<TodoRecord>> getTodo() {
        return ResponseEntity
                .ok()
                .body(todoService.getTodo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoRecord> getTodoById(@PathVariable String id) {
        return ResponseEntity
                .ok()
                .body(todoService.getTodoById(id));
    }

    @PostMapping
    public ResponseEntity<TodoRecord> createTodo(@RequestBody TodoDto todoDto) {
        TodoRecord createdTodo = todoService.createTodo(todoDto);
        URI location = URI.create("/api/todo/" + createdTodo.id());

        return ResponseEntity
                .created(location)
                .body(createdTodo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoRecord> updateTodoById(@PathVariable String id, @RequestBody TodoDto todoDto) {
        return ResponseEntity.ok().body(todoService.updateTodoById(id, todoDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable String id) {
        todoService.deleteTodoById(id);

        return ResponseEntity
                .noContent()
                .build();
    }

}

package com.example.jobHunter.client.controller;


import com.example.jobHunter.client.service.RestClientTodoService;
import com.example.jobHunter.client.service.ToDoService;
import com.example.jobHunter.dto.TodoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final RestClientTodoService restClientTodoService;
    private final ToDoService todoService;

    @GetMapping
    ResponseEntity<List<TodoDto>> findAll() {
        // return ResponseEntity.ok(restClientTodoService.findAll());
        return ResponseEntity.ok(todoService.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<TodoDto> findById(@PathVariable Long id) {
        // return ResponseEntity.ok(restClientTodoService.findById(id));
        return ResponseEntity.ok(todoService.findById(id));
    }

    @PostMapping
    ResponseEntity<TodoDto> create(@RequestBody TodoDto toDoDto) {
        // return ResponseEntity.status(HttpStatus.CREATED).body(restClientTodoService.create(toDoDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.create(toDoDto));
    }

    @PutMapping("/{id}")
    ResponseEntity<TodoDto> update(@PathVariable Long id, @RequestBody TodoDto toDoDto) {
        // return ResponseEntity.status(HttpStatus.OK).body(restClientTodoService.update(id, toDoDto));
        return ResponseEntity.status(HttpStatus.OK).body(todoService.update(id, toDoDto));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable Long id) {
        // restClientTodoService.delete(id);
        todoService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("ToDo deleted successfully");
    }
}

package com.todo.ctrl;

import com.todo.dto.TodoDto;
import com.todo.dto.UserDto;
import com.todo.repo.TodoRepo;
import com.todo.repo.UserRepo;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users/{userId}/todos")
public class TodoCtrl {

    private final UserRepo userRepo;
    private final TodoRepo todoRepo;

    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllUserTodos(@PathVariable int userId) {
        UserDto user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<TodoDto> todos = user.getTodos();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TodoDto> addUserTodo(@PathVariable int userId, @RequestBody TodoDto todoDto) {
        UserDto user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        todoDto.setUser(user);
        TodoDto savedTodo = todoRepo.save(todoDto);
        user.getTodos().add(savedTodo);
        userRepo.save(user);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<TodoDto> getUserTodoById(@PathVariable int userId, @PathVariable int todoId) {
        UserDto user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        TodoDto todo = todoRepo.findById(todoId).orElse(null);
        if (todo == null || !todo.getUser().equals(user)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<TodoDto> updateUserTodo(
            @PathVariable int userId,
            @PathVariable int todoId,
            @RequestBody TodoDto updatedTodoDto
    ) {
        UserDto user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        TodoDto todo = todoRepo.findById(todoId).orElse(null);
        if (todo == null || !todo.getUser().equals(user)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        todo.setTodo(updatedTodoDto.getTodo());
        TodoDto updatedTodo = todoRepo.save(todo);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void> deleteUserTodo(@PathVariable int userId, @PathVariable int todoId) {
        UserDto user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        TodoDto todo = todoRepo.findById(todoId).orElse(null);
        if (todo == null || !todo.getUser().equals(user)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.getTodos().remove(todo);
        userRepo.save(user);
        todoRepo.deleteById(todoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
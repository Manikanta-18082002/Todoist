package net.javaguides.todo.controller;

import lombok.AllArgsConstructor;
import net.javaguides.todo.dto.TodoDto;
import net.javaguides.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController //Makes this class as a Spring MVC Controller
@RequestMapping("api/todos")  //Base URL for all REST APIs
@AllArgsConstructor
public class TodoController {

    private TodoService todoService;

    //Build Addtodo REST API
    @PreAuthorize("hasRole('ADMIN')")  //Only Admin can Access this Method, checks whether Logged-in User is ADMN?
    @PostMapping //To Map Incoming HTTP Post request to this Method
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){
        TodoDto savedTodo = todoService.addTodo(todoDto);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    //Build gettodo REST API
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long todoId){
        TodoDto todoDto = todoService.getTodo(todoId);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }
    //Build getAll Todods REST API
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos(){
        List<TodoDto> todos = todoService.getAllTodos();
//        return new ResponseEntity<>(todos, HttpStatus.OK);
        return ResponseEntity.ok(todos);
    }

    //Build Update Todo REST API
    @PreAuthorize("hasRole('ADMIN')")  //Only Admin can Access this Method
    @PutMapping("{id}") //To Update Existing Resource /Data (Whole Keys)
    public ResponseEntity<TodoDto> updateTodo(@RequestBody  TodoDto todoDto, @PathVariable("id") Long todoId){
        TodoDto updatedTodo = todoService.updateTodo(todoDto, todoId);
        return ResponseEntity.ok(updatedTodo);
    }

    //Build Delete Todo REST API
    @PreAuthorize("hasRole('ADMIN')")  //Only Admin can Access this Method
    @DeleteMapping("{id}") //To Map Incoming Http Request to this Method
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long todoId){
        todoService.deleteTodo(todoId);
        return  ResponseEntity.ok("Todo Deleted Successfully");
    }

    //Build Complete Todo REST API
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("{id}/complete") //To Update Existing Resource Partially (Some Keys only, Completed)
    public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long todoId){
        TodoDto updatedTodo = todoService.completeTodo(todoId);
        return ResponseEntity.ok(updatedTodo);
    }

    //Build In-Complete Todo REST API
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("{id}/in-complete")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("id") Long todoId){
        TodoDto updatedTodo = todoService.inCompleteTodo(todoId);
        return ResponseEntity.ok(updatedTodo);
    }
}

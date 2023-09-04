package net.javaguides.todo.service;

import net.javaguides.todo.dto.TodoDto;

import java.util.List;

public interface TodoService {

    TodoDto addTodo(TodoDto todoDto); //Next: Implement this method in Impl Class

    TodoDto getTodo(Long id);

    List<TodoDto> getAllTodos();

    TodoDto updateTodo(TodoDto todoDto, Long id);

    void deleteTodo(Long id);

    TodoDto completeTodo(Long id);

    TodoDto inCompleteTodo(Long id);
}

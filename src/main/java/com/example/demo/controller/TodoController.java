package com.example.demo.controller;




import com.example.demo.model.TodoModel;
import com.example.demo.service.TodoModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
public class TodoController {
    @Autowired
    private TodoModelService todoModelService;

    @GetMapping
    public List<TodoModel> getAllTodos(){
        return todoModelService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<TodoModel> getTodoById(@PathVariable Long id){
        return todoModelService.findById(id)
                .map(todoModel -> ResponseEntity.ok().body(todoModel))
                .orElse(ResponseEntity.noContent().build());
    }
    @PostMapping
    public TodoModel createTodo(@RequestBody TodoModel todoModel){
        return todoModelService.saveItem(todoModel);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TodoModel> updateTodo(@PathVariable Long id, @RequestBody TodoModel todoModel){
        return todoModelService.findById(id)
                .map(existingTodo -> {
                    existingTodo.setText(todoModel.getText());
                    existingTodo.setCompleted(todoModel.isCompleted());
                    TodoModel updatedTodo = todoModelService.saveItem(existingTodo);
                    return ResponseEntity.ok().body(updatedTodo);
                }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable Long id){
        return todoModelService.findById(id)
                .map(todoModel -> {
                    todoModelService.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}


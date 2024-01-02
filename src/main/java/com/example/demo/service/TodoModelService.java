package com.example.demo.service;


import com.example.demo.model.TodoModel;
import com.example.demo.repository.TodoModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoModelService {
    @Autowired
    private TodoModelRepository todoModelRepository;

    public List<TodoModel> findAll(){
        return todoModelRepository.findAll();
    }
    public Optional<TodoModel> findById(Long id){
        return todoModelRepository.findById(id);
    }
    public List<TodoModel>findByCompleted(boolean completed){
        return todoModelRepository.findByCompleted(completed);
    }
    public TodoModel saveItem(TodoModel todoModel){
        return todoModelRepository.save(todoModel);
    }
    public void deleteById(Long id){
        todoModelRepository.deleteById(id);
    }
}


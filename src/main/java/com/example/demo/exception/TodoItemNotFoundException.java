package com.example.demo.exception;

public class TodoItemNotFoundException extends RuntimeException {
    public TodoItemNotFoundException(Long id){
        super("Could not find the item with the following id: " + id);
    }
}

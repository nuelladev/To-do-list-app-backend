package com.example.demo;

import com.example.demo.model.TodoModel;
import com.example.demo.repository.TodoModelRepository;
import com.example.demo.service.TodoModelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TodoModelServiceTest {
    @Autowired
    private TodoModelService todoModelService;

    @MockBean
    private TodoModelRepository todoModelRepository;

    private TodoModel expectedTodo;

    @BeforeEach
    public void setUp(){
        expectedTodo = new TodoModel();
        expectedTodo.setId(1L);
        expectedTodo.setText("Test My Todo App");
        expectedTodo.setCompleted(false);
    }
    @Test
    public void testFindAll(){
    when(todoModelRepository.findAll()).thenReturn(Arrays.asList(expectedTodo));
        List<TodoModel> result = todoModelService.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(expectedTodo, result.get(0));
        verify(todoModelRepository).findAll();
    }
    @Test
    public void testFindById() {
        when(todoModelRepository.findById(anyLong())).thenReturn(Optional.of(expectedTodo));
        Optional<TodoModel> result = todoModelService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(expectedTodo, result.get());
        verify(todoModelRepository).findById(anyLong());
    }
    @Test
    public void testSaveItem(){
        when(todoModelRepository.save(any(TodoModel.class))).thenReturn(expectedTodo);
        TodoModel result = todoModelService.saveItem(expectedTodo);
        assertNotNull(result);
        assertEquals(expectedTodo, result);
        verify(todoModelRepository).save(any(TodoModel.class));
    }

    @Test
    public void testDeleteItem(){
        doNothing().when(todoModelRepository).deleteById(anyLong());
        todoModelService.deleteById(1L);
        verify(todoModelRepository).deleteById(anyLong());
    }
}

package com.sam.personalBlog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleResourceNotFound(ResourceNotFoundException ex)
    {
        Map<String,Object> body = new HashMap<>();
        body.put("Timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error","Not found");
        body.put("message",ex.getMessage());
        return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
     }

     @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex)
     {
         Map<String,Object> body = new HashMap<>();
         body.put("Timestamp", LocalDateTime.now());
         body.put("status", HttpStatus.NOT_FOUND.value());
         body.put("error","Invalid Arguments");
         List<String> messages = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getField()+": "+x.getDefaultMessage()).toList();
         body.put("messages", messages);
         return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
     }
}

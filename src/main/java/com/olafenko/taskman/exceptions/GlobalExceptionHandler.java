package com.olafenko.taskman.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;

@RestControllerAdvice
public class GlobalExceptionHandler {


    //handler obsługujący exception przychodzący z walidacji pól w requestach
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ArrayList<String>> RequestExceptionHandler(MethodArgumentNotValidException ex){

        ArrayList<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(e -> errors.add(e.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }


}

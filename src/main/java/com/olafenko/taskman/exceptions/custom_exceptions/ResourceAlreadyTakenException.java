package com.olafenko.taskman.exceptions.custom_exceptions;

public class ResourceAlreadyTakenException extends RuntimeException{


    public ResourceAlreadyTakenException(String message) {
        super(message);
    }
}

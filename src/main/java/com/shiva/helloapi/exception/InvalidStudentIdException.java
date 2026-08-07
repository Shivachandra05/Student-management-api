package com.shiva.helloapi.exception;

public class InvalidStudentIdException extends RuntimeException {

    public InvalidStudentIdException(int id) {
        super("Student id " + id + " is invalid");
    }
}
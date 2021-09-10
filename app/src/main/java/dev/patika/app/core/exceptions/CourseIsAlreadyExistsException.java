package dev.patika.app.core.exceptions;

public class CourseIsAlreadyExistsException extends RuntimeException{
    public CourseIsAlreadyExistsException(String message) {
        super(message);
    }
}

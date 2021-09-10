package dev.patika.app.core.exceptions;

public class InstructorIsAlreadyExistsException extends RuntimeException {
    public InstructorIsAlreadyExistsException(String message) {
        super(message);
    }
}

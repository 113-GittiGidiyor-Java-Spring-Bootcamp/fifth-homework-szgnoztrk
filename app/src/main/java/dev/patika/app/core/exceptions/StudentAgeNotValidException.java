package dev.patika.app.core.exceptions;

public class StudentAgeNotValidException extends RuntimeException {
    public StudentAgeNotValidException(String message) {
        super(message);
    }
}

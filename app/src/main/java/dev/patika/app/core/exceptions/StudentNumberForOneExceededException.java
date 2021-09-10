package dev.patika.app.core.exceptions;

public class StudentNumberForOneExceededException extends RuntimeException{
    public StudentNumberForOneExceededException(String message) {
        super(message);
    }
}

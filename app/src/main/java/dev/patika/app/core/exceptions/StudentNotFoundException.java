package dev.patika.app.core.exceptions;

import javassist.NotFoundException;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String msg) {
        super(msg);
    }
}

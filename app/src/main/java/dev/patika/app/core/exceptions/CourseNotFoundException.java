package dev.patika.app.core.exceptions;

import javassist.NotFoundException;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(String msg) {
        super(msg);
    }
}

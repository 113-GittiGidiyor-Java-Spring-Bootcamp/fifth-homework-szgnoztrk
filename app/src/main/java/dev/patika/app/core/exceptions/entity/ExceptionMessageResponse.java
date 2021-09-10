package dev.patika.app.core.exceptions.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionMessageResponse {
    private int statusCode;
    private String message;
    private Long timestamp;
}

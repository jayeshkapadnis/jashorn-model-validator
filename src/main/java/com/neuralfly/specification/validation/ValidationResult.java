package com.neuralfly.specification.validation;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ValidationResult {
    private String type;
    private String message;
}

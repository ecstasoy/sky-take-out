package com.sky.annotation;

import com.sky.enumeration.OperationType;
import java.lang.annotation.*;

/**
 * Autofill annotation, marking the field that needs to be automatically filled
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoFill {
    // Operation type: UPDATE, INSERT
    OperationType value();
}

package com.tecylab.ms.students.app.infrastructure.adapters.input.rest;

import com.tecylab.ms.students.app.domain.exceptions.StudentNotFoundException;
import com.tecylab.ms.students.app.infrastructure.adapters.input.rest.models.response.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.Collections;

import static com.tecylab.ms.students.app.infrastructure.adapters.input.rest.models.enums.ErrorType.FUNCTIONAL;
import static com.tecylab.ms.students.app.infrastructure.adapters.input.rest.models.enums.ErrorType.SYSTEM;
import static com.tecylab.ms.students.app.infrastructure.utils.ErrorCatalog.*;

@RestControllerAdvice
public class GlobalControllerAdvice {

    private final String ERROR_LOG_MESSAGE = "Error -> code: {}, message: {}";

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(StudentNotFoundException.class)
    public ErrorResponse handleStudentNotFoundException() {
        return ErrorResponse.builder()
                .code(STUDENT_NOT_FOUND.getCode())
                .type(FUNCTIONAL)
                .message(STUDENT_NOT_FOUND.getMessage())
                .timestamp(LocalDate.now().toString())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        return ErrorResponse.builder()
                .code(STUDENT_BAD_PARAMETERS.getCode())
                .type(FUNCTIONAL)
                .message(STUDENT_BAD_PARAMETERS.getMessage())
                .details(bindingResult.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList())
                .timestamp(LocalDate.now().toString())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception ex) {
        return ErrorResponse.builder()
                .code(INTERNAL_SERVER_ERROR.getCode())
                .type(SYSTEM)
                .message(INTERNAL_SERVER_ERROR.getMessage())
                .details(Collections.singletonList(ex.getMessage()))
                .timestamp(LocalDate.now().toString())
                .build();
    }

}

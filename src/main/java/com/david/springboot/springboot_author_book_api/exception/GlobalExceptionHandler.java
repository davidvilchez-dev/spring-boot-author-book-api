package com.david.springboot.springboot_author_book_api.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 404 - Recurso no encontrado
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException exception) {

        ErrorResponse error = new ErrorResponse(
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                "El recurso solicitado no existe en la base de datos");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // 400 - ID inválido (letras, símbolos, etc.)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException exception) {

        String detail = "El parámetro '" + exception.getName() + "' debe ser un número válido";

        ErrorResponse error = new ErrorResponse(
                "Parámetro inválido",
                HttpStatus.BAD_REQUEST.value(),
                detail);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // 400 - Validaciones (@Min, @NotNull, etc.)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleValidation(ConstraintViolationException exception) {

        ErrorResponse error = new ErrorResponse(
                "Validación fallida",
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // 500 - Error inesperado (opcional pero recomendado)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception exception) {

        ErrorResponse error = new ErrorResponse(
                "Error interno del servidor",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

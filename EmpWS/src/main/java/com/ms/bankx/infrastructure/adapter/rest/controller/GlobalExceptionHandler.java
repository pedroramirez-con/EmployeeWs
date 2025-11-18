/**
 * 
 */
package com.ms.bankx.infrastructure.adapter.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import com.ms.bankx.domain.exception.EmployeeNotFoundException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Global exception handler for the entire application.
 * Provides centralized handling of exceptions thrown by controller methods.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Builds a structured error response with timestamp, status, message, and details.
     *
     * @param status  the HTTP status to return
     * @param message the main error message
     * @param details additional error details (e.g., request URI or validation errors)
     * @return a map representing the error response body
     */
    private Map<String, Object> buildErrorResponse(HttpStatus status, String message, Object details) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        body.put("details", details);
        return body;
    }

    /**
     * Handles EmployeeNotFoundException when an employee resource is not found.
     *
     * @param ex      the exception thrown
     * @param request the current web request
     * @return a 404 NOT FOUND response with error details
     */
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleEmployeeNotFound(EmployeeNotFoundException ex, WebRequest request) {
        logger.warn("Employee not found: {}", ex.getMessage());
        return new ResponseEntity<>(
                buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request.getDescription(false)),
                HttpStatus.NOT_FOUND
        );
    }

    /**
     * Handles validation errors triggered by @Valid annotations in request bodies.
     *
     * @param ex the MethodArgumentNotValidException containing validation errors
     * @return a 400 BAD REQUEST response with field-specific error messages
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, List<String>> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(fieldError -> fieldError.getDefaultMessage(), Collectors.toList())
                ));

        logger.info("Validation failed: {}", errors);
        return new ResponseEntity<>(
                buildErrorResponse(HttpStatus.BAD_REQUEST, "Validation Failed", errors),
                HttpStatus.BAD_REQUEST
        );
    }

    /**
     * Handles IllegalArgumentException typically thrown due to invalid method arguments.
     *
     * @param ex      the exception thrown
     * @param request the current web request
     * @return a 400 BAD REQUEST response with error details
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgument(IllegalArgumentException ex, WebRequest request) {
        logger.error("Illegal argument: {}", ex.getMessage());
        return new ResponseEntity<>(
                buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getDescription(false)),
                HttpStatus.BAD_REQUEST
        );
    }

    /**
     * Handles all uncaught exceptions that are not explicitly handled by other methods.
     *
     * @param ex      the exception thrown
     * @param request the current web request
     * @return a 500 INTERNAL SERVER ERROR response with a generic error message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex, WebRequest request) {
        logger.error("Unexpected error", ex);
        return new ResponseEntity<>(
                buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", request.getDescription(false)),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
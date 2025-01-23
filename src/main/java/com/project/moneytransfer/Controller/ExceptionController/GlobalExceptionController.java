package com.project.moneytransfer.Controller.ExceptionController;

import com.project.moneytransfer.Exceptions.AlreadyExistsException;
import com.project.moneytransfer.Exceptions.InvalidRequestException;
import com.project.moneytransfer.Exceptions.ResourceNotFoundException;
import com.project.moneytransfer.Exceptions.SomethingHappenedToImageException;
import com.project.moneytransfer.Response.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(NOT_FOUND)
                .body(new ApiResponse(ex.getMessage(), null));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException  ex) {
        return ResponseEntity.status(NOT_ACCEPTABLE)
                .body(new ApiResponse(ex.getMessage(), null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception ex) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(new ApiResponse(ex.getMessage(), null));
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ApiResponse> handleAlreadyExistsException(AlreadyExistsException ex) {
        return ResponseEntity.status(FOUND)
                .body(new ApiResponse(ex.getMessage(), null));
    }


    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ApiResponse> handleInvalidRequestException(InvalidRequestException ex) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ApiResponse(ex.getMessage(), null));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleInvalidRequestException(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ApiResponse(ex.getMessage(), null));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ApiResponse(ex.getMessage(), null));
    }

    @ExceptionHandler(SomethingHappenedToImageException.class)
    public ResponseEntity<ApiResponse> handleSomethingHappenedToImageException(SomethingHappenedToImageException ex) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ApiResponse(ex.getMessage(), null));
    }
}

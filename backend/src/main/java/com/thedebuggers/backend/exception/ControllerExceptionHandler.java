package com.thedebuggers.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponseBody> nullPointerEx(NullPointerException ex) {
        if (!ex.getMessage().isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(ErrorResponseBody.of(String.format("%s을(를) 찾을 수 없습니다.", ex.getMessage()), ex.getClass()));
        }

        return ResponseEntity.status(HttpStatus.OK).body(ErrorResponseBody.of("대상을 찾을 수 없습니다.", ex.getClass()));
    }

    // 403 Forbidden
    @ExceptionHandler
    public ResponseEntity authorizationEx(AuthorizationServiceException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("");
    }

    // 404 Not Found
    @ExceptionHandler
    public ResponseEntity notFoundEx(NoHandlerFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
    }

    // 500 Server Error
    @ExceptionHandler
    public ResponseEntity serverErrorEx(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }
}

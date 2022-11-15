package edu.whu.demo.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.xml.bind.ValidationException;
import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ProductAdminException.class, DataAccessException.class, ValidationException.class})
    public ResponseEntity<Object> handleExceptions(Exception exception, WebRequest webRequest) {
        ResponseEntity<Object> entity = new ResponseEntity<>(getExceptionResponse(exception), HttpStatus.BAD_REQUEST);
        return entity;
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDeniedException(Exception AccessDeniedException, WebRequest webRequest) {
        ResponseEntity<Object> entity = new ResponseEntity<>(getExceptionResponse(AccessDeniedException), HttpStatus.FORBIDDEN);
        return entity;
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<Object> handleAuthenticationException(Exception AuthenticationException, WebRequest webRequest) {
        ResponseEntity<Object> entity = new ResponseEntity<>(getExceptionResponse(AuthenticationException), HttpStatus.UNAUTHORIZED);
        return entity;
    }



    private static ExceptionResponse getExceptionResponse(Exception exception) {
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage(exception.getMessage());
        return response;
    }


}

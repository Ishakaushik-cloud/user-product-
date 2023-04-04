package com.example.postgresql.controller.errors;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> handleSqlIntegrityException(HttpServletRequest req, SQLIntegrityConstraintViolationException ex){
        String error="Unable to submit product: "+ex.getMessage();
        return buildResponseEntity(new ErrorResponse(HttpStatus.BAD_REQUEST,error));
}
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleNoSuchElementException(HttpServletRequest req, Exception ex){
        ErrorResponse response=new ErrorResponse(HttpStatus.NOT_FOUND,"The row does not exist"+req.getRequestURI());
        return buildResponseEntity(response);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValidException(HttpServletRequest req, Exception ex){
        ErrorResponse response=new ErrorResponse(HttpStatus.NOT_FOUND,ex.getMessage());
        return buildResponseEntity(response);
    }
    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse){
        return new ResponseEntity<>(errorResponse.getMessage(),errorResponse.getStatus());
    }
}

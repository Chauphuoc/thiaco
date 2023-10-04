package com.example.thiaco.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // handleDataInputException, chúng ta lấy thông điệp từ ngoại lệ và trả về một ResponseEntity
    // chứa thông điệp lỗi và mã trạng thái HTTP BAD_REQUEST.
    @ExceptionHandler(DataInputException.class)
    public ResponseEntity<?> dataInputException(DataInputException ex, WebRequest request) {
        Map<String, String> body = new HashMap<>();

        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> unAuthorizedException(UnauthorizedException ex, WebRequest request) {
        Map<String, String> body = new HashMap<>();

        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }



    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        Map<String, String> body = new HashMap<>();

        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(EmailExistsException.class)
//    public ResponseEntity<?> emailExistsException(EmailExistsException ex, WebRequest request) {
//        Map<String, String> body = new HashMap<>();
//
//        body.put("message", ex.getMessage());
//
//        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
//    }



}
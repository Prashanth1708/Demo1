package com.example.demo2.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class NoSuchElementExceptionhandler {

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<?> handlecustomexception(NoSuchElementException exception) {
        return new ResponseEntity<>("Student not Found !", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleexception(HttpRequestMethodNotSupportedException exception) {
        return new ResponseEntity<>("The HTTP Request method is not supported!! ", HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleexception(MethodArgumentTypeMismatchException exception) {
        return new ResponseEntity<>("The Product name is mismatching with the names in DB! ",
                HttpStatus.METHOD_NOT_ALLOWED);
    }

    // Handling Global Exception

    @ExceptionHandler(value = Exception.class)

    public ResponseEntity<?> handleglobalexception(Exception exception) {
        return new ResponseEntity<>(" oops something went wrong!!!", HttpStatus.NOT_FOUND);
    }

}
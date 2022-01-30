package com.example.demo.exceptionhandler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class Exception{

    @ExceptionHandler(value = CourseNotFound.class)
    public ResponseEntity<ErrorDetails> CourseNotAvailable(CourseNotFound exception,WebRequest request)
    {
        ErrorDetails details=new ErrorDetails(exception.getMsg(),new Date(),request.getDescription(false));;

        return new ResponseEntity<>(details,HttpStatus.NOT_FOUND);

    }
    
}

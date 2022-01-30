package com.example.demo.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CourseNotFound extends RuntimeException{

    String message;
    public CourseNotFound(String message) {
        super(message);
        this.message=message;
    }

    public CourseNotFound()
    {
        super();
    }

    public String getMsg()
    {
        return message;
    }

  
}

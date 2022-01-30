package com.example.demo.exceptionhandler;

import java.util.Date;

public class ErrorDetails {


    public String message;
    public Date date;
    public String details;

    public ErrorDetails(String message, Date date,String details)
    {
        this.message=message;
        this.date=date;
        this.details=details;

    }

}

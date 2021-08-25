package com.lemire.utils.exceptions;

public class NotFoundException  extends RuntimeException{
    public NotFoundException(){}

    public NotFoundException(String message){
        super(message);
    }

    public NotFoundException(Throwable cause){
        super(cause);
    }

    public NotFoundException(Throwable cause, String message){
        super(message,cause);
    }
}

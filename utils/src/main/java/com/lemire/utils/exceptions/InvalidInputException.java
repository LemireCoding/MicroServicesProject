package com.lemire.utils.exceptions;

public class InvalidInputException extends RuntimeException{

    public InvalidInputException(){}

    public InvalidInputException(String message){
        super(message);
    }

    public InvalidInputException(Throwable cause){
        super(cause);
    }

    public InvalidInputException(Throwable cause, String message){
        super(message,cause);
    }
}

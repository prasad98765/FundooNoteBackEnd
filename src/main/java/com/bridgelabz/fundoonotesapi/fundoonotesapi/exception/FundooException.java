package com.bridgelabz.fundoonotesapi.fundoonotesapi.exception;

public class FundooException extends RuntimeException {

    public ExceptionType type;

    public enum ExceptionType{
        INVALID_DATA,
        INVALID_LINK,
        USER_ALREADY_REGISTERED,
        INVALID_PASSWORD,
        INVALID_EMAIL,
        INVALID_USER,
        INVALID_NOTE,
        INVALID_TOKEN,
        INVALID_LABEL;
    }

    public FundooException(ExceptionType type,String message){
        super(message);
        this.type = type;
    }


}

package com.example.reservation2.Exceptions;

public class RoomUnavailableException extends RuntimeException {

    public RoomUnavailableException(String message) {super (message);}

    public RoomUnavailableException(String message, Throwable cause){
        super(message, cause);
    }
}

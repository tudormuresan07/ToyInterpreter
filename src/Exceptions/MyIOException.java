package Exceptions;

import java.io.IOException;

public class MyIOException extends IOException {
    public MyIOException(String ErrorMessage){
        super(ErrorMessage);
    }
}

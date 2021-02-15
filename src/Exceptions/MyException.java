package Exceptions;

public class MyException extends RuntimeException{
    public MyException(String ErrorMessage){
        super(ErrorMessage);
    }
}

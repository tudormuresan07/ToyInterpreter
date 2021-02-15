package Model.ADT;

import Exceptions.MyDictionaryException;
import Exceptions.MyStackException;

import java.util.List;
import java.util.Stack;

public interface IStack<T> {

    void push(T elem) throws MyStackException;
    T pop() throws MyStackException;
    boolean isEmpty();
    List<T> getAll() throws MyStackException;
    T top() throws MyStackException;
    String toString();
}

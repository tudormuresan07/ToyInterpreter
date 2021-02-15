package Model.ADT;

import Exceptions.MyListException;

import java.util.List;

public interface IList<T> {

    int getLength();
    void add(T elem);
    void remove(int pos) throws MyListException;
    T getElement(int pos) throws MyListException;
    void setElement(int pos, T newValue) throws MyListException;
    List<T> getAll();
    String toString();
}

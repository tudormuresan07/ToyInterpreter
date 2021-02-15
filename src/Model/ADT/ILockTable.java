package Model.ADT;

import Exceptions.MyDictionaryException;
import Exceptions.MyException;

import java.util.HashMap;

public interface ILockTable<T1,T2> {
    void add(T1 v1, T2 v2) throws MyDictionaryException;
    void update(T1 v1,T2 v2) throws MyDictionaryException;
    void remove(T1 v1) throws MyDictionaryException;
    T2 getValue(T1 id) throws MyDictionaryException;
    boolean containsKey(T1 key);
    HashMap<T1,T2> getContent();
    String toString();
}

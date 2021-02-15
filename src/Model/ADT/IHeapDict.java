package Model.ADT;

import Exceptions.MyDictionaryException;

import java.util.HashMap;
import java.util.Map;

public interface IHeapDict<T1,T2> {
    int getFirstFreeAddress();
    void add(T1 v1, T2 v2) throws MyDictionaryException;
    void update(T1 v1,T2 v2) throws MyDictionaryException;
    void remove(T1 v1) throws MyDictionaryException;
    T2 getValue(T1 id) throws MyDictionaryException;
    boolean containsKey(T1 key);
    Map<T1,T2> getContent();
    void setContent(Map<T1,T2> newContent);
    String toString();
}

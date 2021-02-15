package Model.ADT;

import Exceptions.MyDictionaryException;
import Exceptions.MyException;

import java.util.HashMap;
import java.util.Iterator;

public class MyLockTable<T1,T2> implements ILockTable<T1,T2> {
    HashMap<T1, T2> dict;

    public MyLockTable(){
        this.dict=new HashMap<T1,T2>();
    }

    @Override
    public void add(T1 v1,T2 v2) throws MyDictionaryException {
        if(this.dict.get(v1)!=null)
            throw new MyDictionaryException("Given key already in the dictionary!");
        this.dict.put(v1,v2);
    }

    @Override
    public void update(T1 v1,T2 v2) throws MyDictionaryException{
        if(this.dict.get(v1)==null)
            throw new MyDictionaryException("Key not in the dictionary!");
        else
            this.dict.put(v1,v2);
    }

    @Override
    public void remove(T1 v1) throws MyDictionaryException {
        if(this.dict.get(v1)==null)
            throw new MyDictionaryException("Key not in the dictionary!");
        else
            this.dict.remove(v1);
    }

    @Override
    public T2 getValue(T1 id) throws MyDictionaryException{
        if(this.dict.containsKey(id)==false)
            throw new MyDictionaryException("Key not in the dictionary!");
        else
            return this.dict.get(id);
    }

    @Override
    public boolean containsKey(T1 key) {
        return this.dict.containsKey(key);
    }

    @Override
    public HashMap<T1, T2> getContent() {
        return this.dict;
    }

    @Override
    public String toString() {
        String output="";
        for(T1 key: this.dict.keySet()){
            output+=key.toString()+"-->"+this.dict.get(key).toString()+"\n";
        }
        return output;
    }
}

package Model.ADT;

import Exceptions.MyDictionaryException;
import Exceptions.MyException;

import java.util.HashMap;
import java.util.Iterator;

public class MyDictionary<T1,T2> implements IDictionary<T1,T2>,Cloneable{
    HashMap<T1, T2> dict;

    public MyDictionary(){
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

    /*@Override
    public MyDictionary<T1, T2> clone() throws MyException{
        MyDictionary<T1,T2> newDict=new MyDictionary<>();
        try{
            newDict=(MyDictionary<T1,T2>)super.clone();
        }
        catch(CloneNotSupportedException e){
            throw new MyException(e.getMessage());
        }
        return newDict;
    }*/

    @Override
    public IDictionary<T1, T2> clone() throws MyException {
        IDictionary<T1,T2> newMap=new MyDictionary<>();
        Iterator<T1> it=dict.keySet().iterator();
        while(it.hasNext())
        {
            T1 elem=it.next();
            newMap.add(elem,dict.get(elem));
        }
        return newMap;
    }

    @Override
    public void clear() {
        for(T1 key:this.dict.keySet()){
            this.remove(key);
        }
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

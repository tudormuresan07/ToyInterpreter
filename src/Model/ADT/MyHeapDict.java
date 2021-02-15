package Model.ADT;

import Exceptions.MyDictionaryException;

import java.util.HashMap;
import java.util.Map;

public class MyHeapDict<T1,T2> implements IHeapDict<T1,T2>{
    HashMap<T1, T2> heap;

    public MyHeapDict() {
        this.heap = new HashMap<T1, T2>();
    }

    @Override
    public int getFirstFreeAddress() { return this.heap.size()+1; }

    @Override
    public void add(T1 v1, T2 v2) throws MyDictionaryException {
        if(this.heap.get(v1)!=null)
            throw new MyDictionaryException("Given key already in the dictionary!");
        this.heap.put(v1,v2);
    }

    @Override
    public void update(T1 v1, T2 v2) throws MyDictionaryException {
        if(this.heap.get(v1)==null)
            throw new MyDictionaryException("Key not in the dictionary!");
        else
            this.heap.put(v1,v2);
    }

    @Override
    public void remove(T1 v1) throws MyDictionaryException {
        if(this.heap.get(v1)==null)
            throw new MyDictionaryException("Key not in the dictionary!");
        else
            this.heap.remove(v1);
    }

    @Override
    public T2 getValue(T1 id) throws MyDictionaryException {
        if(this.heap.containsKey(id)==false)
            throw new MyDictionaryException("Key not in the dictionary!");
        else
            return this.heap.get(id);
    }

    @Override
    public boolean containsKey(T1 key) {
        return this.heap.containsKey(key);
    }

    @Override
    public String toString() {
        String output="";
        for(T1 key: this.heap.keySet()){
            output+=key.toString()+": "+this.heap.get(key).toString()+"\n";
        }
        return output;
    }

    @Override
    public Map<T1,T2> getContent() {
        return this.heap;
    }

    @Override
    public void setContent(Map<T1, T2> newContent) {
        this.heap.clear();
        HashMap<T1,T2> newHashMapContent=(HashMap<T1,T2>)newContent;
        this.heap.putAll(newHashMapContent);
    }
}

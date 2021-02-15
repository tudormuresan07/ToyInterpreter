package Model.ADT;

import Exceptions.MyException;
import Exceptions.MyListException;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements IList<T>{
    ArrayList<T> elements;

    public MyList() {
        this.elements = new ArrayList<T>();
    }

    @Override
    public int getLength() {
        return this.elements.size();
    }

    @Override
    public void add(T elem) {
        this.elements.add(elem);
    }

    @Override
    public void remove(int pos) throws MyListException {
        if(pos>=this.elements.size())
            throw new MyListException("Invalid position!");
        else
            this.elements.remove(pos);
    }

    @Override
    public T getElement(int pos) throws MyListException {
        if(pos>=this.elements.size())
            throw new MyListException("Invalid position!");
        else
            return this.elements.get(pos);
    }

    @Override
    public void setElement(int pos, T newValue) throws MyListException {
        if(pos>=this.elements.size())
            throw new MyListException("Invalid position!");
        else
            this.elements.set(pos,newValue);
    }

    @Override
    public List<T> getAll(){
        return this.elements;
    }

    @Override
    public String toString() {
        String output="";
        for(T e:this.elements){
            output+=e.toString()+"\n";
        }
        return output;
    }
}

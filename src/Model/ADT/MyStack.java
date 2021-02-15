package Model.ADT;

import Exceptions.MyStackException;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MyStack<T> implements IStack<T> {
    Stack<T> stack;

    public MyStack(){
        this.stack=new Stack<>();
    }

    @Override
    public void push(T elem) throws MyStackException {
        try{
            this.stack.push(elem);
        }
        catch(Exception e){
            throw new MyStackException("The stack is full");
        }
    }

    @Override
    public T pop() throws MyStackException {
        try{
            return this.stack.pop();
        }
        catch(Exception e){
            throw new MyStackException("The stack is empty");
        }
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    @Override
    public List<T> getAll() throws MyStackException{
        try{
            List<T> exeList=new ArrayList<T>();
            while(!isEmpty()){
                T elem=pop();
                exeList.add(elem);
            }
            for(int i=exeList.size()-1;i>=0;i--){
                push(exeList.get(i));
            }
            return exeList;
        }
        catch(Exception e){
            throw new MyStackException("The stack is empty");
        }
    }

    @Override
    public T top() throws MyStackException{
        try{
            return this.stack.firstElement();
        }
        catch(Exception e){
            throw new MyStackException("The stack is empty");
        }
    }

    @Override
    public String toString() {
        String output="";
        Stack<T> secondary=new Stack<>();
        while(!this.isEmpty()){
            T elem=this.pop();
            secondary.push(elem);
            output+=elem.toString()+"\n";
        }
        while(!secondary.isEmpty()){
            T elem=secondary.pop();
            this.push(elem);
        }
        return output;
    }
}

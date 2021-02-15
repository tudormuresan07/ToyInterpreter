package Model;

import Exceptions.MyException;
import Model.ADT.*;
import Model.Stmt.IStmt;
import Model.Value.BoolValue;
import Model.Value.IValue;
import Model.Value.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class PrgState {

    private IStack<IStmt> exeStack;
    private IDictionary<String, IValue> symTable;
    private IList<String> out;
    private IFileDictionary<StringValue, BufferedReader> fileTable;
    private IHeapDict<Integer,IValue> heap;
    private IStmt originalPrg;
    private int ID;
    private static int staticID=0;
    private final static Object lock_object=new Object();

    private synchronized static int incID(){
        staticID=staticID+1;
        return staticID;
    }

    public PrgState(){
        this.exeStack=new MyStack<IStmt>();
        this.symTable=new MyDictionary<String,IValue>();
        this.out=new MyList<String>();
        this.heap=new MyHeapDict<>();
        this.fileTable=new MyFileDictionary<>();
    }

    public PrgState(IStack<IStmt> exeStack, IDictionary<String, IValue> symTable, IList<String> out,IFileDictionary<StringValue,BufferedReader> fileTable,IHeapDict<Integer,IValue> heap,IStmt original) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.fileTable=fileTable;
        this.heap=heap;
        this.originalPrg=original;
        this.ID=incID();
    }

    public IStack<IStmt> getExeStack() {
        return exeStack;
    }

    public IDictionary<String, IValue> getSymTable() {
        return symTable;
    }

    public IList<String> getOut() {
        return out;
    }

    public IFileDictionary<StringValue,BufferedReader> getFileTable(){return fileTable;}

    public IHeapDict<Integer,IValue> getHeap(){return this.heap;}

    public IStmt getOriginalPrg(){return this.originalPrg;}

    public int getID() { return ID; }

    public void setExeStack(IStack<IStmt> exeStack) { this.exeStack = exeStack; }

    public void setSymTable(IDictionary<String, IValue> symTable) { this.symTable = symTable; }

    public void setOut(IList<String> out) { this.out = out; }

    public void setFileTable(IFileDictionary<StringValue, BufferedReader> fileTable) { this.fileTable = fileTable; }

    public void setHeap(IHeapDict<Integer,IValue> heap){this.heap=heap;}

    public void setOriginalPrg(IStmt original){this.originalPrg=original;}

    public void setID(int newID){this.ID=newID;}

    public BoolValue isNotCompleted(){
        if(this.exeStack.isEmpty())
            return new BoolValue(false);
        else
            return new BoolValue(true);
    }

    public PrgState oneStep() throws MyException, IOException,CloneNotSupportedException {
        synchronized (lock_object){
            if (this.exeStack.isEmpty())
                throw new MyException("The execution stack is empty");
            else {
                IStmt crtStmt = this.exeStack.pop();
                return crtStmt.execute(this);
            }
        }
    }

    @Override
    public String toString() {
        return "ID:"+ID+"\nExeStack:\n"+this.getExeStack().toString()+"\nSymTable:\n"+this.getSymTable().toString()+"\nOutput:\n"+this.getOut().toString()+"\nFileTable:\n"+this.getFileTable().toString()+"\nHeap:\n"+this.getHeap().toString()+"\n-----------------------------\n";
    }
}

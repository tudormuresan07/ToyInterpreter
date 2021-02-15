package Model.Stmt;

import Exceptions.MyDictionaryException;
import Exceptions.MyException;
import Exceptions.MyListException;
import Exceptions.MyStackException;
import Model.ADT.IDictionary;
import Model.ADT.IStack;
import Model.ADT.MyDictionary;
import Model.ADT.MyStack;
import Model.PrgState;
import Model.Type.IType;
import Model.Value.IValue;

import java.io.IOException;

public class ForkStmt implements IStmt{
    private IStmt stmt;

    public ForkStmt(IStmt stmt) {
        this.stmt = stmt;
    }

    public IStmt getStmt(){return this.stmt;}

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException,CloneNotSupportedException {
        PrgState newPrgState=new PrgState();
        //setting exeStack for the new program state
        IStack<IStmt> newExeStack=new MyStack<>();
        newExeStack.push(this.stmt);
        newPrgState.setExeStack(newExeStack);
        //setting symTable for the new program state
        IDictionary<String, IValue> newSymTable=state.getSymTable().clone();
        newPrgState.setSymTable(newSymTable);
        //setting heap,fileTable,output for the new program state
        newPrgState.setHeap(state.getHeap());
        newPrgState.setFileTable(state.getFileTable());
        newPrgState.setOut(state.getOut());
        newPrgState.setID(state.getID()+1);
        return newPrgState;
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws MyException {
        this.stmt.typecheck(typeEnv.clone());
        return typeEnv;
    }

    @Override
    public String toString() {
        return "fork("+this.stmt.toString()+")";
    }
}

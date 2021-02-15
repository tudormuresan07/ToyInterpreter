package Model.Stmt;

import Exceptions.MyDictionaryException;
import Exceptions.MyException;
import Exceptions.MyListException;
import Exceptions.MyStackException;
import Model.ADT.IDictionary;
import Model.ADT.IStack;
import Model.PrgState;
import Model.Type.IType;

import java.io.IOException;

public class SleepStmt implements IStmt{
    private int number;

    public SleepStmt(int number){this.number=number;}

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, CloneNotSupportedException {
        IStack<IStmt> exeStack = state.getExeStack();
        if(number!=0) {
            number = number - 1;
            exeStack.push(this);
        }
        return state;
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws MyException {
        return null;
    }

    @Override
    public String toString() {
        return "sleep("+Integer.toString(number)+")";
    }
}

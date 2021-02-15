package Model.Stmt;

import Exceptions.MyDictionaryException;
import Exceptions.MyException;
import Exceptions.MyListException;
import Exceptions.MyStackException;
import Model.ADT.IDictionary;
import Model.ADT.IHeapDict;
import Model.ADT.IStack;
import Model.Exp.IExp;
import Model.Exp.NotExp;
import Model.PrgState;
import Model.Type.IType;
import Model.Value.IValue;

import java.io.IOException;

public class RepeatStmt implements IStmt{
    private final IExp exp2;
    private final IStmt stmt1;

    public RepeatStmt(IExp exp2,IStmt stmt1){
        this.exp2=exp2;
        this.stmt1=stmt1;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, CloneNotSupportedException {
        IStack<IStmt> exeStack=state.getExeStack();
        IDictionary<String, IValue> symTable=state.getSymTable();
        IHeapDict<Integer,IValue> heap=state.getHeap();

        IStmt stmtToBePushed=new WhileStmt(new NotExp(exp2),stmt1);
        exeStack.push(stmtToBePushed);
        return null;
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws MyException {
        return null;
    }

    @Override
    public String toString() {
        return "repeat "+stmt1.toString()+"until "+exp2.toString();
    }
}

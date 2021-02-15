package Model.Stmt;

import Exceptions.MyDictionaryException;
import Exceptions.MyException;
import Exceptions.MyListException;
import Exceptions.MyStackException;
import Model.ADT.IDictionary;
import Model.ADT.IStack;
import Model.Exp.IExp;
import Model.PrgState;
import Model.Type.BoolType;
import Model.Type.IType;
import Model.Value.BoolValue;
import Model.Value.IValue;

import java.io.IOException;

public class WhileStmt implements IStmt{
    private final IExp exp;
    private final IStmt stmt;

    public WhileStmt(IExp exp, IStmt stmt) {
        this.exp = exp;
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        IStack<IStmt> exeStack=state.getExeStack();
        IValue v=exp.eval(state.getSymTable(),state.getHeap());

        if(v.getType().equals(new BoolType())){
            BoolValue boolV=(BoolValue)v;
            if(boolV.getVal()){
                exeStack.push(this);
                exeStack.push(stmt);
            }
            return null;
        }
        else
            throw new MyException("The type of the expression "+exp.toString()+" is not BoolType");
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws MyException {
        IType expType=exp.typecheck(typeEnv);
        if(expType.equals(new BoolType())){
            stmt.typecheck(typeEnv.clone());
            return typeEnv;
        }
        else
            throw new MyException("The condition of WHILE does not have the type bool");
    }

    @Override
    public String toString() {
        return "while "+exp.toString()+" do{ "+stmt.toString()+" }";
    }
}

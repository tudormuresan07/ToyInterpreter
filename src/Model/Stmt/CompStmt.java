package Model.Stmt;

import Exceptions.MyDictionaryException;
import Exceptions.MyException;
import Exceptions.MyStackException;
import Model.ADT.IDictionary;
import Model.ADT.IStack;
import Model.PrgState;
import Model.Type.IType;

public class CompStmt implements IStmt{
    private final IStmt first;
    private final IStmt second;

    public CompStmt(IStmt first, IStmt second) {
        this.first = first;
        this.second = second;
    }

    public IStmt getFirst() {
        return first;
    }

    public IStmt getSecond() {
        return second;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IStack<IStmt> stack=state.getExeStack();
        stack.push(second);
        stack.push(first);

        return null;
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws MyException {
        return second.typecheck(first.typecheck(typeEnv));
    }

    @Override
    public String toString() {
        return "("+first.toString()+";"+second.toString()+")";
    }
}

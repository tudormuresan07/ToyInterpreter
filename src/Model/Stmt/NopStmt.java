package Model.Stmt;

import Exceptions.MyException;
import Model.ADT.IDictionary;
import Model.PrgState;
import Model.Type.IType;

public class NopStmt implements IStmt{
    public NopStmt(){}

    @Override
    public PrgState execute(PrgState state) throws MyException {
        return null;
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "Nope";
    }
}

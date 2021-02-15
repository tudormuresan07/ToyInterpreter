package Model.Stmt;

import Exceptions.MyException;
import Model.ADT.IDictionary;
import Model.ADT.IHeapDict;
import Model.ADT.IList;
import Model.Exp.IExp;
import Model.PrgState;
import Model.Type.IType;
import Model.Value.IValue;

public class PrintStmt implements IStmt{
    private final IExp e;

    public PrintStmt(IExp e){
        this.e=e;
    }

    @Override
    public String toString() {
        return "print(" +e.toString()+")";
    }

    @Override
    public PrgState execute(PrgState state) {
        IDictionary<String, IValue> symbolTable= state.getSymTable();
        IList<String> output=state.getOut();
        IHeapDict<Integer,IValue> heap=state.getHeap();
        try {
            IValue result = this.e.eval(symbolTable,heap );
            output.add(result.toString());
            return null;
        }
        catch(MyException exception){
            return null;
        }

    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws MyException {
        e.typecheck(typeEnv);
        return typeEnv;
    }
}

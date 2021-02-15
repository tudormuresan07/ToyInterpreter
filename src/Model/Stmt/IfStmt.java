package Model.Stmt;

import Exceptions.MyException;
import Model.ADT.IDictionary;
import Model.ADT.IHeapDict;
import Model.ADT.IStack;
import Model.Exp.IExp;
import Model.PrgState;
import Model.Type.BoolType;
import Model.Type.IType;
import Model.Value.BoolValue;
import Model.Value.IValue;

public class IfStmt implements IStmt{
    private final IExp e;
    private final IStmt thenS;
    private final IStmt elseS;

    public IfStmt(IExp e, IStmt thenS, IStmt elseS) {
        this.e = e;
        this.thenS = thenS;
        this.elseS = elseS;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IStack<IStmt> exeStack=state.getExeStack();
        IDictionary<String,IValue> symTable=state.getSymTable();
        IHeapDict<Integer,IValue> heap=state.getHeap();
        IValue val=  e.eval(symTable, heap);
        if(val instanceof BoolValue){
            BoolValue boolVal=(BoolValue) val;
            if(boolVal.getVal()==true){
                exeStack.push(thenS);
            }
            else{
                exeStack.push(elseS);
            }
        }
        else {
            throw new MyException("Value is not a boolean");
        }
        return null;

    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws MyException {
        IType expType=this.e.typecheck(typeEnv);
        if(expType.equals(new BoolType())){
            thenS.typecheck(typeEnv.clone());
            elseS.typecheck(typeEnv.clone());
            return typeEnv;
        }
        else
            throw new MyException("The condition of IF does not have the type bool");
    }

    @Override
    public String toString() {
        return "(IF("+e.toString()+")THEN("+thenS.toString()+")ELSE("+elseS.toString()+"))";
    }
}

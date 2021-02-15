package Model.Stmt;

import Exceptions.*;
import Model.ADT.IDictionary;
import Model.ADT.IHeapDict;
import Model.ADT.IStack;
import Model.Exp.IExp;
import Model.PrgState;
import Model.Type.IType;
import Model.Value.IValue;

public class AssignStmt implements IStmt{
    private final String id;
    private final IExp e;

    public AssignStmt(String id,IExp e){
        this.id=id;
        this.e=e;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IStack<IStmt> exeStack=state.getExeStack();
        IDictionary<String, IValue> symTable=state.getSymTable();
        IHeapDict<Integer,IValue> heap=state.getHeap();

        if(symTable.containsKey(id)){
            IValue val=e.eval(symTable, heap);
            IType typeID=symTable.getValue(id).getType();
            if(val.getType().equals(typeID)) {
                symTable.update(id, val);
                state.setSymTable(symTable);
                return null;
            }
            else
                throw new MyException("Declared type of variable "+id+" and type of the assigned expression do not match");
        }
        else
            throw new ExpressionEvaluationException("The used variable "+id+" was not declared before");
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws MyException {
        IType idType,eType;
        idType=typeEnv.getValue(this.id);
        eType=e.typecheck(typeEnv);
        if(idType.equals(eType)){
            return typeEnv;
        }
        else
            throw new MyException("Assignment: right hand and left hand side have different types");
    }

    @Override
    public String toString() {
        return id+"="+e.toString();
    }
}

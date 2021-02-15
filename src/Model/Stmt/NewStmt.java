package Model.Stmt;

import Exceptions.MyException;
import Model.ADT.IDictionary;
import Model.ADT.IHeapDict;
import Model.Exp.IExp;
import Model.PrgState;
import Model.Type.IType;
import Model.Type.RefType;
import Model.Value.IValue;
import Model.Value.RefValue;

import java.io.IOException;

public class NewStmt implements IStmt{
    private final String var_name;
    private final IExp exp;

    public NewStmt(String var_name, IExp exp) {
        this.var_name = var_name;
        this.exp = exp;
    }

    public String getVar_name() { return var_name; }

    public IExp getExp() { return exp; }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        IDictionary<String, IValue> symTable=state.getSymTable();
        IHeapDict<Integer,IValue> heap=state.getHeap();

        if(symTable.containsKey(var_name)){
            IValue v=symTable.getValue(var_name);
            if(v.getType() instanceof RefType){
                RefValue refV=(RefValue) v;
                IValue e=exp.eval(symTable, heap);
                RefType vRefType=(RefType)refV.getType();
                IType vInnerType=vRefType.getInnerType();
                if(e.getType().equals(vInnerType)){
                    int newFreeAddress=heap.getFirstFreeAddress();
                    heap.add(newFreeAddress,e);
                    symTable.update(var_name,new RefValue(newFreeAddress,vInnerType));
                    state.setHeap(heap);
                    state.setSymTable(symTable);
                    return null;
                }
                else
                    throw new MyException("The type of expression "+exp.toString()+" is not the same as the type of "+refV.toString());


            }
            else
                throw new MyException("The type of "+var_name+" is not RefType");
        }
        else
            throw new MyException("SymTable does not contain the key "+var_name);
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws MyException {
        IType varType=typeEnv.getValue(var_name);
        IType expType=exp.typecheck(typeEnv);
        if(varType.equals(new RefType(expType))){
            return typeEnv;
        }
        else
            throw new MyException("NEW stmt: right hand side and left hand side have different types");
    }

    @Override
    public String toString() {
        //String type=exp.eval(new MyDictionary<String, IValue>() ).getType().toString();
        //return type+" "+var_name+"=new "+type+"("+exp.toString()+")";
        return "new("+var_name+", "+this.exp.toString()+")";
    }
}

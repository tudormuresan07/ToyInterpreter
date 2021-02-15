package Model.Stmt;

import Exceptions.MyDictionaryException;
import Exceptions.MyException;
import Exceptions.MyListException;
import Exceptions.MyStackException;
import Model.ADT.IDictionary;
import Model.ADT.IFileDictionary;
import Model.ADT.IHeapDict;
import Model.Exp.IExp;
import Model.PrgState;
import Model.Type.IType;
import Model.Type.IntType;
import Model.Type.RefType;
import Model.Value.IValue;
import Model.Value.RefValue;
import Model.Value.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class WriteHeapStmt implements IStmt{
    private final String var_name;
    private final IExp exp;
    public WriteHeapStmt(String var_name, IExp exp) {
        this.var_name = var_name;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        IDictionary<String, IValue> symTable=state.getSymTable();
        IHeapDict<Integer,IValue> heap=state.getHeap();

        if(symTable.containsKey(var_name)){
            IValue v=symTable.getValue(var_name);
            if(v.getType() instanceof RefType){
                RefValue refV=(RefValue)v;
                int addr= refV.getAddress();
                if(heap.containsKey(addr)){
                    IValue e=this.exp.eval(symTable,heap);
                    RefType vType=(RefType)refV.getType();
                    IType vInnerType=vType.getInnerType();
                    if(e.getType().equals(vInnerType)){
                        heap.update(addr,e);
                        return null;
                    }
                    else
                        throw new MyException("The result of expression does not have its type equal to the locationType of the var_name type");
                }
                else
                    throw new MyException(String.valueOf(addr)+" is not a key in the heap");
            }
            else
                throw new MyException("The type of variable "+var_name+" is not a RefType");
        }
        else
            throw new MyException(var_name+" is not in symTable");
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws MyException {
        IType expType=exp.typecheck(typeEnv);
        if(expType.equals(new IntType())){
            IType varType=typeEnv.getValue(var_name);
            if(varType.equals(new RefType(expType))){
                return typeEnv;
            }
            throw new MyException("Write Heap Stmt: Type of variable is not ref type of expression type");
        }
        else
            throw new MyException("Write Heap Stmt: Type of expression is not integer");
    }

    @Override
    public String toString() {
        return "wH("+var_name+")="+exp.toString();
    }
}

package Model.Stmt;

import Exceptions.MyException;
import Model.ADT.IDictionary;
import Model.ADT.IFileDictionary;
import Model.ADT.IHeapDict;
import Model.Exp.IExp;
import Model.PrgState;
import Model.Type.IType;
import Model.Type.StringType;
import Model.Value.IValue;
import Model.Value.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class closeRFile implements IStmt{
    private final IExp exp;

    public closeRFile(IExp exp) {
        this.exp = exp;
    }

    public IExp getExp() {
        return exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        IDictionary<String, IValue> symTable=state.getSymTable();
        IFileDictionary<StringValue, BufferedReader> fileTable=state.getFileTable();
        IHeapDict<Integer,IValue> heap=state.getHeap();
        IValue val=exp.eval(symTable, heap);
        if(val.getType().equals(new StringType())){
            StringValue sVal=(StringValue) val;
            if(fileTable.containsKey(sVal)){
                BufferedReader fileDescriptor=fileTable.getValue(sVal);
                fileDescriptor.close();
                fileTable.remove(sVal);
                //symTable.remove(sVal.getVal());
                state.setFileTable(fileTable);
                //state.setSymTable(symTable);
                return null;
            }
            else
                throw new MyException("The file "+sVal.toString()+" is not opened");
        }
        else
            throw new MyException("The evaluation of the expression didn't return a StringType");
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws MyException {
        IType expType=exp.typecheck(typeEnv);
        if(expType.equals(new StringType())){
            return typeEnv;
        }
        else
            throw new MyException("Close file stmt: Type of expression is not string");
    }

    @Override
    public String toString() {
        return "Close File "+exp.toString();
    }
}

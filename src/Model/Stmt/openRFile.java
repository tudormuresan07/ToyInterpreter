package Model.Stmt;

import Exceptions.*;
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
import java.io.FileReader;
import java.io.IOException;

public class openRFile implements IStmt{
    private final IExp exp;

    public openRFile(IExp exp) {
        this.exp = exp;
    }

    public IExp getExp() {
        return exp;
    }


    @Override
    public PrgState execute(PrgState state) throws MyException,IOException {
        IFileDictionary<StringValue, BufferedReader> fileTable=state.getFileTable();
        IDictionary<String,IValue> symTable=state.getSymTable();
        IHeapDict<Integer,IValue> heap=state.getHeap();

        IValue val=exp.eval(symTable, heap);
        if(val.getType().equals(new StringType())){
            StringValue sVal=(StringValue) val;
            if(!fileTable.containsKey(sVal)){
                try{
                    BufferedReader fileDescriptor=new BufferedReader(new FileReader(sVal.getVal()));
                    //symTable.add(sVal.getVal(), new IntType().defaultValue());
                    fileTable.add(sVal,fileDescriptor);
                    state.setFileTable(fileTable);
                    //state.setSymTable(symTable);
                    return null;
                }
                catch(IOException e){
                    throw new IOException(e.getMessage());
                }
            }
            else
                throw new MyException("The file name is already being used");
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
            throw new MyException("Open file stmt: Type of expression is not string");
    }

    @Override
    public String toString() {
        return "Open File "+exp.toString();
    }
}

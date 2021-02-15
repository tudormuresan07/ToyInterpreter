package Model.Stmt;

import Exceptions.MyException;
import Model.ADT.IDictionary;
import Model.ADT.IFileDictionary;
import Model.ADT.IHeapDict;
import Model.Exp.IExp;
import Model.PrgState;
import Model.Type.IType;
import Model.Type.IntType;
import Model.Type.StringType;
import Model.Value.IValue;
import Model.Value.IntValue;
import Model.Value.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class readFile implements IStmt{
    private final IExp exp;
    private final String var_name;

    public readFile(IExp exp, String var_name) {
        this.exp = exp;
        this.var_name = var_name;
    }

    public IExp getExp() {
        return exp;
    }

    public String getVar_name() {
        return var_name;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        IDictionary<String, IValue> symTable=state.getSymTable();
        IFileDictionary<StringValue, BufferedReader> fileTable=state.getFileTable();
        IHeapDict<Integer,IValue> heap=state.getHeap();

        if(symTable.containsKey(var_name)){
            IValue v=symTable.getValue(var_name);
            if(v.getType().equals(new IntType())){
                IValue val=exp.eval(symTable, heap);
                if(val.getType().equals(new StringType())){
                    StringValue sVal=(StringValue) val;
                    if(fileTable.containsKey(sVal)){
                        BufferedReader fileDescriptor=fileTable.getValue(sVal);
                        String line=fileDescriptor.readLine();
                        IValue iVal;
                        if(line==null){
                            iVal=new IntValue(0);
                        }
                        else{
                            int i=Integer.parseInt(line);
                            iVal=new IntValue(i);
                        }
                        symTable.update(var_name,iVal);
                        state.setSymTable(symTable);
                        return null;
                    }
                    else
                        throw new MyException("The file "+sVal.toString()+" is not opened");
                }
                else
                    throw new MyException("The evaluation of the expression didn't return a StringType");
            }
            else
                throw new MyException(var_name+" is not an IntType");
        }
        else
            throw new MyException(var_name.toString()+" is not defined in symTable");

    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws MyException {
        IType expType=exp.typecheck(typeEnv);
        if(expType.equals(new StringType())){
            IType varType=typeEnv.getValue(var_name);
            if(varType.equals(new IntType())){
                return typeEnv;
            }
            else
                throw new MyException("Read file stmt: Variable "+var_name+" is not of type int");
        }
        else
            throw new MyException("Close file stmt: Type of expression is not string");
    }

    @Override
    public String toString() {
        return "Read File "+exp.toString();
    }
}

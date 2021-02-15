package Model.Stmt;

import Exceptions.MyException;
import Model.ADT.IDictionary;
import Model.PrgState;
import Model.Type.*;
import Model.Value.BoolValue;
import Model.Value.IValue;
import Model.Value.IntValue;


public class VarDeclStmt implements IStmt{
    private final IType type;
    private final String ID;

    public VarDeclStmt(String id,IType type) {
        this.type = type;
        ID = id;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IDictionary<String, IValue> symTable=state.getSymTable();
        if(!symTable.containsKey(ID))
        {
            if(type.equals(new IntType())){
                //symTable.add(ID, new IntValue(0));
                symTable.add(ID,new IntType().defaultValue());
            }
            if(type.equals(new BoolType())){
                //symTable.add(ID, new BoolValue(false));
                symTable.add(ID, new BoolType().defaultValue());
            }
            if(type.equals(new StringType())){
                symTable.add(ID,new StringType().defaultValue());
            }
            if(type instanceof RefType){
                symTable.add(ID,type.defaultValue());
            }
            state.setSymTable(symTable);
            return null;
        }
        else
            throw new MyException("The variable "+ID+" has already been defined");

    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws MyException {
        typeEnv.add(this.ID,this.type);
        return typeEnv;
    }

    @Override
    public String toString() {
        return type.toString()+" "+ID;
    }
}

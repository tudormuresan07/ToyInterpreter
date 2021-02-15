package Model.Exp;

import Exceptions.MyException;
import Model.ADT.IDictionary;
import Model.ADT.IHeapDict;
import Model.Type.IType;
import Model.Value.IValue;

public class VarExp implements IExp{
    private final String ID;

    public VarExp(String ID) {
        this.ID = ID;
    }

    @Override
    public IValue eval(IDictionary<String, IValue> table, IHeapDict<Integer, IValue> heap) throws MyException {
        return table.getValue(ID);
    }

    @Override
    public IType typecheck(IDictionary<String, IType> typeEnv) throws MyException {
        return typeEnv.getValue(ID);
    }

    @Override
    public String toString() {
        return this.ID;
    }
}

package Model.Exp;

import Exceptions.MyException;
import Model.ADT.IDictionary;
import Model.ADT.IHeapDict;
import Model.Type.IType;
import Model.Value.IValue;

public class ValueExp implements IExp{
    private final IValue val;

    public ValueExp(IValue v){
        this.val=v;
    }

    @Override
    public IValue eval(IDictionary<String, IValue> table, IHeapDict<Integer, IValue> heap) throws MyException {
        return val;
    }

    @Override
    public IType typecheck(IDictionary<String, IType> typeEnv) throws MyException {
        return val.getType();
    }

    @Override
    public String toString() {
        return val.toString();
    }
}

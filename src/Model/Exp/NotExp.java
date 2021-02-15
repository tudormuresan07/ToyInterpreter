package Model.Exp;

import Exceptions.MyException;
import Model.ADT.IDictionary;
import Model.ADT.IHeapDict;
import Model.Type.BoolType;
import Model.Type.IType;
import Model.Value.BoolValue;
import Model.Value.IValue;

public class NotExp implements IExp{
    private final IExp e;

    public NotExp(IExp e){this.e=e;}

    @Override
    public IValue eval(IDictionary<String, IValue> table, IHeapDict<Integer, IValue> heap) throws MyException {
        if(e.eval(table,heap).getType() instanceof BoolType){
            BoolValue boolE=(BoolValue)e.eval(table,heap);
            if(boolE.getVal())
                return new BoolValue(false);
            else
                return new BoolValue(true);
        }
        return null;
    }

    @Override
    public IType typecheck(IDictionary<String, IType> typeEnv) throws MyException {
        return null;
    }

    @Override
    public String toString() {
        return "!"+this.e.toString();
    }
}

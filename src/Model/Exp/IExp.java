package Model.Exp;

import Exceptions.MyException;
import Model.ADT.IDictionary;
import Model.ADT.IHeapDict;
import Model.Type.IType;
import Model.Value.IValue;

public interface IExp {
    public IValue eval(IDictionary<String, IValue> table, IHeapDict<Integer, IValue> heap) throws MyException;
    IType typecheck(IDictionary<String,IType> typeEnv) throws MyException;
}

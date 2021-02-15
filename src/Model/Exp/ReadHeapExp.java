package Model.Exp;

import Exceptions.MyException;
import Model.ADT.IDictionary;
import Model.ADT.IHeapDict;
import Model.Type.IType;
import Model.Type.RefType;
import Model.Value.IValue;
import Model.Value.RefValue;

public class ReadHeapExp implements IExp{
    private final IExp exp;

    public ReadHeapExp(IExp exp) {
        this.exp = exp;
    }

    @Override
    public IValue eval(IDictionary<String, IValue> table, IHeapDict<Integer, IValue> heap) throws MyException {
        IValue v=this.exp.eval(table,heap);
        if(v instanceof RefValue){
            RefValue refV=(RefValue)v;
            int addr=refV.getAddress();
            if(heap.containsKey(addr)) {
                IValue locationType = heap.getValue(addr);
                return locationType;
            }
            else
                throw new MyException("The heap does not contain the key "+String.valueOf(addr));
        }
        else
            throw new MyException("The expression is not evaluated as a RefValue");
    }

    @Override
    public IType typecheck(IDictionary<String, IType> typeEnv) throws MyException {
        IType type=exp.typecheck(typeEnv);
        if(type instanceof RefType){
            RefType refType=(RefType) type;
            return refType.getInner();
        }
        else
            throw new MyException("The rH argument is not a Ref Type");
    }

    @Override
    public String toString() {
        return "rH("+exp.toString()+")";
    }
}

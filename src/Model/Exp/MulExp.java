package Model.Exp;

import Exceptions.MyException;
import Model.ADT.IDictionary;
import Model.ADT.IHeapDict;
import Model.Type.IType;
import Model.Type.IntType;
import Model.Value.IValue;
import Model.Value.IntValue;
import Utils.Operation;

import java.net.MalformedURLException;

public class MulExp implements IExp{
    private final IExp e1;
    private final IExp e2;

    public MulExp(IExp e1,IExp e2){this.e1=e1;this.e2=e2;}

    @Override
    public IValue eval(IDictionary<String, IValue> table, IHeapDict<Integer, IValue> heap) throws MyException {
        IExp mulExp=new ArithmeticExp(e1,e2, Operation.MUL);
        IExp addExp=new ArithmeticExp(e1,e2,Operation.ADD);
        IExp subExp=new ArithmeticExp(mulExp,addExp,Operation.SUB);
        return subExp.eval(table,heap);
    }

    @Override
    public IType typecheck(IDictionary<String, IType> typeEnv) throws MyException {
        return null;
    }

    @Override
    public String toString() {
        return "MUL("+e1.toString()+","+e2.toString()+")";
    }
}

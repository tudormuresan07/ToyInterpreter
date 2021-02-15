package Model.Exp;

import Exceptions.MyException;
import Model.ADT.IDictionary;
import Model.ADT.IHeapDict;
import Model.Type.BoolType;
import Model.Type.IType;
import Model.Value.BoolValue;
import Model.Value.IValue;
import Utils.logicSign;


public class LogicExp implements IExp{
    private final IExp e1;
    private final IExp e2;
    private final logicSign LS;

    public LogicExp(IExp e1, IExp e2, logicSign LS) {
        this.e1 = e1;
        this.e2 = e2;
        this.LS = LS;
    }

    @Override
    public IValue eval(IDictionary<String, IValue> table, IHeapDict<Integer, IValue> heap) throws MyException {
        IValue v1,v2;
        v1=this.e1.eval(table, heap);
        if(v1.getType().equals(new BoolType())){
            v2=this.e2.eval(table, heap);
            if(v2.getType().equals(new BoolType())){
                BoolValue b1=(BoolValue)v1;
                BoolValue b2=(BoolValue)v2;
                boolean n1,n2;
                n1=b1.getVal();
                n2=b2.getVal();
                switch (this.LS){
                    case AND :
                        return new BoolValue(n1 && n2);
                    case OR:
                        return new BoolValue(n1 || n2);
                }

            }
            else
                throw new MyException("The second operand is not a boolean");
        }
        else
            throw new MyException("The first operand is not a boolean");
        return null;
    }

    @Override
    public IType typecheck(IDictionary<String, IType> typeEnv) throws MyException {
        IType type1,type2;
        type1=e1.typecheck(typeEnv);
        type2=e2.typecheck(typeEnv);
        if(type1.equals(new BoolType())){
            if(type2.equals(new BoolType())){
                return new BoolType();
            }
            else
                throw new MyException("Second operand is not a boolean");
        }
        else
            throw new MyException("First operand is not a boolean");
    }

    @Override
    public String toString() {
        switch (this.LS){
            case AND :
                return e1.toString()+" and "+e2.toString();
            case OR:
                return e1.toString()+" or "+e2.toString();
        }
        return null;
    }
}

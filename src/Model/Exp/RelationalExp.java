package Model.Exp;

import Exceptions.MyException;
import Model.ADT.IDictionary;
import Model.ADT.IHeapDict;
import Model.Type.BoolType;
import Model.Type.IType;
import Model.Type.IntType;
import Model.Value.BoolValue;
import Model.Value.IValue;
import Model.Value.IntValue;

public class RelationalExp implements IExp {
    private final IExp e1;
    private final IExp e2;
    private final String operator;
    public RelationalExp(IExp e1,IExp e2,String operator){this.e1=e1; this.e2=e2;this.operator=operator;}

    @Override
    public IValue eval(IDictionary<String, IValue> table, IHeapDict<Integer, IValue> heap) throws MyException {
        IValue v1,v2;
        v1=e1.eval(table, heap);
        v2=e2.eval(table, heap);

        if(v1.getType().equals(v2.getType())){
            if(v1.getType().equals(new IntType())){ //both expressions are int
                boolean result=false;
                IntValue i1=(IntValue)v1;
                IntValue i2=(IntValue)v2;
                int n1=i1.getVal();
                int n2=i2.getVal();
                switch (this.operator){
                    case "<":
                        result=n1<n2;
                        break;
                    case "<=":
                        result=(n1<=n2);
                        break;
                    case "==":
                        result=(n1==n2);
                        break;
                    case "!=":
                        result=(n1!=n2);
                        break;
                    case ">":
                        result=(n1>n2);
                        break;
                    case ">=":
                        result=(n1>=n2);
                        break;
                    default:
                        break;
                }
                
                return new BoolValue(result);
            }
            else
                throw new MyException("The evaluation of the 2 expressions returned anything but IntType");
        }
        else {
            throw new MyException("The evaluation of the 2 expressions returned different types");
        }
    }

    @Override
    public IType typecheck(IDictionary<String, IType> typeEnv) throws MyException {
        IType type1,type2;
        type1=e1.typecheck(typeEnv);
        type2=e2.typecheck(typeEnv);
        if(type1.equals(new IntType())){
            if(type2.equals(new IntType())){
                return new BoolType();
            }
            else
                throw new MyException("Second operand is not an integer");
        }
        else
            throw new MyException("First operand is not an integer");
    }

    @Override
    public String toString() {
        return e1.toString()+" "+this.operator+" "+e2.toString();
    }
}

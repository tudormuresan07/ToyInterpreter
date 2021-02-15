package Model.Exp;

import Exceptions.ExpressionEvaluationException;
import Exceptions.MyException;
import Model.ADT.IDictionary;
import Model.ADT.IHeapDict;
import Model.Type.IType;
import Model.Type.IntType;
import Model.Value.IValue;
import Model.Value.IntValue;
import Utils.Operation;


public class ArithmeticExp implements IExp{
    private final IExp e1;
    private final IExp e2;
    private final Operation op;

    public ArithmeticExp(IExp e1, IExp e2, Operation op) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public IValue eval(IDictionary<String, IValue> table, IHeapDict<Integer, IValue> heap) throws MyException {
        IValue v1,v2;
        v1=this.e1.eval(table, heap);
        if(v1.getType().equals(new IntType())){
            v2=this.e2.eval(table, heap);
            if(v2.getType().equals(new IntType())){
                IntValue i1=(IntValue)v1;
                IntValue i2=(IntValue)v2;
                int n1,n2;
                n1=i1.getVal();
                n2=i2.getVal();
                switch(this.op){
                    case ADD :
                        return new IntValue(n1+n2);
                    case SUB:
                        return new IntValue(n1-n2);
                    case MUL:
                        return new IntValue(n1*n2);
                    case DIV:
                        if(n2==0)
                            throw new ExpressionEvaluationException("Division by zero");
                        else
                            return new IntValue(n1/n2);


                }
            }
            else
                throw new MyException("The second operand is not an integer");
        }
        else
            throw new MyException("The first operand is not an integer");
        return null;
    }

    @Override
    public IType typecheck(IDictionary<String, IType> typeEnv) throws MyException {
        IType type1,type2;
        type1=e1.typecheck(typeEnv);
        type2=e2.typecheck(typeEnv);
        if(type1.equals(new IntType())){
            if(type2.equals(new IntType())){
                return new IntType();
            }
            else
                throw new MyException("Second operand is not an integer");
        }
        else
            throw new MyException("First operand is not an integer");
    }

    @Override
    public String toString() {
        switch (this.op){
            case ADD:
                return e1.toString()+" + "+e2.toString();
            case SUB:
                return e1.toString()+" - "+e2.toString();
            case MUL:
                return e1.toString()+" * "+e2.toString();
            case DIV:
                return e1.toString()+" / "+e2.toString();
        }
        return null;
    }
}

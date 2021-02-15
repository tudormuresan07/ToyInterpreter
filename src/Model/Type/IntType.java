package Model.Type;

import Model.Value.IValue;
import Model.Value.IntValue;

public class IntType implements IType {

    public IntType() {}

    @Override
    public boolean equals(Object another) {
        if(another instanceof IntType)
            return true;
        else
            return false;
    }

    @Override
    public IValue defaultValue() {
        return new IntValue(0);
    }

    public String toString(){
        return "int";
    }
}

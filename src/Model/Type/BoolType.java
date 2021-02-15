package Model.Type;

import Model.Value.BoolValue;
import Model.Value.IValue;

public class BoolType implements IType {

    public BoolType(){}

    @Override
    public boolean equals(Object another) {
        if(another instanceof BoolType)
            return true;
        else
            return false;
    }

    @Override
    public IValue defaultValue() {
        return new BoolValue(false);
    }

    public String toString(){
        return "boolean";
    }
}

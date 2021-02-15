package Model.Type;

import Model.Value.IValue;
import Model.Value.StringValue;

public class StringType implements IType{

    public StringType(){};

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof StringType)
            return true;
        else
            return false;
    }

    @Override
    public IValue defaultValue() {
        return new StringValue("");
    }

    @Override
    public String toString() {
        return "String";
    }
}

package Model.Value;

import Model.Type.BoolType;
import Model.Type.IType;

public class BoolValue implements IValue{
    private final boolean val;
    public BoolValue(boolean v){
        this.val=v;
    }

    public boolean getVal(){
        return this.val;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }

    public IType getType(){
        return new BoolType();
    }
}

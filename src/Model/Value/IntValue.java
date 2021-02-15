package Model.Value;

import Model.Type.IType;
import Model.Type.IntType;

import java.util.Objects;

public class IntValue implements IValue{
    private final int val;
    public IntValue(int v){
        this.val=v;
    }
    public int getVal(){
        return this.val;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof IntValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }

    public IType getType(){
        return new IntType();
    }
}

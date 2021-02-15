package Model.Value;

import Model.Type.IType;
import Model.Type.StringType;

import java.util.Objects;

public class StringValue implements IValue{
    private final String val;
    public StringValue(String st) { this.val = st; }

    public String getVal() { return val; }

    @Override
    public boolean equals(Object o) {
        return o instanceof StringValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
    }

    @Override
    public String toString() { return val; }

    @Override
    public IType getType() { return new StringType(); }
}

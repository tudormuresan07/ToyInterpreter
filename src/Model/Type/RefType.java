package Model.Type;

import Model.Value.IValue;
import Model.Value.RefValue;

import java.util.Objects;

public class RefType implements IType{
    private IType inner;

    public RefType(IType inner) { this.inner = inner; }

    public IType getInner() { return inner; }

    @Override
    public boolean equals(Object o) {
        if(o instanceof RefType)
            return inner.equals(((RefType) o).getInner());
        else
            return false;
    }

    public IType getInnerType(){return this.inner;}

    @Override
    public IValue defaultValue() {
        return new RefValue(0,inner);
    }

    @Override
    public String toString() {
        return "Ref("+inner.toString()+")";
    }

}

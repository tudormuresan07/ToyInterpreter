package Model.Value;

import Model.Type.IType;
import Model.Type.RefType;

public class RefValue implements IValue{
    private int address;
    private IType locationType;

    public RefValue(int address, IType locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    public int getAddress() { return address; }

    @Override
    public IType getType() {
        return new RefType(locationType);
    }

    @Override
    public String toString() {
        if (address == 0) {
            return "null";
        } else {
            return "&(" + String.valueOf(address) + "->" + locationType + ")";
        }

    }
}

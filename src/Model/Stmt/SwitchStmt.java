package Model.Stmt;

import Exceptions.MyDictionaryException;
import Exceptions.MyException;
import Exceptions.MyListException;
import Exceptions.MyStackException;
import Model.ADT.IDictionary;
import Model.ADT.IStack;
import Model.Exp.IExp;
import Model.Exp.RelationalExp;
import Model.PrgState;
import Model.Type.IType;
import Model.Value.IValue;

import javax.management.relation.Relation;
import java.io.IOException;

public class SwitchStmt implements IStmt{
    private final IExp exp;
    private final IExp exp1;
    private final IStmt stmt1;
    private final IExp exp2;
    private final IStmt stmt2;
    private final IStmt stmt3;

    public SwitchStmt(IExp exp, IExp exp1, IStmt stmt1, IExp exp2, IStmt stmt2, IStmt stmt3) {
        this.exp = exp;
        this.exp1 = exp1;
        this.stmt1 = stmt1;
        this.exp2 = exp2;
        this.stmt2 = stmt2;
        this.stmt3 = stmt3;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, CloneNotSupportedException {
        IStack<IStmt> exeStack=state.getExeStack();
        IDictionary<String, IValue> symTable=state.getSymTable();

        IStmt stmtToBePushed=new IfStmt(new RelationalExp(exp,exp1,"=="),
                stmt1,new IfStmt(new RelationalExp(exp,exp2,"=="), stmt2,stmt3));

        exeStack.push(stmtToBePushed);
        return null;
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws MyException {
        return null;
    }

    @Override
    public String toString() {
        return "switch("+exp.toString()+")(case "+exp1.toString()+": "+stmt1.toString()+")(case "+exp2.toString()+": "+stmt2.toString()+
                ")(default: "+stmt3.toString()+")";
    }
}

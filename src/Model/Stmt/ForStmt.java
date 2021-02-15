package Model.Stmt;

import Exceptions.MyDictionaryException;
import Exceptions.MyException;
import Exceptions.MyListException;
import Exceptions.MyStackException;
import Model.ADT.IDictionary;
import Model.ADT.IStack;
import Model.Exp.IExp;
import Model.Exp.RelationalExp;
import Model.Exp.VarExp;
import Model.PrgState;
import Model.Type.IType;
import Model.Value.IValue;

import java.io.IOException;

public class ForStmt implements IStmt{
    private final IExp e1;
    private final IExp e2;
    private final IExp e3;
    private final String var_name;
    private final IStmt stmt;

    public ForStmt(IExp e1, IExp e2, IExp e3,String var_name,IStmt stmt) {
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
        this.var_name=var_name;
        this.stmt=stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, CloneNotSupportedException {
        IStack<IStmt> stack=state.getExeStack();
        IDictionary<String, IValue> symTable=state.getSymTable();

        IStmt stmtToBePushed=new CompStmt(new AssignStmt(var_name,e1),
                new WhileStmt(new RelationalExp(new VarExp(var_name),e2,"<"),new CompStmt(stmt,new AssignStmt(var_name,e3))));

        stack.push(stmtToBePushed);
       // state.setExeStack(stack);
        return null;
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws MyException {
        return null;
    }

    @Override
    public String toString() {
        return "for("+var_name.toString()+"="+e1.toString()+";"+
                var_name.toString()+"<"+e2.toString()+";"+
                var_name.toString()+"="+e3.toString()+")"+stmt.toString();
    }
}

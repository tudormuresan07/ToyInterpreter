package Model.Stmt;

import Exceptions.MyDictionaryException;
import Exceptions.MyException;
import Exceptions.MyListException;
import Exceptions.MyStackException;
import Model.ADT.IDictionary;
import Model.PrgState;
import Model.Type.IType;

import java.io.IOException;

public interface IStmt{
    PrgState execute(PrgState state) throws MyException, MyDictionaryException, MyListException, MyStackException, IOException,CloneNotSupportedException;
    IDictionary<String, IType> typecheck(IDictionary<String,IType> typeEnv) throws MyException;
    String toString();
}

package Repository;

import Exceptions.MyException;
import Exceptions.MyIOException;
import Model.ADT.MyList;
import Model.PrgState;

import java.util.List;

public interface IRepository {

    List<PrgState> getPrgList();
    void setPrgList(List<PrgState> newList);
    //PrgState getCrtPrg() throws MyException;
    void addPrg(PrgState state);
    PrgState getByIndex(int index) throws MyException;
    void logPrgStateExec(PrgState state);
    void deletePrgState(int id) throws MyException;
}

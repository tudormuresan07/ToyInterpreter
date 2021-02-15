package Repository;

import Exceptions.MyException;
import Exceptions.MyIOException;
import Model.ADT.MyList;
import Model.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


/*public class Repository implements IRepository{
    private List<PrgState> programs;
    private String logFilePath;

    public Repository(String logFilePath){
        this.programs=new ArrayList<>();
        this.logFilePath=logFilePath;
    }

    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    @Override
    public List<PrgState> getPrgList() {
        return this.programs;
    }

    @Override
    public void setPrgList(List<PrgState> newList) { this.programs=newList; }

    @Override
    public PrgState getByIndex(int index) throws MyException{
        return this.programs.get(index);
    }

    @Override
    public void addPrg(PrgState state) {
        this.programs.add(state);
    }

    @Override
    public String toString() {
        return programs.toString();
    }

    @Override
    public void logPrgStateExec(PrgState state) {
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath, true)));
            logFile=logFile.append(state.toString());
            logFile.close();

        }
        catch(IOException e){
            //throw new MyIOException(e.getMessage());
        }
    }
}*/

public class Repository implements IRepository{
    private List<PrgState> programs;
    private PrgState prg;
    private String logFilePath;

    public Repository(PrgState prg,String logFilePath){
        this.programs=new ArrayList<>();
        this.prg=prg;
        this.logFilePath=logFilePath;
        this.programs.add(prg);
    }

    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    @Override
    public List<PrgState> getPrgList() {
        return this.programs;
    }

    @Override
    public void setPrgList(List<PrgState> newList) { this.programs=newList; }

    @Override
    public PrgState getByIndex(int index) throws MyException{
        return this.programs.get(index);
    }

    @Override
    public void addPrg(PrgState state) {
        this.programs.add(state);
    }

    @Override
    public String toString() {
        return programs.toString();
    }

    @Override
    public void deletePrgState(int id) throws MyException{
        int pos=-1;
        for(int i=0;i<this.programs.size();i++){
            if(this.programs.get(i).getID()==id)
                pos=i;
        }
        if(pos==-1)
            throw new MyException("The id you are trying to remove is not in the list!");
        this.programs.remove(pos);
    }

    @Override
    public void logPrgStateExec(PrgState state) {
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath, true)));
            logFile=logFile.append(state.toString());
            logFile.close();

        }
        catch(IOException e){
            //throw new MyIOException(e.getMessage());
        }
    }
}

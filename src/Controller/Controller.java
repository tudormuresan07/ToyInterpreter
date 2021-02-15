package Controller;

import Exceptions.MyException;
import Exceptions.StatementExecutionException;
import Model.ADT.*;
import Model.PrgState;
import Model.Stmt.IStmt;
import Model.Value.IValue;
import Model.Value.StringValue;
import Repository.IRepository;

import java.io.BufferedReader;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    private IRepository prgStateRepo;
    private ExecutorService executor;

    public Controller(IRepository repo){
        this.prgStateRepo=repo;
        //this.executor=Executors.newFixedThreadPool(2);
    }

    public List<PrgState> getAll(){return this.prgStateRepo.getPrgList();}

    public void addState(IStack<IStmt> stack, IDictionary<String, IValue> dictionary, IList<String> list, IFileDictionary<StringValue, BufferedReader> fileTable, IHeapDict<Integer,IValue> heap,IStmt originalPrg) throws StatementExecutionException{
        if(stack.isEmpty())
            throw new StatementExecutionException("The execution stack is empty");
        PrgState state=new PrgState(stack,dictionary,list,fileTable,heap,originalPrg);
        this.prgStateRepo.addPrg(state);
    }

    public void addPrgState(PrgState prgState) throws StatementExecutionException{
        if(prgState.getExeStack().isEmpty()){
            throw new StatementExecutionException("The execution stack is empty");
        }
        this.prgStateRepo.addPrg(prgState);
    }


    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList){
        //removes from list inPrgList all the programs that are completed and then returns the resulted list
        return inPrgList.stream()
                .filter(p->p.isNotCompleted().getVal())
                .collect(Collectors.toList());
    }

    public void oneStepForAllPrg(List<PrgState> prgList) throws MyException, InterruptedException{
        //before the execution, prints all the prgStates into the log file
        prgList.forEach(prg -> prgStateRepo.logPrgStateExec(prg));
        //RUN concurrently one step for each of the existing PrgStates
        //-----------------------------------------------------------------------
        //prepare the list of callables
        List<Callable<PrgState>> callList=prgList.stream()
                .map((PrgState p)->(Callable<PrgState>)(()->{return p.oneStep();}))
                .collect(Collectors.toList());
        //starts the execution of the callables.It returns the list of new created PrgStates (namely threads)
        List<PrgState> newPrgList=executor.invokeAll(callList).stream()
                .map(future-> {
                    try {
                        return future.get();
                    } catch (MyException | InterruptedException | ExecutionException e) {
                        throw new MyException(e.getMessage());
                    }
                })
                .filter(p->p!=null)
                .collect(Collectors.toList());

        //adds the new created threads to the list of existing threads
        prgList.addAll(newPrgList);
        //-----------------------------------------------------------------------
        //after the execution, prints the PrgState List into the lof file
        prgList.forEach(prg-> prgStateRepo.logPrgStateExec(prg));
        //saves the current programs in the repo
        prgStateRepo.setPrgList(prgList);

    }

    public void allStep() throws MyException, InterruptedException{
        executor= Executors.newFixedThreadPool(2);
        List<PrgState> prgList=removeCompletedPrg(prgStateRepo.getPrgList());
        while(prgList.size()>0){
            for(PrgState p: prgList){
                p.getHeap().setContent(GarbageCollector.safeGarbageCollector(
                        GarbageCollector.getAllAddresses(GarbageCollector.getAddr(p.getSymTable().getContent().values()),
                                GarbageCollector.getAddr(p.getHeap().getContent().values())),p.getHeap().getContent()));
            }
            oneStepForAllPrg(prgList);
            prgList=removeCompletedPrg(prgStateRepo.getPrgList());
        }
        executor.shutdownNow();

        //update repo
        prgStateRepo.setPrgList(prgList);
    }

   public void oneStepGUI() throws RuntimeException,InterruptedException {
       //refresh();
       executor= Executors.newFixedThreadPool(2);
       List<PrgState> programStates=removeCompletedPrg(prgStateRepo.getPrgList());
       //List<PrgState> programStates=prgStateRepo.getPrgList();
       if(programStates.size()>0)
       {
           programStates.forEach((PrgState p)->p.getHeap().setContent(GarbageCollector.safeGarbageCollector(
                   GarbageCollector.getAllAddresses(GarbageCollector.getAddr(p.getSymTable().getContent().values()),
                           GarbageCollector.getAddr(p.getHeap().getContent().values())),p.getHeap().getContent())));
           oneStepForAllPrg(programStates);
           prgStateRepo.setPrgList(programStates);
           //programStates=removeCompletedPrg(prgStateRepo.getPrgList());
       }
       else
       {
           executor.shutdownNow();
           prgStateRepo.setPrgList(programStates);
           throw new RuntimeException("finished");
       }
   }

    @Override
    public String toString() {
        String res="";
        for(PrgState p: this.prgStateRepo.getPrgList())
            res=res+p.getExeStack().toString()+"\n";
        return res;
    }
}



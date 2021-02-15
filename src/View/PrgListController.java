package View;

import Controller.Controller;
import Exceptions.MyException;
import Model.ADT.*;
import Model.Exp.*;
import Model.PrgState;
import Model.Stmt.*;
import Model.Type.BoolType;
import Model.Type.IType;
import Model.Type.IntType;
import Model.Type.RefType;
import Model.Value.BoolValue;
import Model.Value.IValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Repository.Repository;
import Utils.Operation;
import com.sun.jdi.Value;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import javafx.util.Callback;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrgListController implements Initializable {

    static Repository repo1,repo2,repo3,repo4,repo5,repo6,repo7,repo8,repo9,repo10,repo11;
    static Controller ctrl1,ctrl2,ctrl3,ctrl4,ctrl5,ctrl6,ctrl7,ctrl8,ctrl9,ctrl10,ctrl11;
    static IStmt stmt1,stmt2,stmt3,stmt4,stmt5,stmt6,stmt7,stmt8,stmt9,stmt10,stmt11;

    @FXML
    ListView<Controller> myPrgList;
    @FXML
    Button runButton;

    public void setUp(){
        stmt1=new CompStmt(new VarDeclStmt("a",new IntType()),
                new CompStmt(new VarDeclStmt("b",new IntType()),
                        new CompStmt(new AssignStmt("a",new ArithmeticExp(new ValueExp(new IntValue(2)),
                                new ArithmeticExp(new ValueExp(new IntValue(3)),new ValueExp(new IntValue(5)), Operation.MUL),Operation.ADD)),
                                new CompStmt(new AssignStmt("b",new ArithmeticExp(new VarExp("a"),new ValueExp(new IntValue(1)),Operation.ADD)),
                                        new PrintStmt(new VarExp("b"))))));
        IStack<IStmt> exeStack1=new MyStack<>();
        IDictionary<String, IValue> symTable1=new MyDictionary<>();
        IList<String> output1=new MyList<>();
        IFileDictionary<StringValue, BufferedReader> fileTable1=new MyFileDictionary<>();
        IHeapDict<Integer,IValue> heap1=new MyHeapDict<>();
        exeStack1.push(stmt1);
        PrgState prgState1=new PrgState(exeStack1,symTable1,output1,fileTable1,heap1,stmt1);
        repo1=new Repository(prgState1,"log1.txt");
        ctrl1=new Controller(repo1);

        stmt2=new CompStmt(new VarDeclStmt("a", new BoolType()),
                new CompStmt(new VarDeclStmt("v",new IntType()),
                        new CompStmt(new AssignStmt("a",new ValueExp(new BoolValue(true))),
                                new CompStmt(
                                        new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new IntValue(2))),new AssignStmt("v",new ValueExp(new IntValue(3)))),
                                        new PrintStmt(new VarExp("v"))))));
        IStack<IStmt> exeStack2=new MyStack<>();
        IDictionary<String, IValue> symTable2=new MyDictionary<>();
        IList<String> output2=new MyList<>();
        IFileDictionary<StringValue, BufferedReader> fileTable2=new MyFileDictionary<>();
        IHeapDict<Integer,IValue> heap2=new MyHeapDict<>();
        exeStack2.push(stmt2);
        PrgState prgState2=new PrgState(exeStack2,symTable2,output2,fileTable2,heap2,stmt2);
        repo2=new Repository(prgState2,"log2.txt");
        ctrl2=new Controller(repo2);

        stmt3=new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),
                new CompStmt(new NewStmt("v",new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a",new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewStmt("a",new VarExp("v")),
                                        new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new VarExp("a")))))));

        IStack<IStmt> exeStack3=new MyStack<>();
        IDictionary<String, IValue> symTable3=new MyDictionary<>();
        IList<String> output3=new MyList<>();
        IFileDictionary<StringValue, BufferedReader> fileTable3=new MyFileDictionary<>();
        IHeapDict<Integer,IValue> heap3=new MyHeapDict<>();
        exeStack3.push(stmt3);
        PrgState prgState3=new PrgState(exeStack3,symTable3,output3,fileTable3,heap3,stmt3);
        repo3=new Repository(prgState3,"log3.txt");
        ctrl3=new Controller(repo3);

        stmt4=new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),
                new CompStmt(new NewStmt("v",new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a",new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewStmt("a",new VarExp("v")),
                                        new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                                new PrintStmt(new ArithmeticExp(new ReadHeapExp(new ReadHeapExp(new VarExp("a"))),new ValueExp(new IntValue(5)), Operation.ADD)))))));

        IStack<IStmt> exeStack4=new MyStack<>();
        IDictionary<String, IValue> symTable4=new MyDictionary<>();
        IList<String> output4=new MyList<>();
        IFileDictionary<StringValue, BufferedReader> fileTable4=new MyFileDictionary<>();
        IHeapDict<Integer,IValue> heap4=new MyHeapDict<>();
        exeStack4.push(stmt4);
        PrgState prgState4=new PrgState(exeStack4,symTable4,output4,fileTable4,heap4,stmt4);
        repo4=new Repository(prgState4,"log4.txt");
        ctrl4=new Controller(repo4);

        stmt5=new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),
                new CompStmt(new NewStmt("v",new ValueExp(new IntValue(20))),
                        new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                new CompStmt(new WriteHeapStmt("v",new ValueExp(new IntValue(30))),
                                        new PrintStmt(new ArithmeticExp(new ReadHeapExp(new VarExp("v")),new ValueExp(new IntValue(5)),Operation.ADD))))));
        IStack<IStmt> exeStack5=new MyStack<>();
        IDictionary<String, IValue> symTable5=new MyDictionary<>();
        IList<String> output5=new MyList<>();
        IFileDictionary<StringValue, BufferedReader> fileTable5=new MyFileDictionary<>();
        IHeapDict<Integer,IValue> heap5=new MyHeapDict<>();
        exeStack5.push(stmt5);
        PrgState prgState5=new PrgState(exeStack5,symTable5,output5,fileTable5,heap5,stmt5);
        repo5=new Repository(prgState5,"log5.txt");
        ctrl5=new Controller(repo5);

        stmt6=new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new VarDeclStmt("a",new RefType(new IntType())),
                        new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(10))),
                                new CompStmt(new NewStmt("a",new ValueExp(new IntValue(22))),
                                        new CompStmt(new ForkStmt(new CompStmt(new WriteHeapStmt("a",new ValueExp(new IntValue(30))),
                                                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(32))),
                                                        new CompStmt(new PrintStmt(new VarExp("v")),
                                                                new PrintStmt(new ReadHeapExp(new VarExp("a"))))))),
                                                new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp(new VarExp("a")))))))));

        IStack<IStmt> exeStack6=new MyStack<>();
        IDictionary<String, IValue> symTable6=new MyDictionary<>();
        IList<String> output6=new MyList<>();
        IFileDictionary<StringValue, BufferedReader> fileTable6=new MyFileDictionary<>();
        IHeapDict<Integer,IValue> heap6=new MyHeapDict<>();
        exeStack6.push(stmt6);
        PrgState prgState6=new PrgState(exeStack6,symTable6,output6,fileTable6,heap6,stmt6);
        repo6=new Repository(prgState6,"log6.txt");
        ctrl6=new Controller(repo6);

        stmt7=new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(20))),
                        new CompStmt(new ForStmt(new ValueExp(new IntValue(0)),new ValueExp(new IntValue(3)),
                                new ArithmeticExp(new VarExp("v"),new ValueExp(new IntValue(1)),Operation.ADD),"v",
                                new ForkStmt(new CompStmt(new PrintStmt(new VarExp("v")),
                                        new AssignStmt("v", new ArithmeticExp(new VarExp("v"),
                                                new ValueExp(new IntValue(1)),Operation.ADD))))),
                                new PrintStmt(new ArithmeticExp(new VarExp("v"),new ValueExp(new IntValue(10)),Operation.MUL)))
        ));

        IStack<IStmt> exeStack7=new MyStack<>();
        IDictionary<String, IValue> symTable7=new MyDictionary<>();
        IList<String> output7=new MyList<>();
        IFileDictionary<StringValue, BufferedReader> fileTable7=new MyFileDictionary<>();
        IHeapDict<Integer,IValue> heap7=new MyHeapDict<>();
        exeStack7.push(stmt7);
        PrgState prgState7=new PrgState(exeStack7,symTable7,output7,fileTable7,heap7,stmt7);
        repo7=new Repository(prgState7,"log7.txt");
        ctrl7=new Controller(repo7);

        stmt8=new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(0))),
                        new CompStmt(new WhileStmt(new RelationalExp(new VarExp("v"),new ValueExp(new IntValue(3)),"<"),
                                new CompStmt(new ForkStmt(
                                        new CompStmt(new PrintStmt(new VarExp("v")),new AssignStmt("v",new ArithmeticExp(new VarExp("v"),new ValueExp(new IntValue(1)),Operation.ADD)))),
                                        new AssignStmt("v",new ArithmeticExp(new VarExp("v"),new ValueExp(new IntValue(1)),Operation.ADD)))),
                                new CompStmt(new SleepStmt(5), new PrintStmt(new ArithmeticExp(new VarExp("v"),new ValueExp(new IntValue(10)),Operation.MUL)))
                        )));

        IStack<IStmt> exeStack8=new MyStack<>();
        IDictionary<String, IValue> symTable8=new MyDictionary<>();
        IList<String> output8=new MyList<>();
        IFileDictionary<StringValue, BufferedReader> fileTable8=new MyFileDictionary<>();
        IHeapDict<Integer,IValue> heap8=new MyHeapDict<>();
        exeStack8.push(stmt8);
        PrgState prgState8=new PrgState(exeStack8,symTable8,output8,fileTable8,heap8,stmt8);
        repo8=new Repository(prgState8,"log8.txt");
        ctrl8=new Controller(repo8);

        stmt9=new CompStmt(new VarDeclStmt("v1",new IntType()),
                new CompStmt(new VarDeclStmt("v2",new IntType()),
                        new CompStmt(new AssignStmt("v1",new ValueExp(new IntValue(2))),
                                new CompStmt(new AssignStmt("v2",new ValueExp(new IntValue(3))),
                                        new IfStmt(new RelationalExp(new VarExp("v1"),new ValueExp(new IntValue(0)),"!="),
                                                new PrintStmt(new MulExp(new VarExp("v1"),new VarExp("v2"))),
                                                new PrintStmt(new VarExp("v1")))))));

        IStack<IStmt> exeStack9=new MyStack<>();
        IDictionary<String, IValue> symTable9=new MyDictionary<>();
        IList<String> output9=new MyList<>();
        IFileDictionary<StringValue, BufferedReader> fileTable9=new MyFileDictionary<>();
        IHeapDict<Integer,IValue> heap9=new MyHeapDict<>();
        exeStack9.push(stmt9);
        PrgState prgState9=new PrgState(exeStack9,symTable9,output9,fileTable9,heap9,stmt9);
        repo9=new Repository(prgState9,"log9.txt");
        ctrl9=new Controller(repo9);

        stmt10=new CompStmt(new VarDeclStmt("a",new IntType()),
                new CompStmt(new VarDeclStmt("b",new IntType()),
                        new CompStmt(new VarDeclStmt("c",new IntType()),
                                new CompStmt(new AssignStmt("a",new ValueExp(new IntValue(1))),
                                        new CompStmt(new AssignStmt("b",new ValueExp(new IntValue(2))),
                                                new CompStmt(new AssignStmt("c",new ValueExp(new IntValue(5))),
                                                        new CompStmt(new SwitchStmt(new ArithmeticExp(new VarExp("a"),new ValueExp(new IntValue(10)),Operation.MUL),
                                                                new ArithmeticExp(new VarExp("b"),new VarExp("c"),Operation.MUL),
                                                                new CompStmt(new PrintStmt(new VarExp("a")),new PrintStmt(new VarExp("b"))),
                                                                new ValueExp(new IntValue(10)),
                                                                new CompStmt(new PrintStmt(new ValueExp(new IntValue(100))),new PrintStmt(new ValueExp(new IntValue(200)))),
                                                                new PrintStmt(new ValueExp(new IntValue(300)))),
                                                                new PrintStmt(new ValueExp(new IntValue(300))))))))));

        IStack<IStmt> exeStack10=new MyStack<>();
        IDictionary<String, IValue> symTable10=new MyDictionary<>();
        IList<String> output10=new MyList<>();
        IFileDictionary<StringValue, BufferedReader> fileTable10=new MyFileDictionary<>();
        IHeapDict<Integer,IValue> heap10=new MyHeapDict<>();
        exeStack10.push(stmt10);
        PrgState prgState10=new PrgState(exeStack10,symTable10,output10,fileTable10,heap10,stmt10);
        repo10=new Repository(prgState10,"log10.txt");
        ctrl10=new Controller(repo10);

        stmt11=new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new RepeatStmt(new RelationalExp(new VarExp("v"),new ValueExp(new IntValue(3)),"=="),
                        new CompStmt(new ForkStmt(new CompStmt(new PrintStmt(new VarExp("v")),new AssignStmt("v",new ArithmeticExp(new VarExp("v"),new ValueExp(new IntValue(1)),Operation.SUB)))),
                                new AssignStmt("v",new ArithmeticExp(new VarExp("v"),new ValueExp(new IntValue(1)),Operation.ADD)))),
                        new CompStmt(new VarDeclStmt("x",new IntType()),
                                new CompStmt(new VarDeclStmt("y",new IntType()),
                                        new CompStmt(new VarDeclStmt("z",new IntType()),
                                                new CompStmt(new VarDeclStmt("w",new IntType()),
                                                        new CompStmt(new AssignStmt("x",new ValueExp(new IntValue(1))),
                                                                new CompStmt(new AssignStmt("y",new ValueExp(new IntValue(2))),
                                                                        new CompStmt(new AssignStmt("z",new ValueExp(new IntValue(3))),
                                                                                new CompStmt(new AssignStmt("w",new ValueExp(new IntValue(4))),
                                                                                        new PrintStmt(new ArithmeticExp(new VarExp("v"),new ValueExp(new IntValue(10)),Operation.MUL))))))))))));

        IStack<IStmt> exeStack11=new MyStack<>();
        IDictionary<String, IValue> symTable11=new MyDictionary<>();
        IList<String> output11=new MyList<>();
        IFileDictionary<StringValue, BufferedReader> fileTable11=new MyFileDictionary<>();
        IHeapDict<Integer,IValue> heap11=new MyHeapDict<>();
        exeStack11.push(stmt11);
        PrgState prgState11=new PrgState(exeStack11,symTable11,output11,fileTable11,heap11,stmt11);
        repo11=new Repository(prgState11,"log11.txt");
        ctrl11=new Controller(repo11);


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUp();
        ObservableList<Controller> myData= FXCollections.observableArrayList();
        try {
            IDictionary<String, IType> typeEnv1 = new MyDictionary<>();
            stmt1.typecheck(typeEnv1);
            myData.add(ctrl1);
        }
        catch(MyException e){}

        try {
            IDictionary<String, IType> typeEnv2 = new MyDictionary<>();
            stmt2.typecheck(typeEnv2);
            myData.add(ctrl2);
        }
        catch(MyException e){}

        try {
            IDictionary<String, IType> typeEnv3 = new MyDictionary<>();
            stmt3.typecheck(typeEnv3);
            myData.add(ctrl3);
        }
        catch(MyException e){}

        try {
            IDictionary<String, IType> typeEnv4 = new MyDictionary<>();
            stmt4.typecheck(typeEnv4);
            myData.add(ctrl4);
        }
        catch(MyException e){}

        try {
            IDictionary<String, IType> typeEnv5 = new MyDictionary<>();
            stmt5.typecheck(typeEnv5);
            myData.add(ctrl5);
        }
        catch(MyException e){}

        try {
            IDictionary<String, IType> typeEnv6 = new MyDictionary<>();
            stmt6.typecheck(typeEnv6);
            myData.add(ctrl6);
        }
        catch(MyException e){}

        /*try {
            IDictionary<String, IType> typeEnv7 = new MyDictionary<>();
            stmt7.typecheck(typeEnv7);
            myData.add(ctrl7);
        }
        catch(MyException e){}

        try {
            IDictionary<String, IType> typeEnv8 = new MyDictionary<>();
            stmt8.typecheck(typeEnv8);
            myData.add(ctrl8);
        }
        catch(MyException e){}

        try {
            IDictionary<String, IType> typeEnv9 = new MyDictionary<>();
            stmt9.typecheck(typeEnv9);
            myData.add(ctrl9);
        }
        catch(MyException e){}

        try {
            IDictionary<String, IType> typeEnv10 = new MyDictionary<>();
            stmt10.typecheck(typeEnv10);
            myData.add(ctrl10);
        }
        catch(MyException e){}

        try {
            IDictionary<String, IType> typeEnv11 = new MyDictionary<>();
            stmt11.typecheck(typeEnv11);
            myData.add(ctrl11);
        }
        catch(MyException e){}*/



/*
        myData.add(ctrl2);
        myData.add(ctrl3);
        myData.add(ctrl4);
        myData.add(ctrl5);
        myData.add(ctrl6);*/
        myData.add(ctrl7);
        myData.add(ctrl8);
        myData.add(ctrl9);
        myData.add(ctrl10);
        myData.add(ctrl11);

        myPrgList.setItems(myData);
        myPrgList.getSelectionModel().selectFirst();
        runButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage programStage=new Stage();
                Parent programRoot;
                Callback<Class<?>,Object> controllerFactory=type->{
                    if(type==PrgRunController.class){
                        return new PrgRunController(myPrgList.getSelectionModel().getSelectedItem());
                    }
                    else{
                        try{
                            return type;
                            //return type.newInstance();
                        }
                        catch(Exception e){
                            System.err.println("Could not create controller for "+type.getName());
                            throw new RuntimeException(e);
                        }
                    }
                };
                try{
                    FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("main_window.fxml"));
                    fxmlLoader.setControllerFactory(controllerFactory);
                    programRoot=fxmlLoader.load();
                    Scene programScene=new Scene(programRoot);
                    programStage.setTitle("Toy Language - Program Running");
                    programStage.setScene(programScene);
                    programStage.show();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
        });

    }
}
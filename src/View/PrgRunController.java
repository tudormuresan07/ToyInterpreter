package View;

import Controller.Controller;
import Model.PrgState;
import Model.Stmt.IStmt;
import Model.Value.IValue;
import Model.Value.StringValue;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class PrgRunController implements Initializable {
    Controller myController;
    private List<PrgState> unfinishedPrgs;
    @FXML
    Label nrPrgStates;
    @FXML
    Button runOneStepButton;
    @FXML
    TableView<HashMap.Entry<Integer, IValue>> heapTable;
    @FXML
    TableColumn<HashMap.Entry<Integer, IValue>,String> heapTableAddress;
    @FXML
    TableColumn<HashMap.Entry<Integer,IValue>,String> heapTableValue;
    @FXML
    ListView<String> outList;
    @FXML
    ListView<String> fileTable;
    @FXML
    ListView<String> prgStateList;
    @FXML
    TableView<HashMap.Entry<String,IValue>> symTable;
    @FXML
    TableColumn<HashMap.Entry<String,IValue>,String> symTableVariable;
    @FXML
    TableColumn<HashMap.Entry<String,IValue>,String> symTableValue;
    @FXML
    ListView<String> exeStackList;

    public PrgRunController(Controller myController){
        this.myController=myController;
        this.unfinishedPrgs=myController.getAll();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initialRun();
        prgStateList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setSymTableAndExeStack();
            }
        });
        runOneStepButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    myController.oneStepGUI();
                    updateUIComponents();
                }
                catch(InterruptedException | RuntimeException e){
                    nrPrgStates.setText("The number of PrgStates is: 0");
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Toy Language - Current program finished");
                    alert.setHeaderText(null);
                    alert.setContentText("Program successfully finished");
                    Button confirm=(Button)alert.getDialogPane().lookupButton(ButtonType.OK);
                    confirm.setDefaultButton(false);
                    confirm.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
                    alert.showAndWait();
                    Stage stage=(Stage) heapTable.getScene().getWindow();
                    stage.close();
                }
                //updateUIComponents();
            }
        });
    }

    public void initialRun(){
        setNumberLabel();
        setHeapTable();
        setOutList();
        setFileTable();
        setPrgStateList();
        prgStateList.getSelectionModel().selectFirst();
        setSymTableAndExeStack();
    }

    public void updateUIComponents(){
        setNumberLabel();
        setHeapTable();
        setOutList();
        setFileTable();
        setPrgStateList();
        if(prgStateList.getSelectionModel().getSelectedItem()==null){
            prgStateList.getSelectionModel().selectFirst();
        }
        setSymTableAndExeStack();
    }

    public void setNumberLabel(){
        nrPrgStates.setText("The number of PrgStates is: "+myController.getAll().size());
    }

    public void setHeapTable(){
        ObservableList<HashMap.Entry<Integer,IValue>> heapTableList= FXCollections.observableArrayList();
        heapTableAddress.setCellValueFactory(cellData->new ReadOnlyStringWrapper(Integer.toString(cellData.getValue().getKey())));
        heapTableValue.setCellValueFactory(cellData->new ReadOnlyStringWrapper(cellData.getValue().getValue().toString()));
        heapTableList.addAll(myController.getAll().get(0).getHeap().getContent().entrySet());
        heapTable.refresh();
        heapTable.setItems(heapTableList);
    }

    public void setOutList(){
        ObservableList<String> outObservableList=FXCollections.observableArrayList();
        //for(String e : myController.getRepository().getPrgList().get(0).getOut().getList()) {
        //            outObservableList.add(e);
        //        }
        outObservableList.addAll(myController.getAll().get(0).getOut().getAll());
        outList.setItems(outObservableList);
    }

    public void setFileTable(){
        ObservableList<String> fileTableList=FXCollections.observableArrayList();
        for(StringValue sv:myController.getAll().get(0).getFileTable().getContent().keySet()){
            fileTableList.add(sv.toString());
        }
        fileTable.refresh();
        fileTable.setItems(fileTableList);
    }

    public void setPrgStateList(){
        ObservableList<String> prgStateIDList=FXCollections.observableArrayList();
        for(PrgState prg:myController.getAll()){
            prgStateIDList.add(Integer.toString(prg.getID()));
        }
        prgStateList.refresh();
        prgStateList.setItems(prgStateIDList);
    }

    public void setSymTableAndExeStack(){
        ObservableList<HashMap.Entry<String,IValue>> symTableObsList=FXCollections.observableArrayList();
        ObservableList<String> exeStackObsList=FXCollections.observableArrayList();
        symTableVariable.setCellValueFactory(cellData->new ReadOnlyStringWrapper(cellData.getValue().getKey()));
        symTableValue.setCellValueFactory(cellData->new ReadOnlyStringWrapper(cellData.getValue().getValue().toString()));
        List<PrgState> allPrgs=myController.getAll();
        PrgState prgResult=null;
        for(PrgState prg:allPrgs){
            if(prg.getID()==Integer.parseInt(prgStateList.getSelectionModel().getSelectedItem())){
                prgResult=prg;
                break;
            }
        }
        if(prgResult!=null){
            symTableObsList.addAll(prgResult.getSymTable().getContent().entrySet());
            for(IStmt stmt: prgResult.getExeStack().getAll()){
                exeStackObsList.add(stmt.toString());
            }
            symTable.refresh();
            symTable.setItems(symTableObsList);
            exeStackList.refresh();
            exeStackList.setItems(exeStackObsList);

        }
    }
}
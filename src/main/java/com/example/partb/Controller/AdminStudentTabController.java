package com.example.partb.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminStudentTabController implements Initializable {
    @FXML
    private PasswordField password;
    @FXML
    private TextField USN;
    @FXML
    private TextField studentName;
    @FXML
    private TextField studentMail;
    @FXML
    private TextField branch;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private Button addStudentBtn;
    @FXML
    private TableView studentTable;
    @FXML
    private TableColumn USNcol;
    @FXML
    private TableColumn nameCol;
    @FXML
    private TableColumn genderCol;
    @FXML
    private TableColumn branchCol;
    @FXML
    private TableColumn emailCol;
    private ToggleGroup toggleGroup;
    private Alert alert = new Alert(Alert.AlertType.NONE);
    private void radioBtnSetup() {
        toggleGroup = new ToggleGroup();
        this.male.setSelected(true);
        male.setToggleGroup(toggleGroup);
        female.setToggleGroup(toggleGroup);
    }
    public void alertMsg(Alert.AlertType type, String msg){
        alert.setAlertType(type);
        if(type == Alert.AlertType.ERROR){
            alert.setTitle("Error!!!");
        }else if(type == Alert.AlertType.CONFIRMATION){
            alert.setTitle("Completed!!!");
        }else if(type == Alert.AlertType.INFORMATION){
            alert.setTitle("Alert!!!");
        }
        alert.setHeaderText(msg);
        alert.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        radioBtnSetup();
    }


    public void addStudent(ActionEvent event) {
        String usn = this.USN.getText().trim();
        String name= this.studentName.getText().trim();
        String mail = this.studentMail.getText().trim();
        String branch = this.branch.getText().trim();
        String password = this.password.getText();
        String gen = "male";
        Toggle gender = toggleGroup.getSelectedToggle();
        if(gender != null){
            if(gender == female){
                gen = "female";
            }
        }
        String msg = null;
        if(usn.length()==0){
            msg = "Enter USN";
        }else if(name.length()<5){
            msg = "Name should have more than 5 characters";
        }else if(mail.length()==0){
            msg = "Enter email";
        }else if(password.length()<5){
            msg = "password should have more than 5 characters";
        }else if(branch.length()==0){
            msg = "Enter branch";
        }
        if(msg!=null){
            alertMsg(Alert.AlertType.ERROR, msg);
        }
        System.out.println(String.format("%s %s %s %s %s %s",usn,name,mail,password,branch,gen));
    }

}

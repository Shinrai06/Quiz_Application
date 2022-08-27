package com.example.partb.Controller;

import com.example.partb.models.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.partb.alert;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AdminStudentTabController implements Initializable {
    @FXML private PasswordField password;
    @FXML private TextField USN;
    @FXML private TextField studentName;
    @FXML private TextField studentMail;
    @FXML private TextField branch;
    @FXML private RadioButton male;
    @FXML private RadioButton female;
    @FXML private Button addStudentBtn;
    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, String> USNcol;
    @FXML private TableColumn<Student, String> nameCol;
    @FXML private TableColumn<Student, String> genderCol;
    @FXML private TableColumn<Student, String> branchCol;
    @FXML private TableColumn<Student, String> emailCol;
    @FXML private TableColumn<Student, String> passwordCol;
    private ToggleGroup toggleGroup;
    private void radioBtnSetup() {
        toggleGroup = new ToggleGroup();
        this.male.setSelected(true);
        male.setToggleGroup(toggleGroup);
        female.setToggleGroup(toggleGroup);
    }
    private void resetForm(){
        this.USN.clear();
        this.studentMail.clear();;
        this.studentName.clear();
        this.password.clear();
        this.branch.clear();
    }
    private boolean valid(Student s){
        String msg = null;
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        if(s.getUSN().length()==0){
            msg = "Enter USN";
        }else if(s.getName().length()<5){
            msg = "Name should have more than 5 characters";
        }else if(!pattern.matcher(s.getEmail()).matches()){
            msg = "Enter valid email";
        }else if(s.getPassword().length()<5){
            msg = "password should have more than 5 characters";
        }else if(s.getBranch().length()==0){
            msg = "Enter branch";
        }
        if(msg!=null){
            alert.alertMsg(Alert.AlertType.WARNING, msg);
            return false;
        }
        return true;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        radioBtnSetup();
        renderTable();
    }

    public void addStudent(ActionEvent event) {
        String usn = this.USN.getText().trim();
        String name= this.studentName.getText().trim();
        String mail = this.studentMail.getText().trim();
        String branch = this.branch.getText().trim();
        String password = this.password.getText();
        char gen = 'M';
        Toggle gender = toggleGroup.getSelectedToggle();
        if(gender != null){
            if(gender == female){
                gen = 'F';
            }
        }
        Student s = new Student(usn,name,mail,branch,gen,password);
        if(valid(s)){
            System.out.println(s);
            if(s.ifExists("Email", mail) || s.ifExists("USN", usn)){
                alert.alertMsg(Alert.AlertType.WARNING, "Student already registered!!");
                return;
            }
            if(s.save() != null){
                alert.alertMsg(Alert.AlertType.CONFIRMATION, "Student details added..." );
                resetForm();

                studentTable.getItems().add(0,s);
                System.out.println("submitted!!");
            }
        }
    }
    public void renderTable(){
        ArrayList<Student> students = Student.getAll();
        studentTable.getItems().clear();

        this.USNcol.setCellValueFactory(new PropertyValueFactory<>("USN"));
        this.nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        this.genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        this.emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.branchCol.setCellValueFactory(new PropertyValueFactory<>("branch"));
        this.passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));

        studentTable.getItems().addAll(students);
    }

}

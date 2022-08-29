package com.example.partb.Controller;

import com.example.partb.Controller.Student.StudentHomeController;
import com.example.partb.Verification.Details;
import com.example.partb.alert;
import com.example.partb.exceptions.LoginException;
import com.example.partb.models.Student;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {

    @FXML
    private TextField adminEmail;
    @FXML
    private PasswordField adminPassword;
    @FXML
    private Button adminLoginBtn;
    @FXML
    private PasswordField studentPassword;
    @FXML
    private Button studentLoginBtn;
    @FXML
    private TextField studentEmail;

    public void reset(){
        adminEmail.setText("");
        adminPassword.setText("");
        studentEmail.setText("");
        studentPassword.setText("");
    }
    public void loginAdmin() throws Exception{
        String email = adminEmail.getText();
        String password = adminPassword.getText();
        if(email.trim().equalsIgnoreCase(Details.Email) && password.equalsIgnoreCase(Details.Password)){
            try{
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/partb/templates/AdminHome.fxml"));
                Stage stage = (Stage)adminPassword.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
            }catch (Exception e){
                e.printStackTrace();
                System.exit(0);
            }
            System.out.println("verified!!");
        }
        System.out.println(email+"---->"+password);
        reset();
    }

    public void loginStudent() throws IOException{
        String email = studentEmail.getText();
        String password = studentPassword.getText();
        Student s = new Student(email,password);
        try{
            s.login();
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/partb/templates/Student/studentHome.fxml"));
                Parent root = loader.load();
                StudentHomeController studentHomeController = loader.getController();
                studentHomeController.setStudent(s);
                Stage stage = (Stage)studentPassword.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                System.out.println("logged In!!!");
            }catch(Exception e){
                e.printStackTrace();
            }
            reset();
        }catch (Exception e){
            if(e instanceof LoginException){
                alert.alertMsg(Alert.AlertType.ERROR,"Invalid Credentials.....");
                System.out.println("Invalid Email...");
            }
            e.printStackTrace();
        }
        System.out.println(email+"---->"+password);
    }
}

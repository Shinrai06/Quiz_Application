package com.example.partb.Controller;

import com.example.partb.Verification.Details;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private Button studentLoginBtm;
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
                Stage stage = (Stage)studentPassword.getScene().getWindow();
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
        System.out.println(email+"---->"+password);
        reset();
    }
}

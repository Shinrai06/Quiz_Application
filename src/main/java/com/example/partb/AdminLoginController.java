package com.example.partb;

import com.example.partb.Verification.Details;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AdminLoginController {

    public TextField adminEmail;
    public PasswordField adminPassword;
    public Button adminLoginBtn;
    public PasswordField studentPassword;
    public Button studentLoginBtm;
    public TextField studentEmail;

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
                Parent root = FXMLLoader.load(getClass().getResource("templates/AdminHome.fxml"));
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

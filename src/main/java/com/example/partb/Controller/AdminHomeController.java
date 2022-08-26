package com.example.partb.Controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminHomeController implements Initializable {
    public TabPane adminTabPane;
    public Tab createQuiz;
    public Tab addStudent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            System.out.println("working...");
            Parent node = FXMLLoader.load(getClass().getResource("/com/example/partb/templates/CreateQuiz.fxml"));
            createQuiz.setContent(node);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

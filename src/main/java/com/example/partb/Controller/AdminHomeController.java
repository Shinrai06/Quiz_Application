package com.example.partb.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminHomeController implements Initializable {
    public Tab deletequiz;
    @FXML
    private TabPane adminTabPane;
    @FXML
    private Tab createQuiz;
    @FXML
    private Tab addStudent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            System.out.println("working...");
            Parent createQuizNode = FXMLLoader.load(getClass().getResource("/com/example/partb/templates/CreateQuiz.fxml"));
            createQuiz.setContent(createQuizNode);

            Parent studentNode = FXMLLoader.load(getClass().getResource("/com/example/partb/templates/adminStudentTab.fxml"));
            addStudent.setContent(studentNode);

            Parent deleteQuizNode = FXMLLoader.load(getClass().getResource("/com/example/partb/templates/DeleteQuiz.fxml"));
            deletequiz.setContent(deleteQuizNode);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

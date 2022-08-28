package com.example.partb.Controller.Student;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class studentHomeController implements Initializable {

    @FXML private Button backBtn;
    @FXML private StackPane stackPanel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addQuizListScreen();
    }
    private void addQuizListScreen(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/partb/templates/Student/quizList.fxml"));
            Node node = loader.load();
            stackPanel.getChildren().add(node);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

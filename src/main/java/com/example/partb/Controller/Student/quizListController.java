package com.example.partb.Controller.Student;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

public class quizListController implements Initializable {
    @FXML private FlowPane quizListContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            int i=10;
            while(i>0){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/partb/templates/Student/card.fxml"));
                Node node = loader.load();
                quizListContainer.getChildren().add(node);
                i -= 1;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

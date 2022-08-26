package com.example.partb;

import com.example.partb.models.Question;
import com.example.partb.models.Quiz;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        try{
            //Parent root = FXMLLoader.load(getClass().getResource("/"));
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/partb/templates/CreateQuiz.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            createTables();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void createTables(){
        Quiz.createTable();
        Question.createTable();
    }


    public static void main(String[] args){
        launch();
    }
}

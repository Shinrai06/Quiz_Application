package com.example.partb;

import com.example.partb.models.*;
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
            createTables();
            //Parent root = FXMLLoader.load(getClass().getResource("/com/example/partb/templates/Student/studentHome.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void createTables(){
        Quiz.createTable();
        Question.createTable();
        Student.createTable();
        QuizResult.createTable();
        QuizResultDetails.createTable();
    }


    public static void main(String[] args){
        launch();
    }
}

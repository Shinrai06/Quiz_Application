package com.example.partb.Controller;

import com.example.partb.alert;
import com.example.partb.models.Quiz;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class DeleteQuizController {
    public TextField quiztitle;
    public Button deleteQuizBtn;

    public void deleteQuiz(ActionEvent event) {
        String title = quiztitle.getText();
        if(Quiz.quizDelete(title)!=-1){
            alert.alertMsg(Alert.AlertType.CONFIRMATION,"Deleted Quiz!!!!");
        }
        else {
            alert.alertMsg(Alert.AlertType.ERROR,"Invalid Quiz Name");
        }
    }
}

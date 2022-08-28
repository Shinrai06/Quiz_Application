package com.example.partb.Controller.Student;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class QuizScreenController implements Initializable {
    @FXML private Label title;
    @FXML private Label time;
    @FXML private Label question;
    @FXML private RadioButton option1Btn;
    @FXML private ToggleGroup options;
    @FXML private RadioButton option2Btn;
    @FXML private RadioButton option3Btn;
    @FXML private RadioButton option4Btn;
    @FXML private Button nextQuestionBtn;
    @FXML private Button submitQuizBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void Submit(ActionEvent event) {
    }

    public void nextQuestion(ActionEvent event) {
    }
}

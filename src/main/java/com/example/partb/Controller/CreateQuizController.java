package com.example.partb.Controller;

import com.example.partb.models.Question;
import com.example.partb.models.Quiz;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import java.net.URL;
import java.util.*;

public class CreateQuizController implements Initializable {
    public TextField quizName;
    public TextArea question;
    public TextField option1;
    public TextField option2;
    public TextField option3;
    public TextField option4;
    public RadioButton option1RadioBtn;
    public RadioButton option2RadioBtn;
    public RadioButton option3RadioBtn;
    public RadioButton option4RadioBtn;
    public Button addNextQuestionBtn;
    public Button submitQuizBtn;
    public Button quizNameBtn;
    private Quiz quiz=null;
    private ArrayList<Question> questions = new ArrayList<>();
    private Alert alert = new Alert(AlertType.NONE);
    private ToggleGroup radioGroup;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            radioBtnSetup();
    }
    public void alertMsg(AlertType type, String msg){
        alert.setHeight(100);
        alert.setWidth(500);
        alert.setAlertType(type);
        if(type == AlertType.ERROR){
            alert.setTitle("Error!!!");
        }else if(type == AlertType.CONFIRMATION){
            alert.setTitle("Completed!!!");
        }else if(type == AlertType.INFORMATION){
            alert.setTitle("Alert!!!");
        }
        alert.setHeaderText(msg);
        alert.show();
    }
    private void radioBtnSetup(){
        radioGroup = new ToggleGroup();
        option1RadioBtn.setToggleGroup(radioGroup);
        option2RadioBtn.setToggleGroup(radioGroup);
        option3RadioBtn.setToggleGroup(radioGroup);
        option4RadioBtn.setToggleGroup(radioGroup);
    }
    private boolean validate(){
        if(quiz == null){
            alertMsg(AlertType.INFORMATION, "Please Enter Quiz title..");
            return false;
        }
        String Q = this.question.getText();
        String op1 = this.option1.getText();
        String op2 = this.option2.getText();
        String op3 = this.option3.getText();
        String op4 = this.option4.getText();
        Toggle selectedRadio = radioGroup.getSelectedToggle();
        //System.out.println(selectedRadio);
        if(Q.trim().isEmpty() || op1.trim().isEmpty() || op2.trim().isEmpty() || op3.trim().isEmpty() || op4.trim().isEmpty()){
            alertMsg(AlertType.INFORMATION,"Enter the question and all the 4 options..");
        }else{
            if(selectedRadio == null){
                alertMsg(AlertType.INFORMATION,"Select answer...");
            }
            else{
                return true;
            }
        }
        return false;
    }
    public void setQuizName(ActionEvent event) {
            String title = quizName.getText();
            if(title.trim().isEmpty()){
                alertMsg(AlertType.INFORMATION, "Invalid Quiz title...");
                //System.out.println("Invalid Quiz title..");
            }else {
                quizName.setEditable(false);
                //System.out.println("Valid Quiz title...");
                this.quiz = new Quiz(title);
            }
    }

    private boolean saveQuestion(){
        Question question = new Question();
        boolean valid = validate();
        if(valid){
            question.setOption1(option1.getText().trim());
            question.setOption2(option2.getText().trim());
            question.setOption3(option3.getText().trim());
            question.setOption4(option4.getText().trim());
            String ans;
            Toggle selected = radioGroup.getSelectedToggle();
            if(selected == option1RadioBtn){
                ans = option1.getText().trim();
            }else  if(selected == option2RadioBtn){
                ans = option2.getText().trim();
            }else  if(selected == option3RadioBtn){
                ans = option3.getText().trim();
            }else  {
                ans = option3.getText().trim();
            }
            question.setAns(ans);
            question.setQuestion(this.question.getText().trim());
            this.question.clear();
            option1.clear();
            option2.clear();
            option3.clear();
            option4.clear();
            questions.add(question);
            question.setQuiz(quiz);
            System.out.println(questions);
            System.out.println(quiz);
        }
        return valid;
    }
    public void submitQuiz(ActionEvent event) {
        boolean flag = saveQuestion();
        if(flag){
            flag = quiz.save(questions);
            if(flag){
                alertMsg(AlertType.CONFIRMATION, "Created Quiz!!");
            }else{
                alertMsg(AlertType.ERROR,"Can't Save quiz");
            }
        }
    }

    public void addNextQuestion(ActionEvent event) {
        saveQuestion();
    }
}

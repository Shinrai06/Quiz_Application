package com.example.partb.Controller.Student;

import com.example.partb.alert;
import com.example.partb.models.Question;
import com.example.partb.models.Quiz;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.*;

public class QuestionScreenController implements Initializable {
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

    private Quiz quiz;
    private List<Question> questionList;
    private Question currentQuestion;
    int currentIndex = 0;
    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
        this.title.setText(this.quiz.getTitle());
        this.getData();
    }
    private void getData(){
        if(quiz!=null){
            this.questionList = quiz.getQuestionsWithQuizID();
            Collections.shuffle(this.questionList);
            this.setNextQuestion();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hideSubmitBtn();
        showNextQuestionBtn();
    }
    private void setNextQuestion(){
        if(currentIndex < questionList.size()) {
            this.currentQuestion = this.questionList.get(currentIndex);
            List<String> options = new ArrayList<>();
            options.add(this.currentQuestion.getOption1());
            options.add(this.currentQuestion.getOption2());
            options.add(this.currentQuestion.getOption3());
            options.add(this.currentQuestion.getOption4());
            Collections.shuffle(options);
            this.question.setText(this.currentQuestion.getQuestion());
            this.option1Btn.setText(options.get(0));
            this.option2Btn.setText(options.get(1));
            this.option3Btn.setText(options.get(2));
            this.option4Btn.setText(options.get(3));
            currentIndex++;
        }else{
            hideNextQuestionBtn();
            showSubmitBtn();
            //alert.alertMsg(Alert.AlertType.INFORMATION, "There are no more questions!!!");
        }
    }
    private void hideNextQuestionBtn(){
        this.nextQuestionBtn.setVisible(false);
    }
    private void showNextQuestionBtn(){
        this.nextQuestionBtn.setVisible(true);
    }
    private void hideSubmitBtn(){
        this.submitQuizBtn.setVisible(false);
    }
    private void showSubmitBtn(){
        this.submitQuizBtn.setVisible(true);
    }
    public void Submit(ActionEvent event) {
    }
    public void nextQuestion(ActionEvent event) {
        this.setNextQuestion();
    }
}

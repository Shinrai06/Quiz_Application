package com.example.partb.Controller.Student;

import com.example.partb.alert;
import com.example.partb.models.Question;
import com.example.partb.models.Quiz;
import javafx.beans.Observable;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.*;

public class QuestionScreenController implements Initializable {
    @FXML private FlowPane progressPane;
    private static class QuestionObservable{
        Property<String> question = new SimpleStringProperty();
        Property<String> option1 = new SimpleStringProperty();
        Property<String> option2 = new SimpleStringProperty();
        Property<String> option3 = new SimpleStringProperty();
        Property<String> option4 = new SimpleStringProperty();
        Property<String> answer = new SimpleStringProperty();

        public void setQuestion(Question question){
            this.question.setValue(question.getQuestion());
            this.option1.setValue(question.getOption1());
            this.option2.setValue(question.getOption2());
            this.option3.setValue(question.getOption3());
            this.option4.setValue(question.getOption4());
            this.answer.setValue(question.getAns());
        }
    }
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
    private QuestionObservable questionObservable;
    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
        this.title.setText(this.quiz.getTitle());
        this.getData();
    }
    private void getData(){
        if(quiz!=null){
            this.questionList = quiz.getQuestionsWithQuizID();
            Collections.shuffle(this.questionList);
            renderProgress();
            this.setNextQuestion();
        }
    }
    private void renderProgress(){
        for(int i=0;i<this.questionList.size();i++){
            FXMLLoader loader = new FXMLLoader((getClass().getResource("/com/example/partb/templates/Student/circle.fxml")));
            try{
                Node node = loader.load();
                CircleController circleController = loader.getController();
                circleController.setNumber(i+1);
                circleController.setDefaultColor();
                progressPane.getChildren().add(node);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.hideSubmitBtn();
        this.showNextQuestionBtn();
        this.questionObservable = new QuestionObservable();
        bindFields();
    }
    private void bindFields(){
        this.question.textProperty().bind(this.questionObservable.question);
        this.option1Btn.textProperty().bind(this.questionObservable.option1);
        this.option2Btn.textProperty().bind(this.questionObservable.option2);
        this.option3Btn.textProperty().bind(this.questionObservable.option3);
        this.option4Btn.textProperty().bind(this.questionObservable.option4);
    }
    private void setNextQuestion(){
        if(currentIndex < questionList.size()) {
            {
                Node circleNode = this.progressPane.getChildren().get(currentIndex);
                CircleController controller = (CircleController) circleNode.getUserData();
                controller.setCurrentColor();
            }
            this.currentQuestion = this.questionList.get(currentIndex);
            List<String> options = new ArrayList<>();
            options.add(this.currentQuestion.getOption1());
            options.add(this.currentQuestion.getOption2());
            options.add(this.currentQuestion.getOption3());
            options.add(this.currentQuestion.getOption4());
            Collections.shuffle(options);
            this.currentQuestion.setOption1(options.get(0));
            this.currentQuestion.setOption2(options.get(1));
            this.currentQuestion.setOption3(options.get(2));
            this.currentQuestion.setOption4(options.get(3));
            this.questionObservable.setQuestion(this.currentQuestion);
            currentIndex++;
        }else{
            this.hideNextQuestionBtn();
            this.showSubmitBtn();
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
        boolean isRight=false;
        {
            RadioButton selectedBtn = (RadioButton) options.getSelectedToggle();
            String userAns = selectedBtn.getText();
            String correctAns = this.currentQuestion.getAns();
            if(userAns.trim().equalsIgnoreCase(correctAns.trim()))
                isRight=true;
        }
        Node circleNode = this.progressPane.getChildren().get(currentIndex-1);
        CircleController controller = (CircleController) circleNode.getUserData();
        if(isRight){
            controller.setRightColor();
        }else{
            controller.setWrongColor();
        }
        this.setNextQuestion();
    }
}

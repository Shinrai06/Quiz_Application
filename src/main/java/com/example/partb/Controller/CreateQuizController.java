package com.example.partb.Controller;

import com.example.partb.alert;
import com.example.partb.models.Question;
import com.example.partb.models.Quiz;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import java.net.URL;
import java.util.*;

public class CreateQuizController implements Initializable {
    @FXML private TreeView<String> treeView;
    @FXML private TextField quizName;
    @FXML private TextArea question;
    @FXML private TextField option1;
    @FXML private TextField option2;
    @FXML private TextField option3;
    @FXML private TextField option4;
    @FXML private RadioButton option1RadioBtn;
    @FXML private RadioButton option2RadioBtn;
    @FXML private RadioButton option3RadioBtn;
    @FXML private RadioButton option4RadioBtn;
    @FXML private Button addNextQuestionBtn;
    @FXML private Button submitQuizBtn;
    @FXML private Button quizNameBtn;
    private Quiz quiz=null;
    private ArrayList<Question> questions = new ArrayList<>();
    private ToggleGroup radioGroup;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            radioBtnSetup();
            renderTreeView();
    }
    private void radioBtnSetup(){
        radioGroup = new ToggleGroup();
        option1RadioBtn.setToggleGroup(radioGroup);
        option2RadioBtn.setToggleGroup(radioGroup);
        option3RadioBtn.setToggleGroup(radioGroup);
        option4RadioBtn.setToggleGroup(radioGroup);
    }
    private void renderTreeView(){
        Map<Quiz, List<Question>> data = Quiz.getAll();
        Set<Quiz> quizes = data.keySet();
        TreeItem<String> root = new TreeItem<String>("List of Quizes");
        for(Quiz q: quizes){
            TreeItem<String> quizTreeItm = new TreeItem<String>(q.getTitle());
            List<Question> questions = data.get(q);
            for(Question question: questions){
                TreeItem<String> questionTreeItm = new TreeItem<String>(question.getQuestion());
                questionTreeItm.getChildren().add(new TreeItem<String>("A: "+question.getOption1()));
                questionTreeItm.getChildren().add(new TreeItem<String>("B: "+question.getOption2()));
                questionTreeItm.getChildren().add(new TreeItem<String>("C: "+question.getOption3()));
                questionTreeItm.getChildren().add(new TreeItem<String>("D: "+question.getOption4()));
                questionTreeItm.getChildren().add(new TreeItem<String>("ANS: "+question.getAns()));
                quizTreeItm.getChildren().add(questionTreeItm);
            }
            root.getChildren().add(quizTreeItm);
        }
        root.setExpanded(true);
        this.treeView.setRoot(root);
    }
    private boolean validate(){
        if(quiz == null){
            alert.alertMsg(AlertType.INFORMATION, "Please Enter Quiz title..");
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
            alert.alertMsg(AlertType.INFORMATION,"Enter the question and all the 4 options..");
        }else{
            if(selectedRadio == null){
                alert.alertMsg(AlertType.INFORMATION,"Select answer...");
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
                alert.alertMsg(AlertType.INFORMATION, "Invalid Quiz title...");
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
                alert.alertMsg(AlertType.CONFIRMATION, "Created Quiz!!");
            }else{
                alert.alertMsg(AlertType.ERROR,"Can't Save quiz");
            }
        }
    }
    public void addNextQuestion(ActionEvent event) {
        saveQuestion();
    }
}

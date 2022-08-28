package com.example.partb.Controller.Student;

import com.example.partb.listeners.NewScreenListener;
import com.example.partb.models.Quiz;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

public class QuizListController implements Initializable {
    @FXML private FlowPane quizListContainer;
    Map<Quiz, Integer> quizes = null;
    private NewScreenListener screenListener;
    Set<Quiz> keys;
    public void setScreenListener(NewScreenListener screenListener) {
        this.screenListener = screenListener;
        setCards();
    }
    private void setCards(){
        for(Quiz quiz: keys){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/partb/templates/Student/card.fxml"));
            try{
                Node node = loader.load();
                CardController card = loader.getController();
                card.setQuiz(quiz);
                card.setNoq(quizes.get(quiz)+"");
                card.setScreenListener(this.screenListener);
                quizListContainer.getChildren().add(node);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        quizes = Quiz.getAllWithQuestionCount();
        keys = quizes.keySet();
    }
}

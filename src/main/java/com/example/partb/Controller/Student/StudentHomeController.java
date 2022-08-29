package com.example.partb.Controller.Student;

import com.example.partb.listeners.NewScreenListener;
import com.example.partb.models.Student;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentHomeController implements Initializable {

    @FXML private Button backBtn;
    @FXML private StackPane stackPanel;

    private Student student;

    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
        addQuizListScreen();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    private void addScreenTOStackPane(Node node){
        this.stackPanel.getChildren().add(node);
    }
    private void addQuizListScreen(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/partb/templates/Student/quizList.fxml"));
            Node node = loader.load();
            QuizListController quizListController = loader.getController();
            quizListController.setStudent(this.student);
            quizListController.setScreenListener(new NewScreenListener() {
                @Override
                public void changeScreen(Node node) {
                    addScreenTOStackPane(node);
                }
                @Override
                public void handle(Event event) {

                }
            });
            quizListController.setCards();
            stackPanel.getChildren().add(node);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void back(ActionEvent event) {
        ObservableList<Node> nodes = this.stackPanel.getChildren();
        if(nodes.size()==1)
            return;
        this.stackPanel.getChildren().remove(this.stackPanel.getChildren().size()-1);
    }
}

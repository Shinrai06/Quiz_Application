package com.example.partb.Controller.Student;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class CircleController implements Initializable {
    @FXML private Circle circle;
    @FXML private Label number;
    public void setDefaultColor(){
        circle.setFill(Color.web("#DAE0E2"));
        number.setTextFill((Color.valueOf("black")));
    }
    public void setCurrentColor(){
        circle.setFill(Color.web("#0ABDE3"));
        number.setTextFill((Color.valueOf("white")));
    }
    public void setWrongColor(){
        circle.setFill(Color.web("#EC4849"));
        number.setTextFill((Color.valueOf("white")));
    }
    public void setRightColor(){
        circle.setFill(Color.web("#AFFC41"));
        number.setTextFill((Color.valueOf("black")));
    }
    public void setNumber(Integer number) {
        this.number.setText(number.toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

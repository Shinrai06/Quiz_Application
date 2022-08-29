package com.example.partb.Controller.Student;

import com.example.partb.models.Question;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class SingleQuestionController implements Initializable {
    @FXML private Label question;
    @FXML private Label option1;
    @FXML private Label option2;
    @FXML private Label option3;
    @FXML private Label option4;

    private Question questionObj;
    private String userAns;

    public void setValues(Question questionObj, String userAns) {
        this.questionObj = questionObj;
        if(userAns==null)
            userAns = "";
        else
            this.userAns = userAns;

        setTexts();
    }

    private void setTexts(){
        this.question.setText(this.questionObj.getQuestion());
        this.option1.setText(this.questionObj.getOption1());
        this.option2.setText(this.questionObj.getOption2());
        this.option3.setText(this.questionObj.getOption3());
        this.option4.setText(this.questionObj.getOption4());

        if(option1.getText().trim().equalsIgnoreCase(this.questionObj.getAns())){
            option1.setTextFill(Color.web("#26AE60"));
        }
        else if(option2.getText().trim().equalsIgnoreCase(this.questionObj.getAns())){
            option2.setTextFill(Color.web("#26AE60"));
        }
        else if(option3.getText().trim().equalsIgnoreCase(this.questionObj.getAns())){
            option3.setTextFill(Color.web("#26AE60"));
        }
        else if(option4.getText().trim().equalsIgnoreCase(this.questionObj.getAns())){
            option4.setTextFill(Color.web("#26AE60"));
        }
        if(!(this.userAns.trim().equalsIgnoreCase(this.questionObj.getAns()))){
            if(option1.getText().trim().equalsIgnoreCase(this.questionObj.getAns())){
                option1.setTextFill(Color.web("#B83227"));
            }
            else if(option2.getText().trim().equalsIgnoreCase(this.questionObj.getAns())){
                option2.setTextFill(Color.web("#B83227"));
            }
            else if(option3.getText().trim().equalsIgnoreCase(this.questionObj.getAns())){
                option3.setTextFill(Color.web("#B83227"));
            }
            else if(option4.getText().trim().equalsIgnoreCase(this.questionObj.getAns())){
                option4.setTextFill(Color.web("#B83227"));
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

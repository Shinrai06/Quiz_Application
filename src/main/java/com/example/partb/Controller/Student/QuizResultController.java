package com.example.partb.Controller.Student;

import com.example.partb.models.Question;
import com.example.partb.models.Quiz;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class QuizResultController implements Initializable {
    @FXML private PieChart attemptedQuestions;
    @FXML private PieChart answers;
    @FXML VBox questionContainer;

    private Map<Question, String> userAns;
    private Integer noOfRightAns;
    private Quiz quiz;
    private List<Question> questionList;
    private Integer notAttempted=0;
    private Integer attempted = 0;
    public void setValues(Map<Question, String> userAns, Integer noOfRightAns, Quiz quiz, List<Question> questionList) {
        this.userAns = userAns;
        this.noOfRightAns = noOfRightAns;
        this.quiz = quiz;
        this.questionList = questionList;
        this.attempted = this.userAns.keySet().size();
        this.notAttempted = this.questionList.size() - this.attempted;

        setValuesToChart();
        renderQuestions();
    }
    private void renderQuestions(){
        for(int i=0;i<this.questionList.size();i++){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/partb/templates/Student/singleQuestion.fxml"));
            try{
                Node node = loader.load();
                SingleQuestionController controller = loader.getController();
                controller.setValues(this.questionList.get(i), this.userAns.get(this.questionList.get(i)));
                questionContainer.getChildren().add(node);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    private void setValuesToChart(){
        ObservableList<PieChart.Data> attemptedData = this.attemptedQuestions.getData();
        attemptedData.add(new PieChart.Data(String.format("Attempted (%d)",this.attempted), this.attempted));
        attemptedData.add(new PieChart.Data(String.format("Not Attempted (%d)",this.notAttempted), this.notAttempted));

        ObservableList<PieChart.Data> scoreChartData = this.answers.getData();
        scoreChartData.add(new PieChart.Data(String.format("Right Answers (%d)",this.noOfRightAns), this.noOfRightAns));
        scoreChartData.add(new PieChart.Data(String.format("Wrong Answers (%d)",this.attempted-this.noOfRightAns), this.attempted-this.noOfRightAns));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

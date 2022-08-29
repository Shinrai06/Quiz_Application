package com.example.partb.models;


import com.example.partb.alert;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.Date;
import java.util.Map;

public class QuizResult{
    private Integer id;
    private Quiz quiz;
    private Student student;
    private Integer rightAnswer;
    private Timestamp timestamp;
    {   //initializer block
        this.timestamp = new Timestamp(new Date().getTime());
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    public void setRightAnswer(Integer rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    public static class metaData{
        public static String tableName = "quiz_results";
        public static String Id = "quiz_results_id";
        public static String studentId = "student_USN";
        public static String rightAns = "right_answers";
        public static String dateTime = "date_time";
        public static String quizId = "quiz_id";
    }
    public QuizResult(Integer id, Quiz quiz, Student student, Integer rightAnswer) {
        this.id = id;
        this.quiz = quiz;
        this.student = student;
        this.rightAnswer = rightAnswer;
    }
    public QuizResult(Quiz quiz, Student student, Integer rightAnswer) {
        this.quiz = quiz;
        this.student = student;
        this.rightAnswer = rightAnswer;
    }

    public Integer getId() {
        return id;
    }
    public Quiz getQuiz() {
        return quiz;
    }
    public Student getStudent() {
        return student;
    }
    public Integer getRightAnswer() {
        return rightAnswer;
    }
    public Timestamp getTimestamp() {
        return timestamp;
    }


    public static void createTable(){
        String raw = "CREATE TABLE IF NOT EXISTS %s( %s Integer NOT null PRIMARY KEY AUTOINCREMENT, %s INT NOT null," +
                " %s VARCHAR(20) NOT null, %s INT NOT null, %s TIMESTAMP NOT null, " +
                "FOREIGN KEY (%s) REFERENCES %s(%s), " +
                "FOREIGN KEY (%s) REFERENCES %s(%s));";
        String query = String.format(raw,
                metaData.tableName,
                metaData.Id,
                metaData.quizId,
                metaData.studentId,
                metaData.rightAns,
                metaData.dateTime,
                metaData.quizId,
                Quiz.metaData.TABLE_NAME,
                Quiz.metaData.QUIZ_ID,
                metaData.studentId,
                Student.metaData.tableName,
                Student.metaData.USN);
        Connection c = null;
        System.out.println(query);
        String url = "jdbc:sqlite:quiz.db";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(url);
            PreparedStatement ps = c.prepareStatement(query);
            boolean flag = ps.execute();
            System.out.println(flag);
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
    public boolean saveQuizResult(Map<Question, String> userAns){
        String raw = "INSERT INTO %s (%s, %s, %s, %s) values"
                +"(?,?,?, CURRENT_TIMESTAMP);";
        String query = String.format(raw,
                metaData.tableName,
                metaData.studentId,
                metaData.quizId,
                metaData.rightAns,
                metaData.dateTime);
        Connection c = null;
        System.out.println(query);
        String url = "jdbc:sqlite:quiz.db";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(url);
            PreparedStatement ps = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,this.getStudent().getUSN());
            ps.setInt(2,this.getQuiz().getQuizId());
            ps.setInt(3,this.getRightAnswer());
            int res = ps.executeUpdate();
            if(res>0){
                ResultSet keys = ps.getGeneratedKeys();
                if(keys.next()){
                    this.setId(keys.getInt(1));
                    System.out.println(this);
                    return saveQuizResultDetails(userAns);
                }
            }
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            e.printStackTrace();
        }
        System.out.println("Opened database successfully");
        return false;
    }
    private boolean saveQuizResultDetails(Map<Question, String> userAns){
        return QuizResultDetails.saveQuizDetails(this, userAns);
    }
}

package com.example.partb.models;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Date;

public class QuizResult{
    private Integer id;
    private Quiz quiz;
    private Student student;
    private Integer rightAnswer;
    private Timestamp timestamp;
    {   //initializer block
        this.timestamp = new Timestamp(new Date().getTime());
    }
    public static class metaData{
        public static String tableName = "quiz_results";
        public static String Id = "quiz_results_id";
        public static String studentId = "student_id";
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
        String raw = "CREATE TABLE IF NOT EXISTS %s( %s INT NOT null PRIMARY KEY, %s INT NOT null," +
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
}

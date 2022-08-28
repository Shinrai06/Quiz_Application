package com.example.partb.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class QuizResultDetails {
    private Integer id;
    private String userAns;
    private Question question;
    private QuizResult quizResult;
    public static class metaData{
        public static String tableName = "quiz_result_details";
        public static String Id = "id";
        public static String quizResultId = "quiz_result_id";
        public static String userAns = "user_answer";
        public static String questionId = "question_id";
    }

    public static void createTable(){
        String raw = "CREATE TABLE IF NOT EXISTS %s( %s INT NOT null PRIMARY KEY, " +
                "%s INT NOT null, %s INT NOT null, %s VARCHAR(200) NOT null, " +
                "FOREIGN KEY (%s) REFERENCES %s(%s), " +
                "FOREIGN KEY (%s) REFERENCES %s(%s));";
        String query = String.format(raw,
                metaData.tableName,
                metaData.Id,
                metaData.quizResultId,
                metaData.questionId,
                metaData.userAns,
                metaData.quizResultId,
                QuizResult.metaData.tableName,
                QuizResult.metaData.Id,
                metaData.questionId,
                Question.metaData.TABLE_NAME,
                Question.metaData.QUESTION_ID);
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

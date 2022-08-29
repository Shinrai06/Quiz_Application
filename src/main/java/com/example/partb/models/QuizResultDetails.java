package com.example.partb.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Map;
import java.util.Set;

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
        String raw = "CREATE TABLE IF NOT EXISTS %s( %s INTEGER NOT null PRIMARY KEY AUTOINCREMENT, " +
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
        String url = "jdbc:sqlite:quiz.db";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(url);
            PreparedStatement ps = c.prepareStatement(query);
            boolean flag = ps.execute();
            System.out.println(flag);
            c.close();
            System.out.println(query);
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    public static boolean saveQuizDetails(QuizResult quizResult, Map<Question, String> userAns){
        String raw = "INSERT INTO %s (%s,%s,%s) VALUES (" +
                " ?, ?, ?);";
        String query = String.format(raw,
                metaData.tableName,
                metaData.quizResultId,
                metaData.questionId,
                metaData.userAns);
        Connection c = null;
        String url = "jdbc:sqlite:quiz.db";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(url);
            PreparedStatement ps = c.prepareStatement(query);
            Set<Question> questions = userAns.keySet();
            for(Question question: questions){
                ps.setInt(1,quizResult.getId());
                ps.setInt(2,question.getQuestionId());
                ps.setString(3,userAns.get(question));
                ps.addBatch();
            }
            int[] res = ps.executeBatch();
            if(res.length>0){
                return true;
            }
            c.close();
            System.out.println(query);
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            e.printStackTrace();
        }
        System.out.println("Opened database successfully");
        return false;
    }
}

package com.example.partb.models;

import java.sql.*;


public class Question {
    private Quiz quiz;

    public Question() {
    }

    private Integer questionId;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String ans;

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public static class metaData{
        public static final String TABLE_NAME = "questions";
        public static final String QUESTION = "question";
        public static final String QUESTION_ID = "id";
        public static final String OPTION1 = "option1";
        public static final String OPTION2 = "option2";
        public static final String OPTION3 = "option3";
        public static final String OPTION4 = "option4";
        public static final String ANSWER = "answer";
        public static final String QUIZ_ID = "quiz_id";
    }
    public Question(Quiz quiz, String question, String option1, String option2, String option3, String option4, String ans) {
        this.quiz = quiz;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.ans = ans;
    }
    public static void createTable(){
        String raw = "CREATE TABLE if NOT EXISTS %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s VARCHAR(10000), " +
                "%s VARCHAR(500)," +
                "%s VARCHAR(500)," +
                "%s VARCHAR(500)," +
                "%s VARCHAR(500)," +
                "%s VARCHAR(500)," +
                "%s INTEGER," +
                "FOREIGN KEY (%s) REFERENCES %s(%s));";
        String query = String.format(raw,metaData.TABLE_NAME,metaData.QUESTION_ID,metaData.QUESTION,metaData.OPTION1,metaData.OPTION2,metaData.OPTION3,metaData.OPTION4,metaData.ANSWER,metaData.QUIZ_ID,metaData.QUIZ_ID,Quiz.metaData.TABLE_NAME,Quiz.metaData.QUIZ_ID);
        Connection c = null;
        String url = "jdbc:sqlite:quiz.db";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(url);
            PreparedStatement ps = c.prepareStatement(query);
            boolean flag = ps.execute();
            System.out.println(query);
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    public boolean save(){
        String raw = "INSERT INTO %s (%s,%s,%s,%s,%s,%s,%s) VALUES (?,?,?,?,?,?,?)";
        String query = String.format(raw , metaData.TABLE_NAME, metaData.QUESTION, metaData.OPTION1, metaData.OPTION2, metaData.OPTION3, metaData.OPTION4, metaData.ANSWER, metaData.QUIZ_ID);
        System.out.println(query);
        String url = "jdbc:sqlite:quiz.db";
        try{
            Class.forName("org.sqlite.JDBC");
            try(Connection c = DriverManager.getConnection(url)){
                PreparedStatement ps = c.prepareStatement(query);
                ps.setString(1,this.question);
                ps.setString(2,this.option1);
                ps.setString(3,this.option2);
                ps.setString(4,this.option3);
                ps.setString(5,this.option4);
                ps.setString(6,this.ans);
                ps.setInt(7,this.quiz.getQuizId());
                int i = ps.executeUpdate();
                System.out.println("updated row: "+ i);
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}

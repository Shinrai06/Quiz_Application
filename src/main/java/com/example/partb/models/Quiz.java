package com.example.partb.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Quiz {
    private String title;
    private Integer quizId;

    public Integer getQuizId() {
        return quizId;
    }

    public Quiz(){};
    public Quiz(String title) {
        this.title = title;
    }
    @Override
    public String toString() {
        return "Quiz{" +
                "title='" + title + '\'' +
                ", quizId=" + quizId +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    public static class metaData{
        public static final String TABLE_NAME = "quiz";
        public static final String TITLE = "title";
        public static final String QUIZ_ID = "quiz_id";
    }
    public static void createTable(){
        String raw = "CREATE TABLE if NOT EXISTS %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s VARCHAR(50));";
        String query = String.format(raw, metaData.TABLE_NAME, metaData.QUIZ_ID, metaData.TITLE);
        Connection c = null;
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

    public int save(){
        String raw = "Insert into %s (%s) values (?)";
        String query = String.format(raw , metaData.TABLE_NAME, metaData.TITLE);
        String url = "jdbc:sqlite:quiz.db";
        try{
            Class.forName("org.sqlite.JDBC");
            try(Connection c = DriverManager.getConnection(url)){
                PreparedStatement ps = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,this.title);
                int i = ps.executeUpdate();
                ResultSet keys = ps.getGeneratedKeys();
                if(keys.next()){
                    return keys.getInt(1);
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return -1;
        }
        return -1;
    }
    public boolean save(ArrayList<Question> questions){
        boolean flag = true;
        this.quizId = this.save();
        //System.out.println(questions.get(0).getQuiz() == this);
        for(Question q: questions){
            flag = flag && q.save();
            System.out.println(flag);
        }
        return flag;
    }

    public static Map<Quiz, List<Question>> getAll(){
        Map<Quiz, List<Question>> quizes = new HashMap<>();
        Quiz key = null;
        List<Question> questions = new ArrayList<>();
        String raw = "SELECT %s.%s, %s, %s, %s, %s, %s, %s, %s, %s FROM %s JOIN %s ON  %s.%s = %s.%s";
        String query = String.format(raw , metaData.TABLE_NAME, metaData.QUIZ_ID, metaData.TITLE, Question.metaData.QUESTION_ID,
                Question.metaData.QUESTION,Question.metaData.OPTION1,Question.metaData.OPTION2,
                Question.metaData.OPTION3,Question.metaData.OPTION4,Question.metaData.ANSWER, metaData.TABLE_NAME,Question.metaData.TABLE_NAME,
                Question.metaData.TABLE_NAME, Question.metaData.QUIZ_ID, metaData.TABLE_NAME,metaData.QUIZ_ID);
        System.out.println(query);
        String url = "jdbc:sqlite:quiz.db";
        try{
            Class.forName("org.sqlite.JDBC");
            try(Connection c = DriverManager.getConnection(url)) {
                PreparedStatement ps = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ResultSet res = ps.executeQuery();
                while(res.next()){
                    Quiz q = new Quiz();
                    q.setQuizId(res.getInt(1));
                    q.setTitle(res.getString(2));

                    Question qu = new Question();
                    qu.setQuestionId(res.getInt(3));
                    qu.setQuestion(res.getString(4));
                    qu.setOption1(res.getString(5));
                    qu.setOption2(res.getString(6));
                    qu.setOption3(res.getString(7));
                    qu.setOption4(res.getString(8));
                    qu.setAns(res.getString(9));
                    if(key!=null && key.equals(q)){
                        quizes.get(key).add(qu);
                    }else{
                        ArrayList<Question> val = new ArrayList<>();
                        val.add(qu);
                        quizes.put(q, val);
                    }
                    key = q;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return quizes;
    }
}

package com.example.partb;

import com.example.partb.exceptions.LoginException;
import com.example.partb.models.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {

    static long min, sec, hr, totSec = 0;
    private static String format(long val){
        if(val<10){
            return 0+""+val;
        }
        return val+"";
    }
    public static void convertTime(){
        min = TimeUnit.SECONDS.toMinutes(totSec);
        sec = totSec-(min*60);
        hr = TimeUnit.MINUTES.toHours(min);
        min = min-(hr*60);
        System.out.println(format(hr)+":"+format(min)+":"+format(sec));
        totSec--;
    }
    public static void main(String[] args) {
        totSec = 6;
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("After 1 sec..");
                convertTime();
                if(totSec<=0){
                    System.exit(0);
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 0,1000);
        //QuizResultDetails.createTable();
        //Quiz quiz = new Quiz();
        //quiz.setQuizId(2);
        //System.out.println(quiz.getQuestionsWithQuizID());
       //System.out.println(Student.getAll());
        //System.out.println(Student.getAll().size());
        /*Quiz quiz = new Quiz("MySql Quiz");
        Question question = new Question(quiz,"1+3=?","4","5","7","6","4");
        //question.save();

        ArrayList<Question> arr =new ArrayList<>();
        arr.add(question);
        arr.add(question);arr.add(question);arr.add(question);arr.add(question);arr.add(question);
        question.setQuiz(quiz);
        quiz.save(arr);
        */
        //Student.createTable();
        //Student st = new Student("25","das","@maiol","brannch",'m',"pass");
        //System.out.println(st.save());
        //boolean s = new Student("25","das2","@maiol2","brannch",'f',"password").ifExists("Email","@mal2");
        //System.out.println(s);

        // delete table
        /*
        String raw = "DROP TABLE %s;";
        String query = String.format(raw, QuizResultDetails.metaData.tableName);
        Connection c = null;
        System.out.println(query);
        String url = "jdbc:sqlite:quiz.db";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(url);
            PreparedStatement ps = c.prepareStatement(query);
            boolean flag = ps.execute();
            System.out.println(flag);
            System.out.println("deleted table");
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        */
    }
}

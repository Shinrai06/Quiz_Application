package com.example.partb;

import com.example.partb.exceptions.LoginException;
import com.example.partb.models.Question;
import com.example.partb.models.Quiz;
import com.example.partb.models.Student;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    public static void main(String[] args) {
        //System.out.println(Quiz.getAll());
        Student s = new Student("shivareddyc.cs20@gmail.com", "12345678");
        try{
            s.login();
        }catch(Exception e){
            if(e instanceof LoginException){
                System.out.println("Invalid Email...");
            }
            e.printStackTrace();
        }
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
    }
}

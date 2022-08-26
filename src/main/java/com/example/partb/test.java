package com.example.partb;

import com.example.partb.models.Question;
import com.example.partb.models.Quiz;

import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        Quiz quiz = new Quiz("MySql Quiz");
        Question question = new Question(quiz,"1+3=?","4","5","7","6","4");
        //question.save();

        ArrayList<Question> arr =new ArrayList<>();
        arr.add(question);
        arr.add(question);arr.add(question);arr.add(question);arr.add(question);arr.add(question);
        question.setQuiz(quiz);
        quiz.save(arr);
    }
}

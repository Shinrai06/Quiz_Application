package com.example.partb;

import com.example.partb.models.Question;
import com.example.partb.models.Quiz;

public class test {
    public static void main(String[] args) {
        Quiz quiz = new Quiz("javaFX Quiz");
        quiz.setQuizId(quiz.save());
        Question question = new Question(quiz,"1+3=?","4","5","7","6","4");
        question.save();
    }
}

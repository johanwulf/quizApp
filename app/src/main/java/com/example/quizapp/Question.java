package com.example.quizapp;

public class Question {
    String question;
    boolean correctAnswer;

    public Question(String str, boolean bool) {
        this.question = str;
        this.correctAnswer = bool;
    }

    public String getQuestion() {
        return question;
    }

    public boolean checkAnswer(boolean userInput) {
        return userInput == correctAnswer;
    }
}

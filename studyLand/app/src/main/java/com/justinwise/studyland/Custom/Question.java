package com.justinwise.studyland.Custom;

/**
 * Created by jwise200 on 7/31/2017.
 */

public class Question {
    private String question;
    private String correctAnswer;
    private String dummy1;
    private String dummy2;
    private String dummy3;

    public Question(String newQuestion, String newCorrect, String newDummy1, String newDummy2, String newDummy3) {
        question = newQuestion;
        correctAnswer = newCorrect;
        dummy1 = newDummy1;
        dummy2 = newDummy2;
        dummy3 = newDummy3;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getDummy1() {
        return dummy1;
    }

    public String getDummy2() {
        return dummy2;
    }

    public String getDummy3() {
        return dummy3;
    }

    public String getQuestion() {
        return question;
    }
}

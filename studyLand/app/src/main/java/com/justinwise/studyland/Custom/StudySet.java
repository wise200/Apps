package com.justinwise.studyland.Custom;

import java.util.ArrayList;

/**
 * Created by jwise200 on 7/31/2017.
 */

public class StudySet {
    private String name;
    private ArrayList<Question> questions;
    private int pos;

    public StudySet(String newName) {
        name = newName;
        questions = new ArrayList<Question>();
        pos = -1;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void shuffle() {
        for (int i = 0; i < questions.size() - 1; i++) {
            int rand = (int) (Math.random() * (questions.size() - i)) + i;
            Question temp = questions.get(i);
            questions.set(i, questions.get(rand));
            questions.set(rand, temp);
        }
        pos = -1;
    }

    public Question getQuestion() {
        pos++;
        if (pos == questions.size()) {
            shuffle();
            return getQuestion();
        }
        return questions.get(pos);
    }
}

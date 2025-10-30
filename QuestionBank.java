/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_2;

/**
 *
 * @author OEM
 */
import java.util.Collections;
import java.util.List;

public class QuestionBank {
    private List<Question> questions;

    public QuestionBank(List<Question> questions) {
        this.questions = questions;
        Collections.shuffle(this.questions);
    }

    public Question getQuestion(int index) {
        return questions.get(index);
    }

    public int size() {
        return questions.size();
    }
}



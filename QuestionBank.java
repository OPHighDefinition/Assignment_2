package assignment_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionBank {

    private List<Question> questions;

    // Default constructor for building the bank dynamically
    public QuestionBank() {
        this.questions = new ArrayList<>();
    }

    // Optional constructor to initialize with a list of questions
    public QuestionBank(List<Question> questions) {
        this.questions = new ArrayList<>(questions);
        Collections.shuffle(this.questions); // Shuffle for randomness
    }

    // Add new questions and reshuffle
    public void addQuestions(List<Question> newQuestions) {
        this.questions.addAll(newQuestions);
        Collections.shuffle(this.questions);
    }

    // Retrieve question by index
    public Question getQuestion(int index) {
        return questions.get(index);
    }

    // Get total number of questions
    public int size() {
        return questions.size();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_2;

/**
 *
 * @author OEM
 */
public class Question {

    private final String prompt;
    private final String[] options;
    private final char correctAnswer;

    // Constructor to initialize question data
    public Question(String prompt, String[] options, char correctAnswer) {
        this.prompt = prompt;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    // Display question and options
    public void display(int number) {
        System.out.println("\nQuestion " + number + ": " + prompt);
        for (String option : options) {
            System.out.println(option);
        }
    }

    // Check if user's input matches the correct answer
    public boolean isCorrect(String input) {
        return input.trim().equalsIgnoreCase(String.valueOf(correctAnswer));
    }
}

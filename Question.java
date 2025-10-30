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
    private String prompt;
    private String[] options;
    private char correctAnswer;

    public Question(String prompt, String[] options, char correctAnswer) {
        this.prompt = prompt;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public void display(int number) {
        System.out.println("\nQuestion " + number + ": " + prompt);
        for (String option : options) {
            System.out.println(option);
        }
    }
//Checks if users input is correct
    public boolean isCorrect(String input) {
        return input.trim().equalsIgnoreCase(String.valueOf(correctAnswer));
    }
}


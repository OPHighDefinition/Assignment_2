/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_2;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt player for their name
        System.out.print("Enter your name: ");
        Player player = new Player(scanner.nextLine());

        // Welcome message
        System.out.println("Welcome to the Quiz Game, " + player.getName() + "!");

        // Load questions from database by difficulty level
        List<Question> easyQuestions = QuestionLoader.loadByDifficulty(1);
        List<Question> mediumQuestions = QuestionLoader.loadByDifficulty(2);
        List<Question> hardQuestions = QuestionLoader.loadByDifficulty(3);

        // Combine all questions into a single bank
        QuestionBank bank = new QuestionBank();
        bank.addQuestions(easyQuestions);
        bank.addQuestions(mediumQuestions);
        bank.addQuestions(hardQuestions);

        // Start the game
        GameEngine game = new GameEngine(player, bank);
        game.play();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_2;

/**
 *
 * @author OEM
 */
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        Player player = new Player(scanner.nextLine());

        List<Question> questions = Arrays.asList(
                new Question("What is the capital of New Zealand?", new String[]{"A: Auckland", "B: Wellington", "C: Christchurch", "D: Hamilton"}, 'B'),
            new Question("What is the main ingredient in guacamole?", new String[]{"A: Avocado", "B: Lemon", "C: Tomatoes", "D: Onion"}, 'A')
            // Add more questions here
        );

        QuestionBank bank = new QuestionBank(questions);
        GameEngine game = new GameEngine(player, bank);
        game.play();
    }
}


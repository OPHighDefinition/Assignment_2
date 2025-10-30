/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_2;

/**
 *
 * @author OEM
 */
import java.io.PrintWriter;
import java.util.Scanner;

public class GameEngine {
    private Player player;
    private QuestionBank bank;
    private int[] moneyTree = {100, 500, 1200, 2500, 8000, 32000, 125000, 250000, 500000, 1000000};

    public GameEngine(Player player, QuestionBank bank) {
        this.player = player;
        this.bank = bank;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < bank.size(); i++) {
            Question q = bank.getQuestion(i);
            q.display(i + 1);

            System.out.print("Your answer (A/B/C/D): ");
            String input = scanner.nextLine().trim().toUpperCase();

            while (!input.matches("[ABCD]")) {
                System.out.print("Invalid input. Try again (A/B/C/D): ");
                input = scanner.nextLine().trim().toUpperCase();
            }

            if (q.isCorrect(input)) {
                System.out.println("Correct!\n");
                player.incrementScore();
            } else {
                System.out.println("Incorrect. Final payout: $" + moneyTree[player.getScore()]);
                return;
            }

            System.out.print("Would you like to collect your money now? (y/n): ");
            String collect = scanner.nextLine().trim().toLowerCase();
            if (collect.equals("y")) {
                System.out.println("Congrats! You won $" + moneyTree[player.getScore()]);
                saveScore();
                return;
            }
        }

        System.out.println("You completed all questions! Final payout: $" + moneyTree[player.getScore()]);
        saveScore();
    }

    private void saveScore() {
        try (PrintWriter pw = new PrintWriter("./resources/score.txt")) {
            pw.println(player.getName() + ": $" + moneyTree[player.getScore()]);
        } catch (Exception e) {
            System.out.println("Error saving score.");
        }
    }
}


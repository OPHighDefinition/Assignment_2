package assignment_2;

import java.io.PrintWriter;
import java.util.Scanner;

public class GameEngine {

    private Player player;
    private QuestionBank bank;
    private MoneyTree moneyTree = new MoneyTree(); // Handles prize and milestone logic

    public GameEngine(Player player, QuestionBank bank) {
        this.player = player;
        this.bank = bank;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        // Loop through each question in the bank
        for (int i = 0; i < bank.size(); i++) {
            Question q = bank.getQuestion(i);
            q.display(i + 1); // Show question number and content

            // Prompt for answer or quit
            System.out.print("Your answer (A/B/C/D or Q to quit): ");
            String input = scanner.nextLine().trim().toUpperCase();

            // Validate input
            while (!input.matches("[ABCDQ]")) {
                System.out.print("Invalid input. Try again (A/B/C/D or Q to quit): ");
                input = scanner.nextLine().trim().toUpperCase();
            }

            // Handle quit option
            if (input.equals("Q")) {
                int payout = moneyTree.getPrize(player.getScore());
                System.out.println("You've chosen to quit. Final payout: $" + payout);
                saveScore(payout);
                return;
            }

            // Check if answer is correct
            if (q.isCorrect(input)) {
                System.out.println("Correct!\n");
                player.incrementScore(); // Increase score

                // Notify if player reached a milestone
                if (moneyTree.isMilestone(player.getScore())) {
                    System.out.println("You've reached a milestone! Your winnings are now guaranteed at $" +
                                moneyTree.getMilestonePrize(player.getScore()));
                }

            } else {
                // Incorrect answer ends game, payout based on last milestone
                int guaranteed = moneyTree.getMilestonePrize(player.getScore());
                System.out.println("Incorrect. Final payout: $" + guaranteed);
                saveScore(guaranteed);
                return;
            }

            // Ask if player wants to collect winnings
            System.out.print("Would you like to collect your money now? (y/n): ");
            String collect = scanner.nextLine().trim().toLowerCase();
            if (collect.equals("y")) {
                int payout = moneyTree.getPrize(player.getScore());
                System.out.println("Congrats! You won $" + payout);
                saveScore(payout);
                return;
            }
        }

        // Player completed all questions
        int payout = moneyTree.getPrize(player.getScore());
        System.out.println("You completed all questions! Final payout: $" + payout);
        saveScore(payout);
    }

    // Save player's name and payout to score file
    private void saveScore(int payout) {
        try (PrintWriter pw = new PrintWriter("./resources/score.txt")) {
            pw.println(player.getName() + ": $" + payout);
        } catch (Exception e) {
            System.out.println("Error saving score.");
        }
    }
}

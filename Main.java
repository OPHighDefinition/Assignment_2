/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        String name = JOptionPane.showInputDialog("Enter your name:");
        if (name == null || name.trim().isEmpty()) {
            return;
        }

        Player player = new Player(name.trim());

        // Load questions by difficulty
        List<Question> easy = QuestionLoader.loadByDifficulty(1);
        List<Question> medium = QuestionLoader.loadByDifficulty(2);
        List<Question> hard = QuestionLoader.loadByDifficulty(3);

        // Combine and shuffle easy + medium
        List<Question> mainSet = new ArrayList<>();
        mainSet.addAll(easy);
        mainSet.addAll(medium);
        Collections.shuffle(mainSet);

        // Select first 10 for main game
        List<Question> selectedMain = mainSet.subList(0, Math.min(10, mainSet.size()));

        // Select 1 bonus question from hard
        Question bonus = hard.isEmpty() ? null : hard.get(0); // or random from hard

        // Build question bank
        QuestionBank bank = new QuestionBank(selectedMain);
        if (bonus != null) {
            bank.addQuestions(Collections.singletonList(bonus));
        }

        SwingUtilities.invokeLater(() -> new QuizGUI(player, bank));
    }
}

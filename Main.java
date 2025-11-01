package assignment_2;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        String name = JOptionPane.showInputDialog("Enter your name:");
        if (name == null || name.trim().isEmpty()) return;

        Player player = new Player(name.trim());

        List<Question> easy = QuestionLoader.loadByDifficulty(1);
        List<Question> medium = QuestionLoader.loadByDifficulty(2);
        List<Question> hard = QuestionLoader.loadByDifficulty(3);

        QuestionBank bank = new QuestionBank();
        bank.addQuestions(easy);
        bank.addQuestions(medium);
        bank.addQuestions(hard);

        SwingUtilities.invokeLater(() -> new QuizGUI(player, bank));
    }
}

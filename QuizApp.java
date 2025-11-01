import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizApp extends JFrame implements ActionListener {
    // Quiz data
    String[][] questions = {
            {"What is the capital of New Zealand", "Auckland", "Wellington", "Christchurch", "Hamilton", "Wellington"},
            {"What is the capital of France?", "Paris", "Rome", "Berlin", "Madrid", "Paris"},
            {"Which planet is known as the Red Planet?", "Earth", "Venus", "Mars", "Jupiter", "Mars"},
            {"What is 5 + 7?", "10", "11", "12", "13", "12"},
            {"Who wrote 'Romeo and Juliet'?", "Charles Dickens", "William Shakespeare", "Mark Twain", "J.K. Rowling", "William Shakespeare"}
    };

    int index = 0;
    int correct = 0;

    JLabel questionLabel;
    JRadioButton[] options = new JRadioButton[4];
    ButtonGroup group;
    JButton submitButton;
    JButton nextButton;

    QuizApp() {
        setTitle("Quiz App");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(245, 245, 245)); // Light theme

        // Question Label
        questionLabel = new JLabel("Question will appear here");
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        questionLabel.setBounds(50, 50, 500, 30);
        add(questionLabel);

        // Radio Buttons
        group = new ButtonGroup();
        int y = 100;
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setBounds(60, y, 400, 30);
            options[i].setBackground(new Color(245, 245, 245));
            group.add(options[i]);
            add(options[i]);
            y += 40;
        }

        // Submit Button
        submitButton = new JButton("Submit");
        submitButton.setBounds(150, 250, 120, 35);
        submitButton.setBackground(new Color(0, 123, 255));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.addActionListener(this);
        add(submitButton);

        // Next Button
        nextButton = new JButton("Next");
        nextButton.setBounds(320, 250, 120, 35);
        nextButton.setBackground(new Color(40, 167, 69));
        nextButton.setForeground(Color.WHITE);
        nextButton.setFocusPainted(false);
        nextButton.addActionListener(this);
        nextButton.setEnabled(false);
        add(nextButton);

        loadQuestion();
        setVisible(true);
    }

    void loadQuestion() {
        if (index < questions.length) {
            questionLabel.setText("Q" + (index + 1) + ": " + questions[index][0]);
            for (int i = 0; i < 4; i++) {
                options[i].setText(questions[index][i + 1]);
                options[i].setSelected(false);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String selected = null;
            for (JRadioButton rb : options) {
                if (rb.isSelected()) {
                    selected = rb.getText();
                    break;
                }
            }

            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Please select an answer!");
                return;
            }

            if (selected.equals(questions[index][5])) {
                correct++;
                JOptionPane.showMessageDialog(this, "Correct! ✅");
            } else {
                JOptionPane.showMessageDialog(this, "Wrong! ❌\nCorrect answer: " + questions[index][5]);
            }

            submitButton.setEnabled(false);
            nextButton.setEnabled(true);

        } else if (e.getSource() == nextButton) {
            index++;
            if (index < questions.length) {
                loadQuestion();
                submitButton.setEnabled(true);
                nextButton.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Quiz Finished!\nYour score: " + correct + " / " + questions.length);
                dispose();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(QuizApp::new);
    }
}

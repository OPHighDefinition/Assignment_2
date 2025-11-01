package assignment_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class QuizGUI {

    // GUI components
    private JFrame frame;
    private JLabel questionLabel, feedbackLabel, payoutLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup optionGroup;
    private JButton submitButton, quitButton, collectButton;

    // Game logic components
    private Player player;
    private QuestionBank bank;
    private MoneyTree moneyTree;
    private int currentIndex = 0;

    // Bonus question flags
    private boolean bonusOffered = false;
    private boolean bonusAccepted = false;

    // Constructor initializes game state and GUI
    public QuizGUI(Player player, QuestionBank bank) {
        this.player = player;
        this.bank = bank;
        this.moneyTree = new MoneyTree();
        createUI();       // Build the GUI
        loadQuestion();   // Load the first question
    }

    // Builds the GUI layout and components
    private void createUI() {
        frame = new JFrame("Quiz Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Question label at top
        questionLabel = new JLabel("Question will appear here");
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(questionLabel, BorderLayout.NORTH);

        // Multiple-choice options in center
        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        optionButtons = new JRadioButton[4];
        optionGroup = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            optionGroup.add(optionButtons[i]);
            optionsPanel.add(optionButtons[i]);
        }

        frame.add(optionsPanel, BorderLayout.CENTER);

        // Control buttons at bottom
        JPanel controlPanel = new JPanel();
        submitButton = new JButton("Submit");
        quitButton = new JButton("Quit");
        collectButton = new JButton("Collect");

        controlPanel.add(submitButton);
        controlPanel.add(quitButton);
        controlPanel.add(collectButton);
        frame.add(controlPanel, BorderLayout.SOUTH);

        // Feedback and payout info on the right
        feedbackLabel = new JLabel(" ");
        payoutLabel = new JLabel(" ");
        JPanel statusPanel = new JPanel(new GridLayout(2, 1));
        statusPanel.add(feedbackLabel);
        statusPanel.add(payoutLabel);
        frame.add(statusPanel, BorderLayout.EAST);

        // Button actions
        submitButton.addActionListener(this::handleSubmit);
        quitButton.addActionListener(e -> endGame("quit"));
        collectButton.addActionListener(e -> endGame("collect"));

        frame.setVisible(true);
    }

    // Loads the next question or triggers bonus/ending
    private void loadQuestion() {
        // Offer bonus question after 10 main questions
        if (currentIndex == 10 && !bonusOffered) {
            bonusOffered = true;
            int result = JOptionPane.showConfirmDialog(frame,
                    "You've completed the main game!\nWould you like to try a bonus question for extra winnings?",
                    "Bonus Question",
                    JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.YES_OPTION) {
                bonusAccepted = true;
            } else {
                endGame("complete");
                return;
            }
        }

        // End game if all questions are done
        if (currentIndex >= bank.size()) {
            endGame("complete");
            return;
        }

        // Load current question
        Question q = bank.getQuestion(currentIndex);
        if (bonusAccepted && currentIndex == 10) {
            questionLabel.setText("Bonus Question: " + q.getPrompt());
        } else {
            questionLabel.setText("Q" + (currentIndex + 1) + ": " + q.getPrompt());
        }

        // Load options
        String[] options = q.getOptions();
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(options[i]);
            optionButtons[i].setActionCommand(String.valueOf((char) ('A' + i)));
        }

        optionGroup.clearSelection();
        feedbackLabel.setText(" ");
    }

    // Handles answer submission
    private void handleSubmit(ActionEvent e) {
        // Prevent overflow
        if (currentIndex >= bank.size()) {
            return;
        }

        // Require selection
        if (optionGroup.getSelection() == null) {
            feedbackLabel.setText("Please select an answer.");
            return;
        }

        String selected = optionGroup.getSelection().getActionCommand();
        Question q = bank.getQuestion(currentIndex);

        // Correct answer logic
        if (q.isCorrect(selected)) {
            if (bonusAccepted && currentIndex == 10) {
                feedbackLabel.setText("Bonus correct! Extra prize awarded.");
                player.incrementScore(); // Optional: treat bonus as extra point
            } else {
                player.incrementScore();
                feedbackLabel.setText("Correct!");
                if (moneyTree.isMilestone(player.getScore())) {
                    payoutLabel.setText("Milestone reached! Guaranteed: $" + moneyTree.getMilestonePrize(player.getScore()));
                }
            }
            currentIndex++;
            loadQuestion();
        } else {
            // Incorrect bonus answer — no penalty
            if (bonusAccepted && currentIndex == 10) {
                feedbackLabel.setText("Bonus incorrect. No extra prize.");
                currentIndex++;
                loadQuestion(); // Will trigger endGame
            } else {
                // Incorrect main question — end game with milestone payout
                feedbackLabel.setText("Incorrect.");
                endGame("fail");
            }
        }
    }

    // Ends the game and shows final payout
    private void endGame(String mode) {
        int payout;
        switch (mode) {
            case "quit":
            case "collect":
            case "complete":
                payout = moneyTree.getPrize(player.getScore()); // Full prize
                break;
            case "fail":
            default:
                payout = moneyTree.getMilestonePrize(player.getScore()); // Milestone fallback
                break;
        }

        // Disable buttons
        submitButton.setEnabled(false);
        quitButton.setEnabled(false);
        collectButton.setEnabled(false);

        // Show final message
        JOptionPane.showMessageDialog(frame,
                player.getName() + ", you won $" + payout,
                "Game Over",
                JOptionPane.INFORMATION_MESSAGE);
        frame.dispose();
    }
}

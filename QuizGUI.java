package assignment_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class QuizGUI {

    private JFrame frame;
    private JLabel questionLabel, feedbackLabel, payoutLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup optionGroup;
    private JButton submitButton, quitButton, collectButton;

    private Player player;
    private QuestionBank bank;
    private MoneyTree moneyTree;
    private int currentIndex = 0;

    public QuizGUI(Player player, QuestionBank bank) {
        this.player = player;
        this.bank = bank;
        this.moneyTree = new MoneyTree();
        createUI();
        loadQuestion();
    }

    private void createUI() {
        frame = new JFrame("Quiz Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        questionLabel = new JLabel("Question will appear here");
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        optionButtons = new JRadioButton[4];
        optionGroup = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            optionGroup.add(optionButtons[i]);
            optionsPanel.add(optionButtons[i]);
        }

        frame.add(optionsPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        submitButton = new JButton("Submit");
        quitButton = new JButton("Quit");
        collectButton = new JButton("Collect");

        controlPanel.add(submitButton);
        controlPanel.add(quitButton);
        controlPanel.add(collectButton);
        frame.add(controlPanel, BorderLayout.SOUTH);

        feedbackLabel = new JLabel(" ");
        payoutLabel = new JLabel(" ");
        JPanel statusPanel = new JPanel(new GridLayout(2, 1));
        statusPanel.add(feedbackLabel);
        statusPanel.add(payoutLabel);
        frame.add(statusPanel, BorderLayout.EAST);

        submitButton.addActionListener(this::handleSubmit);
        quitButton.addActionListener(e -> endGame("quit"));
        collectButton.addActionListener(e -> endGame("collect"));

        frame.setVisible(true);
    }

    private void loadQuestion() {
        if (currentIndex >= bank.size()) {
            endGame("complete");
            return;
        }

        Question q = bank.getQuestion(currentIndex);
        questionLabel.setText("Q" + (currentIndex + 1) + ": " + q.getPrompt());
        String[] options = q.getOptions();
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(options[i]);
            optionButtons[i].setActionCommand(String.valueOf((char) ('A' + i)));
        }
        optionGroup.clearSelection();
        feedbackLabel.setText(" ");
    }

    private void handleSubmit(ActionEvent e) {
        if (optionGroup.getSelection() == null) {
            feedbackLabel.setText("Please select an answer.");
            return;
        }

        String selected = optionGroup.getSelection().getActionCommand();
        Question q = bank.getQuestion(currentIndex);

        if (q.isCorrect(selected)) {
            player.incrementScore();
            feedbackLabel.setText("✅ Correct!");
            if (moneyTree.isMilestone(player.getScore())) {
                payoutLabel.setText("Milestone reached! Guaranteed: $" + moneyTree.getMilestonePrize(player.getScore()));
            }
            currentIndex++;
            loadQuestion();
        } else {
            feedbackLabel.setText("❌ Incorrect.");
            endGame("fail");

        }
    }

    private void endGame(String mode) {
        int payout;
        switch (mode) {
            case "quit":
            case "collect":
            case "complete":
                payout = moneyTree.getPrize(player.getScore());
                break;
            case "fail":
            default:
                payout = moneyTree.getMilestonePrize(player.getScore());
                break;
        }

        JOptionPane.showMessageDialog(frame,
                player.getName() + ", you won $" + payout,
                "Game Over",
                JOptionPane.INFORMATION_MESSAGE);
        frame.dispose();
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionLoader {

    // JDBC URL for connecting to Derby database
    private static final String DB_URL = "jdbc:derby://localhost:1527/questions";

    // Loads questions based on difficulty level (1 = easy, 2 = medium, 3 = hard)
    public static List<Question> loadByDifficulty(int difficultyLevel) {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT * FROM PDC.QUESTIONS WHERE difficulty_level = ?";

        // Connect to database and execute query
        try (Connection conn = DriverManager.getConnection(DB_URL, "pdc", "pdc"); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, difficultyLevel);
            ResultSet rs = stmt.executeQuery();

            // Extract question data from result set
            while (rs.next()) {
                String prompt = rs.getString("prompt");
                String[] options = {
                    rs.getString("option_a"),
                    rs.getString("option_b"),
                    rs.getString("option_c"),
                    rs.getString("option_d")
                };
                char correct = rs.getString("correct_answer").charAt(0);
                questions.add(new Question(prompt, options, correct));
            }

        } catch (SQLException e) {
            System.out.println("Error loading questions: " + e.getMessage());
        }

        return questions;
    }
}

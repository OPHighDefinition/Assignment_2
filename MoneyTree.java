/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_2;


public class MoneyTree {
    private final int[] prizes = {100, 500, 1200, 2500, 8000, 32000, 125000, 250000, 500000, 1000000};
    private final int[] milestones = {3, 5, 7}; // Guaranteed levels

    public int getPrize(int score) {
        int index = Math.min(score, prizes.length - 1);
        return prizes[index];
    }

    public int getMilestonePrize(int score) {
        for (int i = milestones.length - 1; i >= 0; i--) {
            if (score >= milestones[i]) {
                return prizes[milestones[i]];
            }
        }
        return 0;
    }

    public boolean isMilestone(int score) {
        for (int m : milestones) {
            if (score == m) return true;
        }
        return false;
    }

    public int length() {
        return prizes.length;
    }
}


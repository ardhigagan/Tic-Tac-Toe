import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class tic_tac_toe_usingjawaswing extends JFrame {
    private JButton[][] buttons = new JButton[3][3];
    private boolean playerTurn = true;
    private Random rand = new Random();

    public tic_tac_toe_usingjawaswing() {
        setTitle("Tic Tac Toe - Player (X) vs Computer (O)");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));
        initBoard();
        setVisible(true);
    }

    private void initBoard() {
        Font font = new Font("Arial", Font.BOLD, 60);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                JButton btn = new JButton("");
                btn.setFont(font);
                btn.setFocusPainted(false);
                buttons[row][col] = btn;
                add(btn);

                final int r = row;
                final int c = col;

                btn.addActionListener(e -> {
                    if (btn.getText().equals("") && playerTurn) {
                        btn.setText("X");
                        btn.setForeground(Color.BLUE);
                        playerTurn = false;

                        if (checkGameOver("X")) return;

                        SwingUtilities.invokeLater(() -> {
                            try { Thread.sleep(500); } catch (Exception ex) {}
                            computerMove();
                        });
                    }
                });
            }
        }
    }

    private void computerMove() {
        int row, col;

        while (true) {
            row = rand.nextInt(3);
            col = rand.nextInt(3);
            if (buttons[row][col].getText().equals("")) {
                buttons[row][col].setText("O");
                buttons[row][col].setForeground(Color.RED);
                break;
            }
        }

        playerTurn = true;
        checkGameOver("O");
    }

    private boolean checkGameOver(String player) {
        if (hasWon(player)) {
            showEndMessage(player.equals("X") ? "YOU WON!" : "COMPUTER WON!");
            return true;
        } else if (isDraw()) {
            showEndMessage("IT'S A DRAW!");
            return true;
        }
        return false;
    }

    private boolean hasWon(String player) {
        // Rows and Columns
        for (int i = 0; i < 3; i++) {
            if (
                buttons[i][0].getText().equals(player) &&
                buttons[i][1].getText().equals(player) &&
                buttons[i][2].getText().equals(player)
            ) return true;

            if (
                buttons[0][i].getText().equals(player) &&
                buttons[1][i].getText().equals(player) &&
                buttons[2][i].getText().equals(player)
            ) return true;
        }

        // Diagonals
        if (
            buttons[0][0].getText().equals(player) &&
            buttons[1][1].getText().equals(player) &&
            buttons[2][2].getText().equals(player)
        ) return true;

        if (
            buttons[0][2].getText().equals(player) &&
            buttons[1][1].getText().equals(player) &&
            buttons[2][0].getText().equals(player)
        ) return true;

        return false;
    }

    private boolean isDraw() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (buttons[i][j].getText().equals(""))
                    return false;
        return true;
    }

    private void showEndMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
        resetBoard();
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                buttons[i][j].setText("");
        playerTurn = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new tic_tac_toe_usingjawaswing());
    }
}

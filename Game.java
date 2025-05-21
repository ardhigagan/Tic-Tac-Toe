import java.util.Scanner;
import java.util.Random;

public class Game {

    static char[][] board = {
        {' ', ' ', ' '},
        {' ', ' ', ' '},
        {' ', ' ', ' '}
    };

    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();

    public static void intro(){
        System.out.println("================================");
        System.out.println("      >>> TIC TAC TOE <<<");
        System.out.println("================================");
        System.out.println("Player Symbol : X");
        System.out.println("Computer      : O\n");
        System.out.println("How to play:");
        System.out.println("- Enter two numbers (1-3) for row and column");
        System.out.println("- Get 3 in a row (horizontally, vertically, or diagonally) to win.");
        System.out.println("- If all spots are filled and no one wins, then it will be a draw.\n\n");
    }

    // Print the current board
    public static void printBoard() {
        System.out.println("\nBoard:\n");
        for (int i = 0; i < 3; i++) {
            System.out.println(" " + board[i][0] + " | " + board[i][1] + " | " + board[i][2]);
            if (i < 2) System.out.println("---|---|---");
        }
    }

    //Player's Move
    public static void playerMove() {
        int row, col;
        while (true) {
            System.out.print("\nEnter row and column (1-3): ");
            row = sc.nextInt() - 1;
            col = sc.nextInt() - 1;

            if (isValidMove(row, col)) {
                board[row][col] = 'X';
                break;
            } else {
                System.out.println("Invalid move! That spot is taken or out of range. Try again.");
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted");
        }
        System.out.println("\n------------");
        System.out.println("YOUR'S MOVE");
        System.out.println("------------");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted");
        }
        printBoard();
    }


    // Computer's Move 
    public static void computerMove() {
        int row, col;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted");
        }
        System.out.println("\n----------------");
        System.out.println("COMPUTER'S MOVE");
        System.out.println("----------------");
        while (true) {
            row = rand.nextInt(3);
            col = rand.nextInt(3);
            if (isValidMove(row, col)) {
                board[row][col] = 'O';
                break;
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted");
        }
        printBoard();
    }

    // Check for valid move
    public static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    // Check if the game is over (win or draw)
    public static boolean isGameOver(char player) {
        if (hasWon(player)) {
            if(player=='X'){
                System.out.println(" ");
                flashMessage("YOU WON !");
            } else {
                System.out.println(" ");
                flashMessage("COMPUTER WON !");
            }
            return true;
        }
        if (isDraw()) {
            System.out.println();
            flashMessage("IT'S A DRAW !");
            return true;
        }
        return false;
    }

    // Check win condition
    public static boolean hasWon(char player) {
        // Check rows, columns, diagonals
        for (int i = 0; i < 3; i++)
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player))
                return true;

        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
            (board[0][2] == player && board[1][1] == player && board[2][0] == player))
            return true;

        return false;
    }

    // Check for draw
    public static boolean isDraw() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ')
                    return false;
        return true;
    }

    public static void flashMessage(String msg) {
        for (int i = 0; i < 6; i++) {
            System.out.print("\r" + (i % 2 == 0 ? msg : " ".repeat(msg.length())));
            try { 
                Thread.sleep(300); 
            } catch (Exception e) {}
        }
        System.out.println("\r" + msg);  // final display
    }

    public static void main(String[] args) {
        intro();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted");
        }

        System.out.println("Let us begin!\n");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted");
        }

        printBoard();

        while (true) {
            playerMove();
            if (isGameOver('X')) break;

            computerMove();
            if (isGameOver('O')) break;
        }
    }

}
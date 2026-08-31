import java.util.Scanner;

public class TicTacToe {
    // 3x3 game board grid
    private static char[][] board = new char[3][3];
    // Track whose turn it is ('X' or 'O')
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeBoard();
        System.out.println("Welcome to Tic-Tac-Toe!");
        
        while (true) {
            printBoard();
            System.out.println("Player " + currentPlayer + ", enter your move (row 1-3 and column 1-3) separated by space: ");
            
            int row, col;
            // Validate that the input is structural integers
            if (scanner.hasNextInt()) {
                row = scanner.nextInt() - 1;
                if (scanner.hasNextInt()) {
                    col = scanner.nextInt() - 1;
                } else {
                    System.out.println("Invalid input. Please enter numbers only.");
                    scanner.next(); // Clear invalid input
                    continue;
                }
            } else {
                System.out.println("Invalid input. Please enter numbers only.");
                scanner.next(); // Clear invalid input
                continue;
            }

            // Validate boundaries and slot vacancy
            if (row < 0 || row > 2 || col < 0 || col > 2) {
                System.out.println("This position is off the board! Enter values between 1 and 3.");
                continue;
            }
            if (board[row][col] != '-') {
                System.out.println("That slot is already taken! Try again.");
                continue;
            }

            // Make the move
            board[row][col] = currentPlayer;

            // Check game state conditions
            if (hasWon()) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins! 🎉");
                break;
            }
            if (isBoardFull()) {
                printBoard();
                System.out.println("The game is a draw! 🤝");
                break;
            }

            // Switch to the other player
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
        scanner.close();
    }

    // Initialize the board with empty dashes
    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Render the grid layout visually in terminal
    private static void printBoard() {
        System.out.println("\n-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    // Scan for filled array conditions across rows, columns, and diagonals
    private static boolean hasWon() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) return true;
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) return true;
        }
        // Check diagonals
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) return true;
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) return true;
        
        return false;
    }

    // Determine if board is full without a winner declared
    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}

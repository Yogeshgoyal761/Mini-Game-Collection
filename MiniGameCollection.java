import java.util.Scanner;
import java.util.Random;

public class MiniGameCollection {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- MINI GAME COLLECTION ---");
            System.out.println("1. Rock-Paper-Scissors");
            System.out.println("2. Tic-Tac-Toe");
            System.out.println("3. Guess the Number");
            System.out.println("4. Exit");
            System.out.print("Choose a game (1-4): ");

            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    playRockPaperScissors();
                    break;
                case 2:
                    playTicTacToe();
                    break;
                case 3:
                    playGuessTheNumber();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Thanks for playing!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        input.close();
    }

    // ===== 1. ROCK-PAPER-SCISSORS =====
    public static void playRockPaperScissors() {
        Scanner input = new Scanner(System.in);
        Random rnd = new Random();

        final int ROCK = 0, PAPER = 1, SCISSORS = 2;

        System.out.println("\n--- ROCK-PAPER-SCISSORS ---");
        System.out.println("Enter your choice (0=rock, 1=paper, 2=scissors): ");
        int userChoice = input.nextInt();

        while (userChoice < 0 || userChoice > 2) {
            System.out.println("Invalid input. Enter 0, 1, or 2.");
            userChoice = input.nextInt();
        }

        int computerChoice = rnd.nextInt(3);
        printChoice("User", userChoice, ROCK, PAPER, SCISSORS);
        printChoice("Computer", computerChoice, ROCK, PAPER, SCISSORS);

        if (userChoice == computerChoice) {
            System.out.println("It's a draw!");
        } else if ((userChoice == ROCK && computerChoice == SCISSORS) ||
                   (userChoice == PAPER && computerChoice == ROCK) ||
                   (userChoice == SCISSORS && computerChoice == PAPER)) {
            System.out.println("You win!");
        } else {
            System.out.println("Computer wins!");
        }
    }

    // Helper method to print choices
    public static void printChoice(String player, int choice, int ROCK, int PAPER, int SCISSORS) {
        String move;
        if (choice == ROCK) move = "ROCK";
        else if (choice == PAPER) move = "PAPER";
        else move = "SCISSORS";
        System.out.println(player + " chose: " + move);
    }

    // ===== 2. TIC-TAC-TOE =====
    public static void playTicTacToe() {
        Scanner input = new Scanner(System.in);
        char[][] board = new char[3][3];
        char currentPlayer = 'X';

        initializeBoard(board);

        System.out.println("\n--- TIC-TAC-TOE ---");
        System.out.println("Enter row (0-2) and column (0-2) separated by space:");

        while (true) {
            printBoard(board);
            System.out.print("Player " + currentPlayer + "'s turn: ");
            int row = input.nextInt();
            int col = input.nextInt();

            if (row < 0 || row > 2 || col < 0 || col > 2 || board[row][col] != '\0') {
                System.out.println("Invalid move! Try again.");
                continue;
            }

            board[row][col] = currentPlayer;

            if (checkWin(board, currentPlayer)) {
                printBoard(board);
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }

            if (checkDraw(board)) {
                printBoard(board);
                System.out.println("It's a draw!");
                break;
            }

            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    // Initialize the board with empty spaces
    public static void initializeBoard(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '\0';
            }
        }
    }

    // Print the Tic-Tac-Toe board
    public static void printBoard(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] == '\0' ? "- " : board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Check if a player has won
    public static boolean checkWin(char[][] board, char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true; // Rows
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) return true; // Columns
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) return true; // Diagonal 1
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) return true; // Diagonal 2
        return false;
    }

    // Check if the game is a draw
    public static boolean checkDraw(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '\0') return false;
            }
        }
        return true;
    }

    // ===== 3. GUESS THE NUMBER =====
    public static void playGuessTheNumber() {
        Scanner input = new Scanner(System.in);
        Random rnd = new Random();

        int number = rnd.nextInt(100) + 1; // Random number between 1-100
        int guess;
        int attempts = 0;

        System.out.println("\n--- GUESS THE NUMBER (1-100) ---");
        System.out.println("Guess the number I'm thinking of!");

        do {
            System.out.print("Enter your guess: ");
            guess = input.nextInt();
            attempts++;

            if (guess < number) {
                System.out.println("Too low! Try higher.");
            } else if (guess > number) {
                System.out.println("Too high! Try lower.");
            } else {
                System.out.println("Correct! You guessed it in " + attempts + " attempts.");
            }
        } while (guess != number);
    }
}
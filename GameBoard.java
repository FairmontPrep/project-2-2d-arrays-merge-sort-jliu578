import javax.swing.*;
import java.awt.*;

public class GameBoard extends JFrame {
    private static final int SIZE = 8; // Size of the grid (8x8 board)
    private JPanel[][] squares = new JPanel[SIZE][SIZE]; // 2D array of JPanels for the board
    public String[][] piecesArray; // Array to hold piece images

    public GameBoard() {
        setTitle("Chess Board");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE, SIZE)); // Create the grid layout

        // Initialize the pieces array (example values)
        initializePiecesArray();

        // Initialize the board and display pieces on it
        initializeBoard();
    }

    // Method to initialize the piecesArray with example values
    private void initializePiecesArray() {
        // Create an array of 8 rows and 1 column (only for image paths)
        piecesArray = new String[8][1];

        // Assign example pieces to the first two rows
        piecesArray[0][0] = "square.png";piecesArray[0][1] = "1";    // Image for knight
        piecesArray[1][0] = "triangle.png"; piecesArray[1][1]="3";

        // Fill the remaining rows with empty spaces (or placeholders)
        for (int i = 2; i < piecesArray.length; i++) {
            piecesArray[i][0] = "empty.png"; // Empty placeholder image
        }
    }

    // Method to initialize the board (add squares and assign pieces)
    private void initializeBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                // Create each square as a JPanel with BorderLayout
                squares[row][col] = new JPanel(new BorderLayout());

                // Set the background color to white (all squares filled with white)
                squares[row][col].setBackground(Color.WHITE);

                // Add black border to each square
                squares[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));

                // Check if there is a valid piece for this square
                if (row < piecesArray.length) {
                    String imagePath = piecesArray[row][0];       // Get the image path

                    // Add piece image if valid (non-empty)
                    if (!imagePath.equals("empty.png")) {
                        ImageIcon icon = new ImageIcon(imagePath); // Load the image
                        Image scaledImage = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                        JLabel pieceLabel = new JLabel(new ImageIcon(scaledImage)); // Scaled image
                        squares[row][col].add(pieceLabel, BorderLayout.CENTER);
                    }
                }

                // Add the panel (square) to the GridLayout
                add(squares[row][col]);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameBoard board = new GameBoard();
            board.setVisible(true);
        });
    }
}
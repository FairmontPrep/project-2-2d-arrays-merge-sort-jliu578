import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

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

        // Shuffle the pieces array to randomize the positions
        shufflePiecesArray();

        // Initialize the board and display pieces on it
        initializeBoard();

        // Sort the pieces array
        sortPiecesArray();

        // Update the board with sorted pieces
        updateBoard();
    }

    // Method to initialize the piecesArray with example values
    private void initializePiecesArray() {
        // List of all PNG files except grid.png
        String[] pieceImages = {
            "square.png", "triangle.png", "circle.png", "star.png", 
            "hexagon.png", "octagon.png", "pentagon.png", "diamond.png"
        };

        // Calculate the number of times each piece should be repeated
        int piecesPerType = (SIZE * SIZE) / pieceImages.length;

        // Create an array to hold all the pieces
        piecesArray = new String[SIZE * SIZE][1];

        // Assign the piece images to the array
        int index = 0;
        for (String piece : pieceImages) {
            for (int i = 0; i < piecesPerType; i++) {
                piecesArray[index][0] = piece;
                index++;
            }
        }

        // Fill any remaining slots with empty spaces (if the grid size is not perfectly divisible)
        while (index < piecesArray.length) {
            piecesArray[index][0] = "empty.png"; // Empty placeholder image
            index++;
        }
    }

    // Method to shuffle the piecesArray
    private void shufflePiecesArray() {
        List<String> piecesList = new ArrayList<>();
        for (int i = 0; i < piecesArray.length; i++) {
            piecesList.add(piecesArray[i][0]);
        }
        Collections.shuffle(piecesList);
        for (int i = 0; i < piecesArray.length; i++) {
            piecesArray[i][0] = piecesList.get(i);
        }
    }

    // Method to sort the piecesArray
    private void sortPiecesArray() {
        List<String> piecesList = new ArrayList<>();
        for (int i = 0; i < piecesArray.length; i++) {
            piecesList.add(piecesArray[i][0]);
        }
        Collections.sort(piecesList);
        for (int i = 0; i < piecesArray.length; i++) {
            piecesArray[i][0] = piecesList.get(i);
        }
    }

    // Method to initialize the board (add squares and assign pieces)
    private void initializeBoard() {
        int pieceIndex = 0;
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                // Create each square as a JPanel with BorderLayout
                squares[row][col] = new JPanel(new BorderLayout());

                // Set the background color to white (all squares filled with white)
                squares[row][col].setBackground(Color.WHITE);

                // Add black border to each square
                squares[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));

                // Check if there is a valid piece for this square
                if (pieceIndex < piecesArray.length) {
                    String imagePath = piecesArray[pieceIndex][0]; // Get the image path

                    // Add piece image if valid (non-empty)
                    if (!imagePath.equals("empty.png")) {
                        ImageIcon icon = new ImageIcon(imagePath); // Load the image
                        Image scaledImage = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                        JLabel pieceLabel = new JLabel(new ImageIcon(scaledImage)); // Scaled image
                        squares[row][col].add(pieceLabel, BorderLayout.CENTER);
                    }
                    pieceIndex++;
                }

                // Add the panel (square) to the GridLayout
                add(squares[row][col]);
            }
        }
    }

    // Method to update the board with sorted pieces
    private void updateBoard() {
        int pieceIndex = 0;
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                // Remove all components from the square
                squares[row][col].removeAll();

                // Check if there is a valid piece for this square
                if (pieceIndex < piecesArray.length) {
                    String imagePath = piecesArray[pieceIndex][0]; // Get the image path

                    // Add piece image if valid (non-empty)
                    if (!imagePath.equals("empty.png")) {
                        ImageIcon icon = new ImageIcon(imagePath); // Load the image
                        Image scaledImage = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                        JLabel pieceLabel = new JLabel(new ImageIcon(scaledImage)); // Scaled image
                        squares[row][col].add(pieceLabel, BorderLayout.CENTER);
                    }
                    pieceIndex++;
                }

                // Revalidate and repaint the square to update the UI
                squares[row][col].revalidate();
                squares[row][col].repaint();
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

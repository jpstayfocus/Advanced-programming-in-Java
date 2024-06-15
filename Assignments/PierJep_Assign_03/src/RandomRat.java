/**
 * Name: Jephte Pierre
 * Date: March 13, 2024
 * Description: RandomRat is a relatively smarter rat that uses a small amount of memory to remember its previous position.
 * It moves in random directions to cells that it has not visited before. If it reaches a dead-end, it goes back to
 * the previous cell and continues from there.
 */

import java.util.Random;

// Implementation of Animal interface representing a Random Rat

public class RandomRat implements Animal {
    private static final Random rnd = new Random();

    // Fields to track current and previous positions, start position, and number of moves

    private int startCol = 0;
    private int startRow = 0;
    private int currentCol = 0;
    private int currentRow = 0;
    private int previousCol = -1; // Initialize to invalid position
    private int previousRow = -1; // Initialize to invalid position
    private final String name = "RandomRat";
    private int numMoves = 0;

    // Constructor
    public RandomRat() {
    }

    // Getters for various properties
    @Override
    public int getRow() {
        return currentRow;
    }

    @Override
    public int getColumn() {
        return currentCol;
    }

    @Override
    public char getLetter() {
        return name.charAt(0);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getNumMoves() {
        return numMoves;
    }

    @Override
    public int getStartColumn() {
        return startCol;
    }

    @Override
    public int getStartRow() {
        return startRow;
    }

    // Method to move the rat in the maze
    @Override
    public void move(Maze maz) {
        boolean noMoveFound = true;

        while (noMoveFound) {
            int direction = rnd.nextInt(4); // 0: Up, 1: Right, 2: Down, 3: Left

            int nextRow = currentRow;
            int nextCol = currentCol;

            switch (direction) {
                case 0: // Up
                    nextRow--;
                    break;
                case 1: // Right
                    nextCol++;
                    break;
                case 2: // Down
                    nextRow++;
                    break;
                case 3: // Left
                    nextCol--;
                    break;
            }

            if (isValidMove(nextRow, nextCol, maz)) {
                currentRow = nextRow;
                currentCol = nextCol;
                previousRow = currentRow;
                previousCol = currentCol;
                noMoveFound = false;
            }

            numMoves++;
        }
    }

    // Method to check if a move is valid

    private boolean isValidMove(int row, int col, Maze maz) {
        if (row < 0 || row >= maz.getNumRows() || col < 0 || col >= maz.getNumColumns()) {
            return false; // Out of bounds
        }
        if (row == previousRow && col == previousCol) {
            return false; // Already visited this cell
        }
        return maz.getSquare(row, col) != Maze.WALL; // Wall in the way
// Valid move
    }

    // Method to reset the rat's position and move count
    @Override
    public void reset() {
        currentRow = startRow;
        currentCol = startCol;
        numMoves = 0;
        previousCol = -1;
        previousRow = -1;
    }

    // Setters for start position
    @Override
    public void setStartColumn(int col) {
        startCol = col;
    }

    @Override
    public void setStartRow(int row) {
        startRow = row;
    }
}

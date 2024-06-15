/**
 * Name: Jephte Pierre
 * Date: March 13, 2024
 * Represents a rat that moves through a maze using Depth-First Search algorithm.
 */

import java.util.Random;

// Implementation of Animal interface representing a Depth-First Search Rat
public class DepthFirstSearchRat implements Animal {


    // Constants to represent directions
    public static final Object UP = "Up";
    public static final Object DOWN = "Down";
    public static final Object LEFT = "Left";
    public static final Object RIGHT = "Right";

    // Fields to track current position, start position, name, number of moves
    private int startCol = 0;
    private int startRow = 0;
    private int currentCol = 0;
    private int currentRow = 0;
    private final String name = "DFS Rat";
    private int numMoves = 0;
    private final Random random = new Random();

    // Constructors
    public DepthFirstSearchRat() {}

    // Getter methods
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
    public int getRow() {
        return currentRow;
    }

    @Override
    public int getStartColumn() {
        return startCol;
    }

    @Override
    public int getStartRow() {
        return startRow;
    }

    // Setter methods
    @Override
    public void setStartColumn(int col) {
        startCol = col;
    }

    @Override
    public void setStartRow(int row) {
        startRow = row;
    }

    /**
     * Moves the rat through the maze using Depth-First Search algorithm.
     *
     * @param maze The maze in which the rat is moving.
     */
    @Override
    public void move(Maze maze) {
        if (maze == null) {
            return; // If maze is not initialized, do nothing
        }

        // Attempt to move in a depth-first search manner
        if (maze.canMove(currentRow + 1, currentCol)) {
            currentRow++;
        } else if (maze.canMove(currentRow, currentCol + 1)) {
            currentCol++;
        } else if (maze.canMove(currentRow - 1, currentCol)) {
            currentRow--;
        } else if (maze.canMove(currentRow, currentCol - 1)) {
            currentCol--;
        }

        numMoves++;
    }

    /**
     * Resets the rat's position and number of moves to their initial values.
     */
    @Override
    public void reset() {
        currentRow = startRow;
        currentCol = startCol;
        numMoves = 0;
    }
}

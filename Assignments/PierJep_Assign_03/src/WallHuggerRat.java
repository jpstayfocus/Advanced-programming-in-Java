/**
 * Name: Jephte Pierre
 * Date: March 13, 2024
 * Represents a rat that hugs the wall while navigating a maze.
 */

import java.util.Random;

// Implementation of Animal interface representing a Wall Hugger Rat
public class WallHuggerRat implements Animal {
    private static final Random rnd = new Random();

    // Fields to track current position, start position, name, number of moves, and orientation
    private int startCol = 0;
    private int startRow = 0;
    private int currentCol = 0;
    private int currentRow = 0;
    private final String name = "WallHuggerRat";
    private int numMoves = 0;
    private int orientation = 3; // Initial orientation: Right

    //Constructor

    public WallHuggerRat() {
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
            switch (orientation) {
                case 0: // Up
                    if (maz.canMove(currentRow, currentCol + 1)) {
                        currentCol++;
                        orientation = 3; //  orientation to Right
                        if (maz.getSquare(currentRow, currentCol) == Maze.FINISH) {
                            noMoveFound = false;
                        }
                    } else if (maz.canMove(currentRow - 1, currentCol)) {
                        currentRow--;
                        if (maz.getSquare(currentRow, currentCol) == Maze.FINISH) {
                            noMoveFound = false;
                        }
                    } else if (maz.canMove(currentRow, currentCol - 1)) {
                        currentCol--;
                        orientation = 1; //  orientation to Left
                        if (maz.getSquare(currentRow, currentCol) == Maze.FINISH) {
                            noMoveFound = false;
                        }
                    } else {
                        currentRow++;
                        orientation = 2; // orientation to Down
                        if (maz.getSquare(currentRow, currentCol) == Maze.FINISH) {
                            noMoveFound = false;
                        }
                    }
                    break;
                case 1: // Left
                    if (maz.canMove(currentRow - 1, currentCol)) {
                        currentRow--;
                        orientation = 0; //  orientation to Up
                        if (maz.getSquare(currentRow, currentCol) == Maze.FINISH) {
                            noMoveFound = false;
                        }
                    } else if (maz.canMove(currentRow, currentCol - 1)) {
                        currentCol--;
                        if (maz.getSquare(currentRow, currentCol) == Maze.FINISH) {
                            noMoveFound = false;
                        }
                    } else if (maz.canMove(currentRow + 1, currentCol)) {
                        currentRow++;
                        orientation = 2; //  orientation to Down
                        if (maz.getSquare(currentRow, currentCol) == Maze.FINISH) {
                            noMoveFound = false;
                        }
                    } else {
                        currentCol++;
                        orientation = 3; //  orientation to Right
                        if (maz.getSquare(currentRow, currentCol) == Maze.FINISH) {
                            noMoveFound = false;
                        }
                    }
                    break;
                case 2: // Down
                    if (maz.canMove(currentRow, currentCol - 1)) {
                        currentCol--;
                        orientation = 1; // orientation to Left
                        if (maz.getSquare(currentRow, currentCol) == Maze.FINISH) {
                            noMoveFound = false;
                        }
                    } else if (maz.canMove(currentRow + 1, currentCol)) {
                        currentRow++;
                        if (maz.getSquare(currentRow, currentCol) == Maze.FINISH) {
                            noMoveFound = false;
                        }
                    } else if (maz.canMove(currentRow, currentCol + 1)) {
                        currentCol++;
                        orientation = 3; //  orientation to Right
                        if (maz.getSquare(currentRow, currentCol) == Maze.FINISH) {
                            noMoveFound = false;
                        }
                    } else {
                        currentRow--;
                        orientation = 0; //  orientation to Up
                        if (maz.getSquare(currentRow, currentCol) == Maze.FINISH) {
                            noMoveFound = false;
                        }
                    }
                    break;
                case 3: // Right
                    if (maz.canMove(currentRow + 1, currentCol)) {
                        currentRow++;
                        orientation = 2; //  orientation to Down
                        if (maz.getSquare(currentRow, currentCol) == Maze.FINISH) {
                            noMoveFound = false;
                        }
                    } else if (maz.canMove(currentRow, currentCol + 1)) {
                        currentCol++;
                        if (maz.getSquare(currentRow, currentCol) == Maze.FINISH) {
                            noMoveFound = false;
                        }
                    } else if (maz.canMove(currentRow - 1, currentCol)) {
                        currentRow--;
                        orientation = 0; //  orientation to Up
                        if (maz.getSquare(currentRow, currentCol) == Maze.FINISH) {
                            noMoveFound = false;
                        }
                    } else {
                        currentCol--;
                        orientation = 1; //  orientation to Left
                        if (maz.getSquare(currentRow, currentCol) == Maze.FINISH) {
                            noMoveFound = false;
                        }
                    }
                    break;
            }
            numMoves++;
        }
    }

    // Method to reset the rat's position and move count
    @Override
    public void reset() {
        currentRow = startRow;
        currentCol = startCol;
        numMoves = 0;
        orientation = 3; // orientation to Right
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

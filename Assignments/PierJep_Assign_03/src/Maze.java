import java.io.*;
import java.util.*;


/** Represents a sewer maze through which rats can run. */
public class Maze extends Observable
{
  // STATIC CLASS CONSTANTS

  /** Constant for a square in the maze which is a wall; used with getSquare(int, int). */
  public static final char WALL   = 'X';
  /** Constant for a square in the maze which is the maze's starting point; used with getSquare(int, int). */
  public static final char START  = 'S';
  /** Constant for a square in the maze which is the maze's ending point; used with getSquare(int, int). */
  public static final char FINISH = 'F';
  /** Constant for a square in the maze which is empty; used with getSquare(int, int). */
  public static final char EMPTY  = ' ';

  // STATIC METHODS

  /** Creates a maze from the given file name (Factory pattern); used by GUI. */
  public static final Maze create(String filename) throws IOException
  {
    FileReader fr = new FileReader(filename);
    BufferedReader in = new BufferedReader(fr);

    int numRows = Integer.parseInt(in.readLine());
    int numCols = Integer.parseInt(in.readLine());
    char[][] squares = new char[numRows][numCols];
    Maze maz = new Maze(squares);

    // read in each line from file, store in array
    for (int rr = 0;  rr < numRows;  rr++)
    {
      for (int cc = 0;  cc < numCols;  cc++)
      {
        char square = (char)in.read();
        if (square == START)
        {
          maz.startRow = rr;
          maz.startCol = cc;
        }
        squares[rr][cc] = square;
      }

      // consume newline \n character
      in.readLine();
    }

    return maz;
  }


  // INSTANCE VARIABLES
  private char[][] squares;
  private int numRows, numCols;
  private int startRow, startCol;
  private Animal rat;

  /* Constructs a new maze with given squares; can't be called from outside. */
  private Maze(char[][] squares)
  {
    this.squares = squares;
    this.numRows = squares.length;
    this.numCols = squares[0].length;
  }

  /** Returns square in maze at given row/column index. */
  public char getSquare(int row, int col)
  {
    if (!(0 <= row && row < numRows
            && 0 <= col && col < numCols))
      throw new IndexOutOfBoundsException("Index is outside of maze: " + row + ", " + col);

    return squares[row][col];
  }

  /** Returns how many rows are in this maze. */
  public int getNumRows()
  {
    return numRows;
  }

  /** Returns how many columns are in this maze. */
  public int getNumColumns()
  {
    return numCols;
  }

  /** Returns the row at which the rat starts in this maze. */
  public int getStartRow()
  {
    return startRow;
  }

  /** Returns the column at which the rat starts in this maze. */
  public int getStartColumn()
  {
    return startCol;
  }

  /** Returns this maze's rat; used by GUI. */
  public Animal getRat()
  {
    return rat;
  }

  /** Sets this maze's rat to be the given rat; used by GUI. */
  public void setRat(Animal rat)
  {
    reset();
    this.rat = rat;
    rat.setStartRow(startRow);
    rat.setStartColumn(startCol);
    rat.reset();
  }

  /** Returns whether the given row/column is within the maze or not. */
  public boolean contains(int row, int col)
  {
    return 0 <= row  &&  row < numRows  &&
            0 <= col  &&  col < numCols;
  }

  /** Makes rat move one square; called by GUI. */
  public boolean update()
  {
    if (rat != null)
    {
      // move rat
      rat.move(this);
      setChanged();
      notifyObservers(null);
      return true;
    }

    return false;
  }

  /** Makes rat reset to original position; called by GUI. */
  public void reset()
  {
    if (rat != null)
      rat.reset();
  }

  /** Returns whether it would be legal for a rat to move to the given 
   *  row/column (e.g. not blocked by a wall).
   */
  public boolean canMove(int row, int col)
  {
    return contains(row, col)  &&  getSquare(row, col) != WALL;
  }

  /** Returns whether or not the rat has escaped the maze; called by GUI. */
  public boolean ratHasEscaped()
  {
    return rat != null  &&  getSquare(rat.getRow(), rat.getColumn()) == FINISH;
  }
}
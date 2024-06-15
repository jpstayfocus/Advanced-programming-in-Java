import java.util.Random;



/**    A *totally* dumb rat that randomly tries different positions
*      until it finds one to go to
*
*      This is just a badly designed demonstration rat
*      *DON'T* use this one in your assignment
*
*/

public class IdiotRat implements Animal {

   static Random rnd = new Random();

  // constructors
   public IdiotRat() {
   }

  int        startCol   = 0;
  int        startRow   = 0;
  int        currentCol = 0;
  int        currentRow = 0;
  String     name       = "NoName";
  int        numMoves   = 0;


  // returns current row animal is in
 public int getRow(){ return currentRow; }

  // returns current column animal is in
  public int getColumn() { return currentCol; }

  // returns one letter to represent animal
  public char getLetter() { return name.charAt(0); }

  // returns animal's name
  public String getName(){ return name; }

  // returns # moves animal has made in maze so far
  public int getNumMoves() { return numMoves;  }

  // returns column where animal started in maze
  public int getStartColumn(){  return startCol;  }

  // returns row where animal started in maze
  public int getStartRow(){ return startRow; }

  // asks animal to make a move in this maze. This is called by the Maze
  public void move(Maze maz){
     boolean noMoveFound = true;

       while ( noMoveFound)
         switch ( rnd.nextInt(4))
         {   case 0: if( maz.canMove(currentRow -1, currentCol))
                         {   currentRow--;                         //up
                             noMoveFound = false;
                         }
                         break;
             case 1: if( maz.canMove(currentRow , currentCol + 1))
                         {   currentCol++;                         //RIGHT
                             noMoveFound = false;
                         }
                         break;
             case 2: if( maz.canMove(currentRow + 1 , currentCol ))
                         {   currentRow++;                         //DOWN
                             noMoveFound = false;
                         }
                         break;
             case 3: if( maz.canMove(currentRow  , currentCol - 1))
                         {   currentCol--;                         //Left
                             noMoveFound = false;
                         }
                         break;

         }

     numMoves++;
   }

  // moves animal back to starting row/column, wipes # moves to 0
  public void reset() {
    currentRow = startRow;
    currentCol = startCol;
    numMoves = 0;
  }

    // sets column where animal started in maze
  public void setStartColumn(int col) { startCol = col; }

  // sets row where animal started in maze
  public void setStartRow(int row){ startRow = row; }


}

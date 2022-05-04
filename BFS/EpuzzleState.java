import java.util.*;

public class EpuzzleState extends SearchState{
    
/**
* EpuzzleState.java
* State in 8 piece puzzle problem
* Oliver Spacey (ohmspacey1@sheffield.ac.uk) 2022 version
*/



private int[][] puzzle;

  /**
   * constructor
   * 
   * @param puzzle - the complete puzzle
   */

  public EpuzzleState (int[][] puzzlec) {
      puzzle = puzzlec;
  }

  /**
   * accessor for current puzzle state
   */

  public int[][] get_puzzle() {
    return puzzle;
  };



  /**
   * goalPredicate
   * 
   * @param searcher - the current search
   */

  public boolean goalPredicate(Search searcher) {
    EpuzzleSearch eSearcher = (EpuzzleSearch) searcher;
    int[][] tar = eSearcher.getTarget(); // get target state


    for(int i=0; i<tar.length; i++){
      for(int j=0;j<tar[i].length;j++){
        if (puzzle[i][j] != tar[i][j]){
          return false;
        }
      }
    }

    return true;
  }

  /**
   * getSuccessors - uses the current puzzle state to find all legal moves and add them to a list, which is returned.
   * 
   * @param searcher - the current search
   * @return - returns list of possible states from the current position.
   */

  public ArrayList<SearchState> getSuccessors(Search searcher) {
    EpuzzleSearch eSearcher = (EpuzzleSearch) searcher;
    int[][] current = eSearcher.getCurrent();

    int col = 0;//col and row coordinates for the blank space in the puzzle.
    int row = 0;

    ArrayList<EpuzzleState> eslis = new ArrayList<EpuzzleState>(); // the list of puzzle states
    ArrayList<SearchState> slis = new ArrayList<SearchState>();


    //find index of 0 in current position

    for(int i=0; i<puzzle.length; i++){
      for(int j=0; j<puzzle[i].length; j++){
        if (puzzle[i][j] == 0){
          col = j;
          row = i;
        }
      }
    }

    System.out.println("0 is in position: "+col+","+row+"\n");


    //If statements for adding states to the possible state list 'eslis':

    if (row != 0)
      eslis.add(movePieceUp(puzzle, col, row));
    if (row != 2)
      eslis.add(movePieceDown(puzzle, col, row));
    if (col != 0)
      eslis.add(movePieceLeft(puzzle, col, row));
    if (col != 2)
      eslis.add(movePieceRight(puzzle, col, row));


    // cast the puzzles states to SearchState

    for (EpuzzleState es : eslis)
      slis.add((SearchState) es);

    return slis;

  }

   /**
   * sameState - compares two puzzle states and determines similarity - i.e. are they identical or not
   * 
   * @param s2 - The state of the puzzle provided which is required to verify whether it is identical to another puzzle.
   * @return - A boolean depending on whether the puzzles are the same or not.
   */

  public boolean sameState(SearchState s2) {
    EpuzzleState es2 = (EpuzzleState) s2;
    int[][] state2 = es2.get_puzzle();

    for(int i=0; i<state2.length; i++){
      for(int j=0;j<state2[i].length;j++){
        if (puzzle[i][j] != state2[i][j]){
          return false;
        }
      }
    }

    return true;
  }

  /**
   * toString
   */
  public String toString() {
    return "\n|"+puzzle[0][0]+" "+puzzle[0][1]+" "+puzzle[0][2]+"|\n|"+puzzle[1][0]+" "+puzzle[1][1]+" "+puzzle[1][2]+"|\n|"+puzzle[2][0]+" "+puzzle[2][1]+" "+puzzle[2][2]+"|\n";
  }

  /**
   * movePieceUp, movePieceDown, movePieceLeft, movePieceRight - All methods work by manipulating the position of the blank space in the direction indicated by their name.
   * 
   * @param puzzle_ - the current puzzle state is provided so it can be copied and altered.
   * @param col - The column in which the blank space is located.
   * @param row - The row in which the blank space is located.
   * @return - The puzzle with the blank space moved in the direction indicated by the method name (up, down, left, or right).
   */

  private EpuzzleState movePieceUp(int[][] puzzle_, int col, int row){
    int[][] puzzleCopy = copyPuzzle(puzzle_);

    //col and row are the co-ordinates for the blank space.

    int temp = puzzleCopy[row-1][col];
    puzzleCopy[row-1][col] = puzzleCopy[row][col];
    puzzleCopy[row][col] = temp;

    return new EpuzzleState(puzzleCopy);
  }


  private EpuzzleState movePieceDown(int[][] puzzle_, int col, int row){
    int[][] puzzleCopy = copyPuzzle(puzzle_);

    //col and row are the co-ordinates for the blank space.
  
    int temp = puzzleCopy[row+1][col];
    puzzleCopy[row+1][col] = puzzleCopy[row][col];
    puzzleCopy[row][col] = temp;

    return new EpuzzleState(puzzleCopy);
  }


  private EpuzzleState movePieceLeft(int[][] puzzle_, int col, int row){
    int[][] puzzleCopy = copyPuzzle(puzzle_);

    //col and row are the co-ordinates for the blank space.
  
    int temp = puzzleCopy[row][col-1];
    puzzleCopy[row][col-1] = puzzleCopy[row][col];
    puzzleCopy[row][col] = temp;

    return new EpuzzleState(puzzleCopy);
  }


  private EpuzzleState movePieceRight(int[][] puzzle_, int col, int row){
    int[][] puzzleCopy = copyPuzzle(puzzle_);

    //col and row are the co-ordinates for the blank space.
  
    int temp = puzzleCopy[row][col+1];
    puzzleCopy[row][col+1] = puzzleCopy[row][col];
    puzzleCopy[row][col] = temp;

    return new EpuzzleState(puzzleCopy);
  }



   /**
   * copyPuzzle - Creates an exact copy of the puzzle provided as a parameter.
   * 
   * @param puzzle_ - The current state of the puzzle.
   * @return - An identical copy of the puzzle, which can be altered without affecting the original.
   */

  public int[][] copyPuzzle(int[][] puzzle_){
    int[][] puzzlecopy = new int[3][3];
    for(int i=0; i<puzzle_.length; i++){
      for(int j=0; j<puzzle_[i].length; j++){
        puzzlecopy[i][j] = puzzle_[i][j];
      }
    }

    return puzzlecopy;
  }

/**
 * hamming - Utilises hamming to calculate an estimated distance until puzzle is solved.
 * 
 * @param searcher - Used to obtain the target state so that the number of out-of-place tiles can be summated.
 * @param puzzle_ - The current state of the puzzle, which is also required to compare the number of out-of-place tiles.
 * @return - The total number of tiles that are not in the current position is counted and returned.
 */

  public int hamming(Search searcher, int[][] puzzle_){
    int count = 0;
    EpuzzleSearch eSearcher = (EpuzzleSearch) searcher;
    int[][] target = eSearcher.getTarget();
    for(int i = 0; i<puzzle_.length; i++){
      for (int j = 0; j<puzzle_[i].length; j++){
        if ((puzzle_[i][j] != target[i][j]) && (puzzle_[i][j] != 0)){
          count+=1;
        }
      }
    }
    return count;
  }

}


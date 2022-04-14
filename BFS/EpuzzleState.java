import java.util.*;

public class EpuzzleState extends SearchState{
    
/**
* JugsState.java
* State in a jugs problem
* Phil Green 2013 version
* Heidi Christensen (heidi.christensen@sheffield.ac.uk) 2021 version
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
   * accessor for content of jug1
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
   * getSuccessors
   * 
   * @param searcher - the current search
   */

  public ArrayList<SearchState> getSuccessors(Search searcher) {
    EpuzzleSearch eSearcher = (EpuzzleSearch) searcher;
    int[][] current = eSearcher.getCurrent();

    int col = 0;//col and row coordinates for the blank space in the puzzle.
    int row = 0;
    boolean canMoveLeft = true;
    boolean canMoveRight = true;
    boolean canMoveUp = true;
    boolean canMoveDown = true;
    int[][] newState;

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


    //If statements:

    if (row != 0)
      eslis.add(movePieceUp(puzzle, col, row));
    if (row != 2)
      eslis.add(movePieceDown(puzzle, col, row));
    if (col != 0)
      eslis.add(movePieceLeft(puzzle, col, row));
    if (col != 2)
      eslis.add(movePieceRight(puzzle, col, row));


    // cast the jugs states as search states in slis

    for (EpuzzleState es : eslis)
      slis.add((SearchState) es);

    return slis;

  }

  /**
   * sameState - do 2 JugsSearchNodes have the same state?
   * 
   * @param s2 second state
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



  private EpuzzleState movePieceUp(int[][] puzzle_, int col, int row){
    int[][] puzzleCopy = copyPuzzle(puzzle_);

    //col and row are the co-ordinates for the blank space.

    int temp = puzzleCopy[row-1][col];
    puzzleCopy[row-1][col] = puzzleCopy[row][col];
    puzzleCopy[row][col] = temp;

    //System.out.println("Moving up:\n"+"|"+puzzleCopy[0][0]+" "+puzzleCopy[0][1]+" "+puzzleCopy[0][2]+"|\n|"+puzzleCopy[1][0]+" "+puzzleCopy[1][1]+" "+puzzleCopy[1][2]+"|\n|"+puzzleCopy[2][0]+" "+puzzleCopy[2][1]+" "+puzzleCopy[2][2]+"|\n");    
    return new EpuzzleState(puzzleCopy);
  }



  private EpuzzleState movePieceDown(int[][] puzzle, int col, int row){
    int[][] puzzleCopy = copyPuzzle(puzzle);

    //col and row are the co-ordinates for the blank space.
  
    int temp = puzzleCopy[row+1][col];
    puzzleCopy[row+1][col] = puzzleCopy[row][col];
    puzzleCopy[row][col] = temp;

    //System.out.println("Moving down:\n"+"|"+puzzleCopy[0][0]+" "+puzzleCopy[0][1]+" "+puzzleCopy[0][2]+"|\n|"+puzzleCopy[1][0]+" "+puzzleCopy[1][1]+" "+puzzleCopy[1][2]+"|\n|"+puzzleCopy[2][0]+" "+puzzleCopy[2][1]+" "+puzzleCopy[2][2]+"|\n");

    return new EpuzzleState(puzzleCopy);
  }


  private EpuzzleState movePieceLeft(int[][] puzzle, int col, int row){
    int[][] puzzleCopy = copyPuzzle(puzzle);

    //col and row are the co-ordinates for the blank space.
  
    int temp = puzzleCopy[row][col-1];
    puzzleCopy[row][col-1] = puzzleCopy[row][col];
    puzzleCopy[row][col] = temp;

    //System.out.println("Moving left:\n"+"|"+puzzleCopy[0][0]+" "+puzzleCopy[0][1]+" "+puzzleCopy[0][2]+"|\n|"+puzzleCopy[1][0]+" "+puzzleCopy[1][1]+" "+puzzleCopy[1][2]+"|\n|"+puzzleCopy[2][0]+" "+puzzleCopy[2][1]+" "+puzzleCopy[2][2]+"|\n");    
    return new EpuzzleState(puzzleCopy);
  }

  private EpuzzleState movePieceRight(int[][] puzzle, int col, int row){
    int[][] puzzleCopy = copyPuzzle(puzzle);

    //col and row are the co-ordinates for the blank space.
  
    int temp = puzzleCopy[row][col+1];
    puzzleCopy[row][col+1] = puzzleCopy[row][col];
    puzzleCopy[row][col] = temp;

    //System.out.println("Moving right:\n"+"|"+puzzleCopy[0][0]+" "+puzzleCopy[0][1]+" "+puzzleCopy[0][2]+"|\n|"+puzzleCopy[1][0]+" "+puzzleCopy[1][1]+" "+puzzleCopy[1][2]+"|\n|"+puzzleCopy[2][0]+" "+puzzleCopy[2][1]+" "+puzzleCopy[2][2]+"|\n");
    return new EpuzzleState(puzzleCopy);
  }


  public int[][] copyPuzzle(int[][] puzzle_){
    int[][] puzzle = new int[3][3];
    for(int i=0; i<puzzle_.length; i++){
      for(int j=0; j<puzzle_[i].length; j++){
        puzzle[i][j] = puzzle_[i][j];
      }
    }

    return puzzle;
  }


}


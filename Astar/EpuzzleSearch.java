
import java.util.*;

public class EpuzzleSearch extends Search {


    private int[][] target; //target
    private int[][] current; //current

    /** constructor takes current and target states.
    * @param cur current state of the puzzle
    * @param tar target state to be reached
    */

    public  EpuzzleSearch (int[][] cur, int[][] tar) {

	    current = cur;
	    target = tar;

    }



    /**
    * accessor for target
    */

     public int[][] getTarget(){
	return target;
    }

    /**
     * Accessor for current puzzle state
     */
    public int[][] getCurrent(){
        return current;
    }
}
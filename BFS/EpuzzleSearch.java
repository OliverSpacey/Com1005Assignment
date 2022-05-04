
import java.util.*;

public class EpuzzleSearch extends Search {


    private int[][] target; //target
    private int[][] current; //current

    /** constructor  takes target and current puzzle states
    * @param cur current state of the puzzle
    * @param tar target amount to be measured
    */

    public  EpuzzleSearch (int[][] cur, int[][] tar) {

	    current = cur;
	    target = tar;

    }

    /**
    * accessors for target and current puzzle state
    */

     public int[][] getTarget(){
	return target;
    }

    public int[][] getCurrent(){
        return current;
    }
}
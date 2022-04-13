
import java.util.*;

public class EpuzzleSearch extends Search {


    private int[][] target; //target
    private int[][] current; //current

    /** constructor  takes jug capacities and target
    * @param cur current state of the puzzle
    * @param tar target amount to be measured
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

    public int[][] getCurrent(){
        return current;
    }
}
import java.util.*;

public class RunEpuzzleBFS {

  public static void main(String[] arg) {

    //p1
    int[][] start = {{1,0,3},
                     {4,2,6},
                     {7,5,8}};
    
    //p2
    //    int[][] start = {{4,1,3},
    //                     {7,2,5},
    //                     {0,8,6}};

    //p3
    //    int[][] start = {{2,3,6},
    //                     {1,5,8},
    //                     {4,7,0}};


    int[][] goal = {{1,2,3},
                    {4,5,6},
                    {7,8,0}};

    //SearchState initState = (SearchState) new SearchState();
    EpuzzleSearch searcher = new EpuzzleSearch(start, goal);
    SearchState initState = (SearchState) new EpuzzleState(start);

    // change from search1 - specify strategy
    String resb = searcher.runSearch(initState, "breadthFirst");
    // String resd = searcher.runSearch(initState, "depthFirst");
    System.out.println(resb);

  }
}


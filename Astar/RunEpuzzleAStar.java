public class RunEpuzzleAStar {
    public static void main(String[] args) {

        //p1
        //int[][] start = {{1,0,3},{4,2,6},{7,5,8}};
        
        //p2
        //int[][] start = {{4,1,3},{7,2,5},{0,8,6}};
    
        //p3
        //int[][] start = {{2,3,6},{1,5,8},{4,7,0}};
    
        // generator given seed
        //int seed = 4321;
        
        //int d = 6;
        // generate puzzle providing difficulty


        int d = Integer.parseInt(args[1]);
        int seed = Integer.parseInt(args[2]);
        EpuzzGen gen = new EpuzzGen(seed);
        
        int[][] start = gen.puzzGen(d);
    
        int[][] goal = {{1,2,3},{4,5,6},{7,8,0}};

        String engine = args[0];
    
        //SearchState initState = (SearchState) new SearchState();
        EpuzzleSearch searcher = new EpuzzleSearch(start, goal);
        SearchState initState = (SearchState) new EpuzzleState(start, 1, 2);
    
        // change from search1 - specify strategy
        //String resb = searcher.runSearch(initState, "AStar");
        //String resb = searcher.runSearch(initState, "breadthFirst");
        // String resd = searcher.runSearch(initState, "depthFirst");
        String resb = searcher.runSearch(initState, engine);
        System.out.println(resb);
    
      }
}

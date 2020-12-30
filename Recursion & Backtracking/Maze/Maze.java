package Maze;
import java.util.*;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
//Shreya Vhadadi(10453495)
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int x, int y) {
        // COMPLETE HERE FOR PROBLEM 1
        if (x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows())
              return false; // Cell is out of bounds
        else if (!maze.getColor(x, y).equals(NON_BACKGROUND))
              return false; // Cell is not on path
        else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) 
        {
              maze.recolor(x, y, PATH); // Cell is on path
              return true; 
        }
        else 
        { // Recursive case to check each neighbor
              maze.recolor(x, y, PATH);
                if (findMazePath(x - 1, y)
                    || findMazePath(x + 1, y)
                    || findMazePath(x, y - 1)
                    || findMazePath(x, y + 1)) 
                {
                  return true;
                }
                else 
                {
                  maze.recolor(x, y, TEMPORARY); // Dead end
                  return false;
                } 
         }

    }

    // ADD METHOD FOR PROBLEM 2 HERE
    public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace)
    {
        if (x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows() || !maze.getColor(x, y).equals(NON_BACKGROUND))
            return; 
        else if(x == maze.getNCols() - 1 && y == maze.getNRows() - 1) 
        {  	
            trace.push(new PairInt(x, y));
            ArrayList<PairInt> currentTrace = new ArrayList<>(trace);
            result.add(currentTrace);
            trace.pop();
            maze.recolor(x, y, NON_BACKGROUND);
            return;
        }
        else 
        {
        	trace.push(new PairInt(x,y));
            maze.recolor(x, y, PATH);
            findMazePathStackBased(x+1,y,result,trace);
            findMazePathStackBased(x-1,y,result,trace);
            findMazePathStackBased(x,y+1,result,trace);
            findMazePathStackBased(x,y-1,result,trace);
            maze.recolor(x, y, NON_BACKGROUND);
            trace.pop();
            return;
        }
    
    }
    
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y){
		    ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
			Stack<PairInt> trace = new Stack<>();
			findMazePathStackBased (0 ,0 , result , trace );
			return result ;
    }
    
    // ADD METHOD FOR PROBLEM 3 HERE
    public ArrayList<PairInt> findMazePathMin(int x, int y){
    	ArrayList <ArrayList<PairInt>> allPaths = findAllMazePaths(x, y);
    	ArrayList<PairInt> curr = new ArrayList<>();
    	ArrayList<PairInt> minPath = new ArrayList<>();   	
    	int index=0;
    	int[] min = new int[allPaths.size()];
    	int minimum;
    	
    	for(int i=0 ; i<allPaths.size() ; i++)
    	{
    		curr = allPaths.get(i);
    		min[i] = curr.size();
    	}
    	
    	minimum=min[0];
    	
    	for(int j=1 ; j<min.length ; j++)
    	{
    		if(min[j] < minimum)
    		{
    			  minimum = min[j];
    			  index = j;
    		}
    	}
    	
    	minPath = allPaths.get(index);
    	return minPath;    	   	
    }
    

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/


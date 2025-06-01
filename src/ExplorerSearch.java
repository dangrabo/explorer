import java.util.ArrayList;
import java.util.List;

public class ExplorerSearch {

    /**
     * Returns how much land area an explorer can reach on a rectangular island.
     * 
     * The island is represented by a rectangular int[][] that contains
     * ONLY the following nunbers:
     * 
     * '0': represents the starting location of the explorer
     * '1': represents a field the explorer can walk through
     * '2': represents a body of water the explorer cannot cross
     * '3': represents a mountain the explorer cannot cross
     * 
     * The explorer can move one square at a time: up, down, left, or right.
     * They CANNOT move diagonally.
     * They CANNOT move off the edge of the island.
     * They CANNOT move onto a a body of water or mountain.
     * 
     * This method should return the total number of spaces the explorer is able
     * to reach from their starting location. It should include the starting
     * location of the explorer.
     * 
     * For example
     * 
     * @param island the locations on the island
     * @return the number of spaces the explorer can reach
     */
    public static int reachableArea(int[][] island) {
        int[] start = explorerLocation(island);
        boolean[][] visited = new boolean[island.length][island[0].length];
        return reachableArea(island, start, visited);
    }

    //overloaded reachableArea
    public static int reachableArea(int[][] island, int[] current, boolean[][] visited) {
        int curR = current[0];
        int curC = current[1];

        if(visited[curR][curC]) return 0;

        visited[curR][curC] = true;

        List<int[]> moves = possibleMoves(island, current);

        int total = 1;
        for (int[] move : moves) total += reachableArea(island, move, visited);

        return total;

    }

    //location
    public static int[] explorerLocation(int[][] island) {
        for (int r = 0; r < island.length; r++) {
            for (int c = 0; c < island[r].length; c++) {
                if (island[r][c] == 0) return new int[] {r, c};
            }
        }
        throw new IllegalArgumentException("No explorer present");
    }
    //possibleMoves
    public static List<int[]> possibleMoves(int[][] island, int[] current) {
        List<int[]> moves = new ArrayList<>();

        int curR = current[0];
        int curC = current[1];

        //up
        int newR = curR - 1;
        int newC = curC;
        if (newR >= 0 && island[newR][newC] == 1) moves.add(new int[] {newR, newC});

        //down
        newR = curR + 1;
        newC = curC;
        if (newR < island.length && island[newR][newC] == 1) moves.add(new int[] {newR, newC});

        //left
        newR = curR;
        newC = curC - 1;
        if (newC >= 0 && island[newR][newC] == 1) moves.add(new int[] {newR, newC});

        //right
        newR = curR;
        newC = curC + 1;
        if (newC < island[newR].length && island[newR][newC] == 1) moves.add(new int[] {newR, newC});

        return moves;
    }
}


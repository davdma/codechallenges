/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codechallenges;
import java.util.*;

/**
 *
 * @author david
 */
public class Battleship {
    
    // verifies if battleship placements on 10x10 board are valid
    public static boolean fieldValidator(int[][] field) {
        // iterate over all cells until reaching an occupied cell
        int submarine = 0;
        int destroyer = 0;
        int cruiser = 0;
        int battleship = 0;
        for (int j = 0; j < field[0].length; j++) {
            for (int i = 0; i < field.length; i++) {
                if (field[j][i] == 1) {
                    switch(shipValidator(field, i, j)) {
                        case 0:
                            return false;
                        case 1:
                            submarine++;
                            break;
                        case 2:
                            destroyer++;
                            break;
                        case 3:
                            cruiser++;
                            break;
                        case 4:
                            battleship++;
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        
        // check if the number of each ship type is correct
        if (battleship == 1 && cruiser == 2 && destroyer == 3 && submarine == 4) {
            return true;
        } else {
            return false;
        }
    }
    
    // check to see if shape of ship is valid, via BFS
    // make ship in field unavailable afterwards (substitute -1 during traversal)
    public static int shipValidator(int[][] field, int x, int y) {
        // location of cell in 10x10 can be stored via one number
        Queue<Integer> q = new LinkedList<>();
        q.add(vertexIndex(x, y, field[0].length));
        field[y][x] = -1;
        
        int dir = -1; // only allow directions in same line (Left Right, Up Down)
        int length = 1;
        // N = 0, S = 2, E = 1, W = 3
        while (!q.isEmpty()) {
            int current = q.remove();
            int row = current / field[0].length;
            int col = current % field[0].length;
            
            // check in all eight directions to add to queue
            // if any in corners, immediately return 0
            
            // N
            if (row > 0) {
                if (field[row-1][col] == 1) {
                    q.add(vertexIndex(col, row-1, field[0].length));
                    field[row-1][col] = -1;
                    length++;
                    // check if direction changed
                    if (dir != -1 && dir % 2 != 0) {
                        // direction changed = bad case
                        return 0;
                    }
                    dir = 0;
                }
                // NW
                if (col > 0) {
                    if (field[row-1][col-1] == 1) {
                        return 0;
                    }
                }
                // NE
                if (col < field[0].length - 1) {
                    if (field[row-1][col+1] == 1) {
                        return 0;
                    }
                }
            }
            // S
            if (row < field.length - 1) {
                if (field[row+1][col] == 1) {
                    q.add(vertexIndex(col, row+1, field[0].length));
                    field[row+1][col] = -1;
                    length++;
                    // check if direction changed
                    if (dir != -1 && dir % 2 != 0) {
                        // direction changed = bad case
                        return 0;
                    }
                    dir = 2;
                }
                // SW
                if (col > 0) {
                    if (field[row+1][col-1] == 1) {
                        return 0;
                    }
                }
                // SE
                if (col < field[0].length - 1) {
                    if (field[row+1][col+1] == 1) {
                        return 0;
                    }
                }
            }
            // E
            if (col < field[0].length - 1) {
                if (field[row][col+1] == 1) {
                    q.add(vertexIndex(col+1, row, field[0].length));
                    field[row][col+1] = -1;
                    length++;
                    // check if direction changed
                    if (dir != -1 && dir % 2 != 1) {
                        // direction changed = bad case
                        return 0;
                    }
                    dir = 1;
                }
            }
            // W
            if (col > 0) {
                if (field[row][col-1] == 1) {
                    q.add(vertexIndex(col-1, row, field[0].length));
                    field[row][col-1] = -1;
                    length++;
                    // check if direction changed
                    if (dir != -1 && dir % 2 != 1) {
                        // direction changed = bad case
                        return 0;
                    }
                    dir = 3;
                }
            }
            
        }
        // 0 - not a valid battleship (disqualify if length > 4, turns)
        // 1 - submarine
        // 2 - destroyer
        // 3 - cruiser
        // 4 - battleship
        
        return length;
    }
    // takes position in maze and returns index in predecessor array
    public static int vertexIndex(int x, int y, int width) {
        return y * width + x;
    }
}

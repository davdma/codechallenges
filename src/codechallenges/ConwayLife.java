/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codechallenges;

/**
 *
 * @author david
 */
public class ConwayLife {
    
    // Conway's Game of Life 
    public static int[][] getGeneration(int[][] cells, int generations){
        // given the size of generations, generate largest needed
        // array to fit the cells as they evolve
        // each generation  = +1 width and length of array
        
        // note: number of rows then columns
        int[][] expandedCells = new int[cells.length + 2*generations][cells[0].length + 2*generations];
        
        // center original cells into expanded grid (input)
        for (int i = generations; i < cells.length + generations; i++) {
            for (int j = generations; j < cells[0].length + generations; j++) {
                expandedCells[i][j] = cells[i - generations][j - generations];
            }
        }
        
        // iterate over n generations
        for (int gen = 0; gen < generations ; gen++) {
            expandedCells = nextGen(expandedCells);
        }
        
        // crop final result
        return cropCells(expandedCells);
    }
    
    public static int[][] nextGen(int[][] cells) {
        // go through the steps of one generation of Conway's game of life
        // simply sum up the number of 1s (live cells) in Moore's neighborhood
        int[][] nextGen = new int[cells.length][cells[0].length];
        int neighbors;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                // sum neighborhood
                neighbors = sumNeighbors(cells, i, j);
                if (neighbors < 2 || neighbors > 3) {
                    // guaranteed dead cell condition
                    nextGen[i][j] = 0;
                } else if (cells[i][j] == 1 && (neighbors == 2 || neighbors == 3)) {
                    // surviving live cell condition
                    nextGen[i][j] = 1;
                } else if (cells[i][j] == 0 && neighbors == 3) {
                    // reviving dead cell condition
                    nextGen[i][j] = 1;
                }
            }
        }
        return nextGen;
    }
    
    public static int sumNeighbors(int[][] cells, int i, int j) {
        int sum = 0;
       
        for (int a = -1; a < 2; a++) {
            for (int b = -1; b < 2; b++) {
                // skip center cell
                if (i+a == i && j+b == j) {
                    continue;
                }
                // boundary checker
                if (i+a >= 0 && j+b >= 0 && i+a < cells.length && j+b < cells[0].length) {
                    sum += cells[i+a][j+b];
                }
            }

        }
        return sum;
        
    }
    
    // crop out empty rows and columns from ends of array of arrays
    public static int[][] cropCells(int[][] cells) {
        
        int row_s = -1;
        int row_e = -1;
        int col_s = -1;
        int col_e = -1;
        
        // find start of row
        out1:
            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[0].length; j++) {
                    if (cells[i][j] == 1) {
                        row_s = i;
                        break out1;
                    }
                }
            }
        
        // find end of row
        out2:
            for (int i = cells.length - 1; i >= 0; i--) {
                for (int j = 0; j < cells[0].length; j++) {
                    if (cells[i][j] == 1) {
                        row_e = i;
                        break out2;
                    }
                }
            }
        
        // find start of column
        out3:
            for (int i = 0; i < cells[0].length; i++) {
                for (int j = 0; j < cells.length; j++) {
                    if (cells[j][i] == 1) {
                        col_s = i;
                        break out3;
                    }
                }
            }
        
        // find end of column
        out4:
            for (int i = cells[0].length - 1; i >= 0; i--) {
                for (int j = 0; j < cells.length; j++) {
                    if (cells[j][i] == 1) {
                        col_e = i;
                        break out4;
                    }
                }
            }
        
        // empty array edge case
        if (row_s + row_e + col_s + col_e == -4) {
            return null;
        }

        // resize array to leave out empty columns and rows on the edges
        int[][] cropped = new int[row_e - row_s + 1][col_e - col_s + 1];
        
        // fill newly cropped array
        for (int i = 0; i < cropped.length; i++) {
            for (int j = 0; j < cropped[0].length; j++) {
                cropped[i][j] = cells[row_s + i][col_s + j];
            }
        }
        return cropped;
    }
}

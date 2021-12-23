/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package codechallenges;
import java.util.*;

/**
 *
 * @author david
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Challenge: https://www.codewars.com/kata/58905bfa1decb981da00009e
        // Test Elevator
        final int[][] queues = {
            new int[]{8, 8, 6}, // G
            new int[]{8, 3, 4, 7}, // 1
            new int[0], // 2
            new int[]{2, 6, 8, 5}, // 3
            new int[0], // 4
            new int[]{0, 8, 8, 4, 1}, // 5
            new int[0], // 6
            new int []{3, 2, 2, 3}, // 7
            new int[0] // 8
        };
        final int[] result = Elevator.theLift(queues, 9);
        System.out.println(Arrays.toString(result));
        
        // Challenge: https://www.codewars.com/kata/56a1c63f3bc6827e13000006
        // Test SmallerThan
        int[] small = SmallerThan.smaller(new int[] {5, 4, 7, 9, 2, 4, 4, 5, 6});
        System.out.println(Arrays.toString(small));
        
        // Challenge: https://www.codewars.com/kata/55b7bb74a0256d4467000070
        // Test ProperFractions
        long x = ProperFractions.properFractions(25L);
        System.out.println(x);
        
        // Challenge: https://www.codewars.com/kata/540d0fdd3b6532e5c3000b5b
        // Test BinomialExpansion
        String m = BinomialExpansion.expand("(x+1)^2");
        System.out.println(m);
        
        // Challenge: https://www.codewars.com/kata/52bb6539a4cf1b12d90005b7
        // Test Battleship
        int[][] battleField = {{1, 0, 0, 0, 0, 1, 1, 0, 0, 0},
                               {1, 0, 1, 0, 0, 0, 0, 0, 1, 0},
                               {1, 0, 1, 0, 1, 1, 1, 0, 1, 0},
                               {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                               {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                               {0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                               {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                               {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                               {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                               {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        System.out.println(Battleship.fieldValidator(battleField));
        
        // Challenge: https://www.codewars.com/kata/52423db9add6f6fc39000354
        // Test ConwayLife
        int[][] gliders = {{1, 0, 1}, {1, 1, 1}, {0, 1, 0}};
        int[][] conway = ConwayLife.getGeneration(gliders, 2);
        
        // Challenge: https://www.codewars.com/kata/51e056fe544cf36c410000fb
        // Test TopWords
        List<String> lst = TopWords.top3("uy'mr ezoay\n" + "ezoay");
        System.out.println(lst.get(0));
    }
    
}

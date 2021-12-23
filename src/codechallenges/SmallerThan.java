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
public class SmallerThan {
    
    // returns array with value at index i = number of elements
    // to the right of arr[i] smaller than arr[i]
    public static int[] smaller(int[] arr) {
        int[] smallerThan = new int[arr.length];
        
        // fill default index positions into separate array
        int[] pos = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            pos[i] = i;
        }
        
        // merge sort and count
        divideAndConquer(0 , arr.length - 1, arr, smallerThan, pos);
        return smallerThan;
    }
    
    public static void divideAndConquer(int p, int r, int[] arr, int[] smallerThan, int[] pos) {
        // divide-conquer-merge if subarray bigger than size one
        // if size one already solved (no action needed)
        if (p < r) {
            int q = (p + r) / 2;
            divideAndConquer(p, q, arr, smallerThan, pos);
            divideAndConquer(q+1, r, arr, smallerThan, pos);
            mergeCount(p, q, r, arr, smallerThan, pos);
        }
    }
   
    // O(n) merging of two subarrays while simultaneously counting inversions
    // to do it in O(n) the trick is to write in numbers from two arrays
    // starting from the largest
    public static void mergeCount(int p, int q, int r, int[] arr, int[] smallerThan, int[] pos) {
        int[] lowPos = Arrays.copyOfRange(pos, p, q+1);
        int[] highPos = Arrays.copyOfRange(pos, q+1, r+1);
        int[] lowHalf = Arrays.copyOfRange(arr, p, q+1);
        int[] highHalf = Arrays.copyOfRange(arr, q+1, r+1);
        
        int i = lowHalf.length - 1;
        int j = highHalf.length - 1;
        int k = r;
        // while both lists not empty
        while (i >= 0 && j >= 0) {
            if (highHalf[j] < lowHalf[i]) {
                arr[k] = lowHalf[i];
                pos[k] = lowPos[i];
                smallerThan[lowPos[i]] += j + 1; // trick
                i--;
            } else {
                arr[k] = highHalf[j];
                pos[k] = highPos[j];
                j--;
            }
            k--;
        }
        // when one list has been emptied append the remaining list
        if (i == -1) {
            for (; j >= 0; j--) {
                arr[k] = highHalf[j];
                pos[k] = highPos[j];
                k--;
            }
        } else if (j == -1) {
            // inversions can occur here as well since all are less
            // than last element in j
            for (; i >= 0; i--) {
                arr[k] = lowHalf[i];
                pos[k] = lowPos[i];
                k--;
            }
        }
        
        // positions: you have info of new assigned index in k
        // you ahve info of old assigned index in i, j
        // need to assign new position every time overwrite
    }
}

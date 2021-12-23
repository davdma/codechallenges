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
public class ProperFractions {
    
    // calculate number of possible proper fractions from denominator n
    public static long properFractions(long n) {
        // trivial case:
        if (n == 1) {
            return 0;
        }
        
        // USE EULER'S TOTIENT FUNCTION:
        // factorize then calculate (up to root n)
        long result = 1;  
        long pow = 0;
        
        // factor n (via successively dividing out primes)
        for (long p = 2; (double) p <= Math.sqrt(n); p++) {
            if (n % p == 0) {
                while (n % p == 0) {
                    // divide out all prime factors so no composites
                    // will be used in equations
                    n /= p; 
                    pow++;
                }
                result *= (long) (Math.pow(p, pow) - Math.pow(p, pow - 1)); // phi = n*(1-1/p1)*(1-1/p2)...
            }
            
            // reset power
            pow = 0;
        }
        
        // this if statement occurs if n is prime phi(p) = p - 1
        // or if n is composed of a prime smaller than sqrt(n) and 
        // a prime bigger than sqrt(n): (still factor left after iterating to sqrt(n))
        if (n > 1) {
            result *= n - 1;
        }

        return result;
    }
}

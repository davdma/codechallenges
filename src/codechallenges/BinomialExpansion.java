/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codechallenges;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.lang.Math;

/**
 *
 * @author david
 */
public class BinomialExpansion {
    
    // Takes in an expression with a single, one character variable, and expands it. 
    // the expression is in the form (ax+b)^n.
    public static String expand(String expr) {
        // two parts:
        // 1) parse string and extract useful information (a, b, n) in (ax+b)^n
        // 1.1) use combinatorics to calculate coefficients of each exponent
        // 2) process and return proper string representation following certain rules
        Pattern p = Pattern.compile("\\((-?\\d*)([a-z])\\+?(-?\\d*)\\)\\^(\\d+)");
        Matcher m = p.matcher(expr);
        m.find();
        String symbol = m.group(2);
        String formula = "";
        // must process a slightly differently
        // empty string, a = 1, only negative sign, a = -1
        long a = 1;
        if (m.group(1).equals("")) {
        } else if (m.group(1).equals("-")) {
            a = -1;
        } else {
            a = Long.valueOf(m.group(1));
        }
        long b = Long.valueOf(m.group(3));
        long n = Long.valueOf(m.group(4));
        
        // edge case
        if (n == 0) {
            return "1";
        }

        // loop through n+1 terms and create final expanded formula
        // starting from (n, n) x^n term down to (n, 0) last coefficient
        long nk = 1;
        for (long k = n; k >= 0; k--) {
            // use simple algorithm for calculating (n, k):
            // (n, k+1) = (n, k) * (n-k)/(k+1)
            // OR via descending:
            // (n, k-1) = (n, k) * k / (n-k+1)
            
            // calculate coefficient
            // (n, k) * a^k * b^n-k
            long cf = (long) (nk * Math.pow(a, k) * Math.pow(b, n-k));
            // if not zero, add to string, if == 1 exclude coefficient
            
            // append term to string, watching for edge cases
            // determine sign of term first
            String sign = "+";
            if (cf < 0) {
                // if negative don't include sign
                sign = "";
            }
            
            if (cf == 0) {
                // do nothing
            } else if (cf == 1) {
                // leave out the 1 in the string form
                // check exponent of x first
                if (k == 0) {
                    formula += sign + Long.toString(cf);
                } else if (k == 1) {
                    formula += sign + symbol;
                } else {
                    formula += sign + symbol + "^" + Long.toString(k);
                }
            } else if (cf == -1) {
                // leave out 1 and replace with -
                if (k == 0) {
                    formula += Long.toString(cf);
                } else if (k == 1) {
                    formula += "-" + symbol;
                } else {
                    formula += "-" + symbol + "^" + Long.toString(k);
                }
            } else {
                // check exponent of x
                if (k == 0) {
                    formula += sign + Long.toString(cf);
                } else if (k == 1) {
                    formula += sign + Long.toString(cf) + symbol;
                } else {
                    formula += sign + Long.toString(cf) + symbol + "^" + Long.toString(k);
                }
            }
            
            // find next (n, k-1)
            nk = nk * k / (n-k+1);
        }
        return formula.charAt(0) == '+' ? formula.substring(1) : formula;
    }
}

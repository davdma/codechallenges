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
public class TopWords {
    
    // return top 3 most frequently occurring words in string
    public static List<String> top3(String s) {
        // split by space OR (regex |) next line character
        String[] filter = s.split(" |\\n|[^A-Za-z']+");
        // use a map to keep track of frequency
        Map<String, Integer> m = new TreeMap<>();
        
        // pass string through processing - remove any punctuation (except ')
        for (String str: filter) {
            String processed = str.toLowerCase().replaceAll("[^A-Za-z']+", "");
            
            if (processed.isBlank() || !processed.matches(".*[a-z].*")) {
                // disqualify empty spaces
                // also disqualify only apostrophes aka no letters
                continue;
            }
            
            // must use wrapper class here to accept null
            Integer freq = m.get(processed);
            m.put(processed, (freq == null) ? 1 : freq + 1);
        }
        
        // extract top 3 frequency words
        List<String> top3 = new ArrayList<>();
        
        // number of words to extractr
        int upper = 3;
        if (m.size() < 3) {
            upper = m.size();
        }
        
        for (int i = 0; i < upper; i++) {
            String curTop = topKey(m);
            m.remove(curTop);
            top3.add(curTop);
        }
        
        return top3;
    }
    
    // extract top frequency word from map
    public static String topKey(Map<String, Integer> m) {
        // after each pass through it remove the top frequency word
        String maxStr = "";
        int maxInt = 0;
        for (Iterator it = m.entrySet().iterator(); it.hasNext();) {
            Map.Entry<String, Integer> kv = (Map.Entry<String, Integer>) it.next();
            if (kv.getValue() > maxInt) {
                maxInt = kv.getValue();
                maxStr = kv.getKey();
            }
        }
        return maxStr;
    }
}

package problems.lc2060;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    Map<String , Boolean> map;
    public boolean isEmpty(int n, String s) {
        return n==0 && s.isEmpty();
    }

    public boolean isNumber(char ch) {
        return ch >= '1' && ch <= '9';
    }

    public boolean possiblyEquals(int  num, String s1, String s2) {
        if(num == 0 && s1.isEmpty() && s2.isEmpty()) {
            return true;
        }
        if(num == 0 && s1.isEmpty() && !s2.isEmpty()) {
            return false;
        }
        if((num > 0 || !s1.isEmpty()) && s2.isEmpty()) {
            return false;
        }

        if(map.containsKey(num + s1 + s2)) {
            return true;
        }
        if(num > 0) {
            if(!isNumber(s2.charAt(0))) {
                return possiblyEquals(num - 1, s1, s2.substring(1));
            }
            int index = 0;
            while ( index < s2.length() && isNumber(s2.charAt(index))) {
                int number = Integer.parseInt(s2.substring(0, index+1));
                if(number <= num) {
                    boolean result = possiblyEquals(num - number, s1, s2.substring(index+1));
                    if(result) {
                        map.put((num - number) + s1 + s2.substring(index + 1) , true);
                        map.put((num - number) + s2.substring(index + 1) + s1, true);
                        return true;
                    }
                } else {
                    boolean result = possiblyEquals(number - num, s2.substring(index+1), s1);
                    if(result) {
                        map.put( (number - num) + s1 + s2.substring(index + 1) , true);
                        map.put( (number - num) + s2.substring(index + 1) + s1, true);
                        return true;
                    }
                }
                index++;
            }
            return false;
        }

        if(isNumber(s1.charAt(0))) {
            int index = 0;
            while (index < s1.length() && isNumber(s1.charAt(index))) {
                int number = Integer.parseInt(s1.substring(0, index+1));
                boolean result = possiblyEquals(number, s1.substring(index+1), s2);
                if(result) {
                    map.put(number + s1.substring(index+1) + s2, true);
                    map.put(number + s2 + s1.substring(index+1) , true);
                    return true;
                }
                index++;
            }
            return false;
        }

        if(isNumber(s2.charAt(0))) {
            int index = 0;
            while (index < s2.length() && isNumber(s2.charAt(index))) {
                int number = Integer.parseInt(s2.substring(0, index+1));
                boolean result = possiblyEquals(number, s2.substring(index+1), s1);
                if(result) {
                    map.put(number + s2.substring(index+1) + s1, true);
                    map.put(number + s1 + s2.substring(index+1) , true);
                    return true;
                }
                index++;
            }
            return false;
        }
        boolean result =  s1.charAt(0) == s2.charAt(0) && possiblyEquals(0, s1.substring(1), s2.substring(1));
        if(result) {
            map.put(0 + s1.substring(1) + s2.substring(1), true);
            map.put(0 + s2.substring(1) + s1.substring(1), true);
        }
        return result;
    }

    public boolean possiblyEquals(String s1, String s2) {
        map = new HashMap<>();
        return possiblyEquals(0, s1, s2);
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.possiblyEquals("internationalization", "i18n"));
        System.out.println(obj.possiblyEquals("l123e", "123e"));
        System.out.println(obj.possiblyEquals("l123e", "44"));
        System.out.println(obj.possiblyEquals("a5b", "c5b"));
        System.out.println(obj.possiblyEquals("44", "123"));
        System.out.println(obj.possiblyEquals("64g97q959g531q54g576g491q611g362g", "9g157q83q57q9g465q92q554g23g41q47"));
    }
}

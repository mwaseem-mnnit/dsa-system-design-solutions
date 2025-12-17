package problems.lc17;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    public List<String> letterCombinations(String digits) {
        Map<Character, Character[]> digitToCharacter = new HashMap<>();
        digitToCharacter.put('2', new Character[]{'a', 'b', 'c'});
        digitToCharacter.put('3', new Character[]{'d', 'e', 'f'});
        digitToCharacter.put('4', new Character[]{'g', 'h', 'i'});
        digitToCharacter.put('5', new Character[]{'j', 'k', 'l'});
        digitToCharacter.put('6', new Character[]{'m', 'n', 'o'});
        digitToCharacter.put('7', new Character[]{'p', 'q', 'r', 's'});
        digitToCharacter.put('8', new Character[]{'t', 'u', 'v'});
        digitToCharacter.put('9', new Character[]{'w', 'x', 'y', 'z'});
        List<String> result = new ArrayList<>();
        this.computeAllPossibleString(digits, digitToCharacter, result, "");
        return result;
    }

    private void computeAllPossibleString(
        String digits,
        Map<Character, Character[]> digitToCharacter,
        List<String> result,
        String prefix
    ) {
        if(digits.length() == 0) {
            result.add(prefix);
            return;
        }
        char digit = digits.charAt(0);
        Character[] characters = digitToCharacter.get(digit);
        for(Character character: characters) {
            this.computeAllPossibleString(digits.substring(1), digitToCharacter, result, prefix + character);
        }
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.letterCombinations("23"));
        System.out.println(obj.letterCombinations("2"));
        System.out.println(obj.letterCombinations("33"));
        System.out.println(obj.letterCombinations("2345"));
    }
}
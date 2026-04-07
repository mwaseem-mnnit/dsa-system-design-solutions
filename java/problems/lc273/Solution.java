package problems.lc273;

import java.util.Stack;

class Solution {
    String[] belowTwenty = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
            "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
            "Eighteen", "Nineteen"};

    String[] tensWord = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty",
            "Seventy", "Eighty","Ninety"};

    String[] hundredsWord = {"", "Thousand", "Million", "Billion"};

    public String numberToWords(int num) {
        if(num == 0) {
            return "Zero";
        }
        String numChar = Integer.toString(num);
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < numChar.length(); i++) {
            stack.push(numChar.charAt(i));
        }
        StringBuilder result = new StringBuilder();
        int i = 0;
        while(!stack.isEmpty()) {
            String nextWord = this.computeNextHundred(stack);
            if(!nextWord.isEmpty()) {
                result.insert(0, (nextWord + " " + hundredsWord[i]).trim() + " ");
            }
            i++;
        }
        return result.toString().trim();
    }

    private String computeNextHundred(Stack<Character> stack) {
        char ones = stack.isEmpty() ? '0' : stack.pop();
        char tens = stack.isEmpty() ? '0' : stack.pop();
        char hundred = stack.isEmpty() ? '0' : stack.pop();
        StringBuilder result = new StringBuilder();
        if(Integer.parseInt(String.valueOf(tens) + ones) < 20) {
            result.append(belowTwenty[Integer.parseInt(String.valueOf(tens) + ones)]);
        } else {
            result.append(tensWord[Integer.parseInt(String.valueOf(tens))]).append(" ");
            result.append(belowTwenty[Integer.parseInt(String.valueOf(ones))]);
        }
        if(hundred != '0') {
            result.insert(0, belowTwenty[Integer.parseInt(String.valueOf(hundred))] + " Hundred " );
        }
        return result.toString().trim();
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.numberToWords(123));
        System.out.println(obj.numberToWords(123019000));
        System.out.println(obj.numberToWords(123019001));
        System.out.println(obj.numberToWords(2123019001));
        System.out.println(obj.numberToWords(2119019001));
    }
}

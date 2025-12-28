package problems.lc2116;

import java.util.Stack;

class Solution {

    public boolean canBeValid(String s, String locked) {
        if (s.length() % 2 > 0) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(locked.charAt(i) == '0') {
                int top = stack.isEmpty() || stack.peek() == -1 ? 0 : stack.pop();
                stack.push(top + 1);
            } else if(c == '(') {
                stack.push(-1);
            } else {
                if(stack.isEmpty()) {
                    return false;
                } else if (stack.peek() == -1) {
                    stack.pop();
                } else {
                    int top = stack.pop();
                    if(!stack.isEmpty() && stack.peek() == -1) {
                        stack.pop();
                    } else {
                        top = top -1;
                    }
                    if(top > 0) {
                        stack.push(top);
                    }
                }
            }
        }
        int count = 0;
        while (!stack.isEmpty()) {
            int top = stack.pop();
            if(top == -1) {
                if(count == 0) {
                    return false;
                } else {
                    count--;
                }
            } else {
                count += top;
            }
        }
        return count % 2 == 0;
    }
    /*
    * (((()) (((())
    * 111111 010111
    * (( (((())
    *    010111
    * */
    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.canBeValid("))()))", "010100"));
        System.out.println(obj.canBeValid("(((())", "111111"));
        System.out.println(obj.canBeValid("(((())(((())", "111111010111"));
        System.out.println(obj.canBeValid(")", "0"));
        System.out.println(obj.canBeValid("()()", "0000"));
        System.out.println(obj.canBeValid("()()", "1111"));
        System.out.println(obj.canBeValid("((()(()()))()((()()))))()((()(()", "10111100100101001110100010001001"));
        // ((()(()()
        // 101111001
    }
}
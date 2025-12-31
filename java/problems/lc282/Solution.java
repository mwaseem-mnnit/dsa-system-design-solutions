package problems.lc282;


import java.util.*;

class Solution {
    List<String> result;
    public List<String> addOperators(String num, int target) {
        this.result = new ArrayList<>();
        this.findTarget(num, "", target);
        return result;
    }

    public void findTarget(String num, String exp , int target) {
        if(num.length() <= 0) {
            this.createExp(exp, target);
            return;
        }
        for(int i=1; i<= num.length(); i++) {
            String sub = num.substring(0, i);
            if(sub.startsWith("0") && sub.length() > 1) {
                continue;
            }
            if(i==num.length()) {
                this.findTarget("", exp + sub, target);
            } else {
                this.findTarget(num.substring(i), exp + sub + "+", target);
                this.findTarget(num.substring(i), exp + sub + "-", target);
                this.findTarget(num.substring(i), exp + sub + "*", target);

            }
        }
    }

    public void createExp(String exp, int target) {
        Stack<String> stack = new Stack<>();
        for(int i = exp.length() - 1; i >= 0 ; i--) {
            char ch = exp.charAt(i);
            switch(ch) {
                case '-':
                case '+':
                case '*':
                    stack.push(Character.toString(ch));
                    break;
                default:
                    StringBuilder number = new StringBuilder();
                    while( i >= 0 && !( exp.charAt(i) == '+' || exp.charAt(i) == '-' || exp.charAt(i) == '*')) {
                        number.append(exp.charAt(i));
                        i--;
                    }
                    i++;
                    stack.push(String.valueOf(Long.parseLong(number.reverse().toString())));
            }
        }
        String resultExp = this.evaluateExp(stack, target);
        if(!Objects.equals(resultExp, "")) {
            result.add( resultExp);
        }
    }

    public String evaluateExp(Stack<String> stack, int target) {
        StringBuilder resultExp = new StringBuilder(stack.peek());
        long result = Long.parseLong( stack.pop());
        while( !stack.isEmpty()) {
            resultExp.append(stack.peek());
            String top=stack.pop();
            if(Objects.equals(top, "*")) {
                resultExp.append(stack.peek());
                result *= Long.parseLong(stack.pop());
            } else {
                resultExp.append(stack.peek());
                long temp = Long.parseLong(stack.pop());
                while(!stack.isEmpty() && Objects.equals(stack.peek(), "*")) {
                    resultExp.append(stack.peek());
                    stack.pop();
                    resultExp.append(stack.peek());
                    temp *= Long.parseLong(stack.pop());
                }
                result = (Objects.equals(top, "+")) ? result + temp : result - temp;
            }
        }
        return (long) target == result ? resultExp.toString() : "";
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.addOperators("123", 6));
        System.out.println(s.addOperators("3456237490", 9191));

    }
}


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ValidCoupon {
    public static List<Integer> fun(List<String> coupon) {
        List<Integer> answer = new ArrayList<Integer>();
        for(int i=0; i<coupon.size(); i++) {
            Stack<Character> stack = new Stack<Character>();
            for (int j = 0; j < coupon.get(i).length(); j++) {
                if(stack.size() > 0 && stack.peek().charValue() == coupon.get(i).charAt(j)) {
                    stack.pop();
                } else {
                    stack.push(coupon.get(i).charAt(j));
                }
            }
            if(stack.size() > 0) {
                answer.add(0);
            } else {
                answer.add(1);
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(fun(List.of("abba", "abca", "", "daabbd", "abc", "acac")));
    }
}
package problems.oddevenjump;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

class Solution {

    public static TreeMap<Integer, Integer> treeMap;

    public static int oddEvenJumps(int[] arr) {
        int n = arr.length;
        if(n == 0 ) return 0;

        treeMap = new TreeMap<>();
        int[] oddJumpResult = new int[n];
        int[] evenJumpResult = new int[n];
        oddJumpResult[n-1] = 1;
        evenJumpResult[n-1] = 1;

        treeMap.put(arr[n-1], n-1);
        for (int i = n-2; i >= 0; i--) {
            computeOddEvenJump(0, arr[i], i, oddJumpResult, evenJumpResult);
            computeOddEvenJump(1, arr[i], i, evenJumpResult, oddJumpResult);
            treeMap.put(arr[i], i);
        }
        return Arrays.stream(oddJumpResult).sum();
    }

    public static void computeOddEvenJump(int type, int value, int index, int[] jumpResult, int[] useResult) {
        Map.Entry<Integer, Integer> entry = (type == 0) ? treeMap.ceilingEntry(value) : treeMap.floorEntry(value);

        if(entry == null) {
            jumpResult[index] = 0;
        } else {
            jumpResult[index] = useResult[entry.getValue()] == 1 ? 1 : 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(oddEvenJumps(new int[]{5,1,3,4,2}));
    }
}
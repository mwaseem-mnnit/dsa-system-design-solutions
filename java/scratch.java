import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Scratch {
    public static void main(String[] args) {
        System.out.println("Hello World!!!");
//        List<Float> list = new ArrayList<>();
//        list.add((float) 1.01);
//        list.add((float) 1.99);
//        list.add((float) 2.5);
//        list.add((float) 1.5);
//        list.add((float) 1.01);
//        System.out.println(efficientJanitor(list));
        System.out.println(prison(3, 2, Arrays.asList(1, 2, 3), Arrays.asList(1, 2)));

    }

    public static int efficientJanitor(List<Float> weight) {
        weight.sort(Comparator.reverseOrder());
        int ans = 0;
        int i=0;
        while(i < weight.size()) {
            if(weight.get(i).compareTo(Float.valueOf("2.00")) >= 0) {
                ans+=1;
                i+=1;
            } else {
                break;
            }
        }
        while(i < weight.size()) {
            if(weight.get(i) == null) {
                i++;
                continue;
            }
            int j=i+1;
            while(j<weight.size()) {
                if(weight.get(j) != null && weight.get(j) + weight.get(i) <= 3.00) {
                    break;
                }
                j+=1;
            }
            if(j < weight.size()) {
                weight.set(j, null);
            }
            ans+=1;
            i++;
        }
        return ans;
    }

    public static long prison(int n, int m, List<Integer> h, List<Integer> v) {
        boolean[] x = new boolean[n+1];
        boolean[] y = new boolean[m+1];
        for(int i=0; i<=n; i++) {
            x[i] = true;
        }
        for(int i=0; i<=m; i++) {
            y[i] = true;
        }
        for (Integer integer : h) {
            x[integer] = false;
        }

        for (Integer integer : v) {
            y[integer] = false;
        }
        long cx=0,ox= Long.MIN_VALUE, cy=0, oy= Long.MIN_VALUE;
        for (int i=1;i<=n;i++) {//loop to find the maximum gap horizontally
            if (x[i])
                cx= 0;
            else {
                cx++;
                ox= Math.max(ox, cx);
            }
        }

        for (int i=1;i<=m;i++) {//loop to find the maximum gap horizontally
            if (y[i])
                cy= 0;
            else {
                cy++;
                oy= Math.max(oy, cy);
            }
        }
        return (ox+1)*(oy+1);
    }


    public static int largestSubgrid(List<List<Integer>> grid, int maxSum) {
        if(grid == null || grid.size() == 0 || grid.get(0).size() == 0 )
            return 0;
        int n = grid.size();

        int[][] sum = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {

            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    sum[i][j] = 0;
                    continue;
                }

                sum[i][j] = grid.get(i - 1).get(j - 1)
                        + sum[i - 1][j] + sum[i][j - 1]
                        - sum[i - 1][j - 1];
            }
        }

        int ans = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {

                if (i + ans - 1 > n || j + ans - 1 > n)
                    break;

                int mid, lo = ans;

                int hi = Math.min(n - i + 1, n - j + 1);

                while (lo < hi) {

                    mid = (hi + lo + 1) / 2;

                    if (sum[i + mid - 1][j + mid - 1]
                            + sum[i - 1][j - 1]
                            - sum[i + mid - 1][j - 1]
                            - sum[i - 1][j + mid - 1]
                            <= maxSum) {
                        lo = mid;
                    }

                    else {
                        hi = mid - 1;
                    }
                }

                ans = Math.max(ans, lo);
            }
        }
        return ans;
    }
}

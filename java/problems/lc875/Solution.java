package problems.lc875;
import java.util.*;

public class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int l = 1;//Arrays.stream(piles).min().getAsInt();
        int r = Arrays.stream(piles).max().getAsInt();
        int min=r;
        while(l<=r) {
            int mid = (l+r)/2;
            boolean isValid = isValidK(piles, mid, h);
            if(isValid) {
                min = Math.min(min, mid);
                r=mid-1;
            } else {
                l = mid+1;
            }
        }
        return min;
    }
    public boolean isValidK(int[] piles, int k, int h) {
        int i=0;
        for( ;i<piles.length;i++){
            h = h - (piles[i]/k + (piles[i]%k == 0 ? 0 : 1));
            if(h<0) {
                break;
            }
        }
        if(h<0 || i<piles.length){
            return false;
        }
        return true;
    }
}
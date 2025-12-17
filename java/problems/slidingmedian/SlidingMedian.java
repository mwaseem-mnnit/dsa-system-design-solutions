package problems.slidingmedian;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

class Solution {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(medianSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));;
    }

    public static double[] medianSlidingWindow(int[] nums, int k) {
        TreeSet<MyPair> maxHeap = new TreeSet<>((o1, o2) -> o2.val - o1.val);
        TreeSet<MyPair> minHeap = new TreeSet<>(Comparator.comparingInt(o -> o.val));
        MyPair[] pairs = new MyPair[nums.length];
        for(int i=0;i<nums.length;i++) {
            pairs[i]=new MyPair(nums[i], i);
            if(i<k) {
                minHeap.add(pairs[i]);
            }
        }
        for(int i=0; i<k/2; i++) {
            maxHeap.add(minHeap.pollFirst());
        }
        double[] medians = new double[nums.length-k+1];
        medians[0] = getMedian(minHeap, maxHeap);
        for(int j=0; j<nums.length-k; j++) {
            minHeap.remove(pairs[j]);
            maxHeap.remove(pairs[j]);
            if(!minHeap.isEmpty() && pairs[k+j].val >= minHeap.first().val) {
                minHeap.add(pairs[k+j]);
            } else {
                maxHeap.add(pairs[k+j]);
            }

            if(minHeap.size()>maxHeap.size()+1) {
                maxHeap.add(minHeap.pollFirst());
            }
            if(maxHeap.size()>minHeap.size()+1) {
                minHeap.add(maxHeap.pollFirst());
            }
            medians[j+1] = getMedian(minHeap, maxHeap);
        }
        return medians;
    }

    public static double getMedian(TreeSet<MyPair> minHeap, TreeSet<MyPair> maxHeap) {
        double median;
        if(minHeap.size() == maxHeap.size()) {
            median = (((double)minHeap.first().val + (double)maxHeap.first().val)/2);

        } else {
            median = minHeap.size() > maxHeap.size() ? (double)minHeap.first().val : (double)maxHeap.first().val;
        }
        return (double)Math.round(median*100000d)/100000d;
    }
}

class MyPair {
    int val;
    int index;
    MyPair(int val, int index) {
        this.val=val;
        this.index=index;
    }
}
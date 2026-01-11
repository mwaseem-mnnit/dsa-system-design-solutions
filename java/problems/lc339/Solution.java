package problems.lc339;

import java.util.List;

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 */

interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // The result is undefined if this NestedInteger holds a nested list
    public Integer getInteger();

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value);

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni);

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // The result is undefined if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}
class Solution {

    long recursion(List<NestedInteger> nestedList, int depth) {
        long sum = 0;
        for (NestedInteger nestedInteger : nestedList) {
            if(nestedInteger.isInteger()) {
                sum += (long) nestedInteger.getInteger() * depth;
            } else {
                sum += recursion(nestedInteger.getList(), depth + 1);
            }
        }
        return sum;
    }

    public int depthSum(List<NestedInteger> nestedList) {
        return (int) recursion(nestedList, 1);
    }
}
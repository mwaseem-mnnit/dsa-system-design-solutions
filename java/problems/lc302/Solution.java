package problems.lc302;

class Solution {

    boolean findInVector(char[][] matrix, int choice, int mid) {
        switch (choice) {
            case 0, 1:
                for (int i = 0; i < matrix.length; i++) {
                    if (matrix[i][mid] == '1') return true;
                }
                return false;
            case 2, 3:
                ;
                for (int i = 0; i < matrix[0].length; i++) {
                    if (matrix[mid][i] == '1') return true;
                }
                return false;
        }
        return false;
    }

    int[] computedUpdatedLR(int choice, boolean isFound, int l, int r, int mid) {
        switch (choice) {
            case 0, 2:
                if (isFound) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
                break;
            case 1, 3:
                if (isFound) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
                break;
        }
        return new int[]{l, r};
    }

    int findTargetIndex(char[][] matrix, int choice, int l, int r) {
        while (l < r) {
            int mid = (l + r) / 2;
            boolean isFound = findInVector(matrix, choice, mid);
            int[] updatedLR = computedUpdatedLR(choice, isFound, l, r, mid);
            l = updatedLR[0];
            r = updatedLR[1];
            if (l + 1 == r) {
                switch (choice) {
                    case 0, 2:
                        if(findInVector(matrix, choice, l)) {
                            return l;
                        }
                        return r;
                    case 1, 3:
                        if (findInVector(matrix, choice, r)) {
                            return r;
                        }
                        return l;
                }
            }
        }
        return l;
    }

    public int minArea(char[][] image, int x, int y) {
        int left = findTargetIndex(image, 0, 0, y);
        int right = findTargetIndex(image, 1, y, image[0].length - 1);
        int top = findTargetIndex(image, 2, 0, x);
        int bottom = findTargetIndex(image, 3, x, image.length - 1);
        return (right - left + 1) * (bottom - top + 1);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.minArea(new char[][]{{'0','0','1','0'},{'0','1','1','0'},{'0','1','0','0'}}, 0, 2));
        System.out.println(s.minArea(new char[][]{{'0', '1'}, {'0', '1'}}, 0, 1));
        System.out.println(s.minArea(new char[][]{{'1', '0'}, {'1', '0'}}, 1, 0));
        System.out.println(s.minArea(new char[][]{{'1', '1'}, {'0', '0'}}, 0, 0));
        System.out.println(s.minArea(new char[][]{{'0', '0'}, {'1', '1'}}, 1, 1));
        System.out.println(s.minArea(new char[][]{{'1', '0'}, {'0', '1'}}, 1, 1));
        System.out.println(s.minArea(new char[][]{{'0', '1', '0'}, {'1', '1', '1'}, {'1', '1', '1'}, {'0', '1', '0'}, {'0', '0', '0'}, {'0', '0', '0'}}, 1, 1));
    }
}
import java.util.ArrayList;
import java.util.List;

class Solution {
    public static List<String> fullJustify(String[] words, int maxWidth) {
        int currentWidth=0;
        List<String> temp = new ArrayList<>();
        List<String> result = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if(currentWidth + words[i].length() < maxWidth || currentWidth > 0 && currentWidth + words[i].length() + 1 < maxWidth) {
                temp.add(words[i]);
            } else {
                result.add(createString(temp, maxWidth));
                currentWidth=0;
                temp = new ArrayList<>();
            }

        }
    }

    private static String createString(List<String> temp, int maxWidth) {
        int totalChar = temp.stream().mapToInt(String::length).sum();
        int spaces = maxWidth - totalChar;
        
    }

    public static void main(String[] args) {

    }
}
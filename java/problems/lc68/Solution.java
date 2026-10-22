package problems.lc68;

import java.util.ArrayList;
import java.util.List;

class Solution {

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < words.length; ) {
            List<String> subWords = this.fetchNextLineWords(words, maxWidth, i);
            if (subWords.isEmpty()) {
                return result;
            }
            i = i + subWords.size();
            String currentLine = this.createNewLineWithWords(subWords, maxWidth, i == words.length);
            result.add(currentLine);
        }
        return result;
    }
    // This     is     an
    // This    is    an
    private String createNewLineWithWords(List<String> subWords, int maxWidth, boolean lastLine) {
        if (lastLine) {
            String temp = (String.join(" ", subWords));
            return temp + " ".repeat(maxWidth - temp.length());
        }

        if(subWords.size() == 1) {
            return subWords.getFirst() + " ".repeat(maxWidth - subWords.getFirst().length());
        }
        int totalSpace = maxWidth - subWords.stream().mapToInt(String::length).sum();
        int fixSpace = totalSpace / (subWords.size() - 1);
        int extraSpace = totalSpace - (fixSpace * (subWords.size() - 1));
        StringBuilder result = new StringBuilder();
        for (int i=0; i< subWords.size() - 1;i++) {
            String subWord = subWords.get(i);
            result.append(subWord);
            result.append(" ".repeat(fixSpace));
            if (extraSpace > 0) {
                result.append(" ");
                extraSpace = extraSpace - 1;
            }
        }
        result.append(subWords.getLast());
        return result.toString();
    }

    private List<String> fetchNextLineWords(
            String[] words, int maxWidth, int startIndex
    ) {
        int i = startIndex;
        int currentLength = 0;
        List<String> result = new ArrayList<>();
        while ( i < words.length && currentLength < maxWidth) {
            if (currentLength + words[i].length() > maxWidth) {
                break;
            }
            currentLength += (words[i].length() + 1);
            result.add(words[i]);
            i++;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution object = new Solution();
        System.out.println(object.fullJustify(
                new String[]{"This", "is", "an", "example", "of", "text", "justification."},
                16
        ));
    }
}

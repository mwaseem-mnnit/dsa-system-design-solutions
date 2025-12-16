import java.util.*;

class Solution {

    public String alienOrder(String[] words) {
        Map<Character, Integer> inDegreeMap = new HashMap<>();
        Map<Character, List<Character>> preRequisiteMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            this.intilaiseIndegree(inDegreeMap, words[i]);
        }
        for (int i = 0; i < words.length - 1; i++) {
            int result = this.setPreRequisiteAndIndegree(words[i], words[i + 1], inDegreeMap, preRequisiteMap);
            if(result == -1) {
                return "";
            }
        }

        return this.topoSortIfValid(inDegreeMap, preRequisiteMap);
    }

    private String topoSortIfValid(Map<Character, Integer> inDegreeMap, Map<Character, List<Character>> preRequisiteMap) {
        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        this.initialiseQueue(inDegreeMap, queue);
        while (!queue.isEmpty()) {
            Character result = queue.poll();
            sb.append(result);

            List<Character> preRequisite = preRequisiteMap.getOrDefault(result, new ArrayList<>());
            for (Character preRequisiteChar : preRequisite) {
                inDegreeMap.put(preRequisiteChar, inDegreeMap.get(preRequisiteChar) - 1);
                if(inDegreeMap.get(preRequisiteChar) == 0) {
                    queue.add(preRequisiteChar);
                    inDegreeMap.remove(preRequisiteChar);
                }
            }
        }
        return inDegreeMap.size() > 0 ? "" : sb.toString();
    }

    private void initialiseQueue(
        Map<Character, Integer> inDegreeMap, Queue<Character> queue
    ) {
        // Iterate using an iterator
        Iterator<Map.Entry<Character, Integer>> iterator = inDegreeMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Character, Integer> entry = iterator.next();
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
                iterator.remove(); // Safely remove the current entry
            }
        }
    }

    private int setPreRequisiteAndIndegree(
            String word, String word1,
            Map<Character, Integer> inDegreeMap,
            Map<Character, List<Character>> preRequisiteMap
    ) {
        int i = 0, j = 0;
        while (i < word.length() && j < word1.length() && word.charAt(i) == word1.charAt(j)) {
            i++;
            j++;
        }

        if(i < word.length() && j >= word1.length()) {
            return -1;
        }

        if (i < word.length()) {
            //set preRequisite
            List<Character> preRequisite = preRequisiteMap.getOrDefault(word.charAt(i), new ArrayList<>());
            if (!preRequisite.contains(word1.charAt(j))) {
                //set indegree
                inDegreeMap.put(word1.charAt(j), inDegreeMap.getOrDefault(word1.charAt(j), 0) + 1);
                preRequisite.add(word1.charAt(j));
                preRequisiteMap.put(word.charAt(i), preRequisite);
            }
        }
        return 0;
    }

    private void intilaiseIndegree(Map<Character, Integer> inDegreeMap, String word) {
        for (int i = 0; i < word.length(); i++) {
            inDegreeMap.putIfAbsent(word.charAt(i), 0);
        }
    }
}
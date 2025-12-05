import java.util.*;

class Main {

    public static List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> freq = new HashMap<>();
        for (String w : words) {
            freq.put(w, freq.getOrDefault(w, 0) + 1);
        }

        PriorityQueue<String> pq = new PriorityQueue<>(
            (a, b) -> {
                if (!freq.get(a).equals(freq.get(b))) {
                    return freq.get(b) - freq.get(a);
                }
                return a.compareTo(b);
            }
        );

        pq.addAll(freq.keySet());

        List<String> result = new ArrayList<>();
        for (int i = 0; i < k && !pq.isEmpty(); i++) {
            result.add(pq.poll());
        }

        return result;
    }

    public static void main(String[] args) {
        String[] words = {"java","python","java","golang","java","python"};
        int k = 2;

        System.out.println(topKFrequent(words, k));
    }
}
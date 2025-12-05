import java.util.Map;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        String input = "This is a test. This is only a test";

        Map<String, Integer> result = countWords(input);

        result.forEach((k, v) -> System.out.println(k + " → " + v));
    }

    public static Map<String, Integer> countWords(String text) {

        Map<String, Integer> freq = new HashMap<>();

        if (text == null || text.isEmpty()) {
            return freq;
        }

        // Hapus tanda baca, kecuali huruf & angka
        text = text.toLowerCase().replaceAll("[^a-z0-9 ]", "");

        // Split berdasarkan spasi
        String[] words = text.split("\\s+");

        for (String w : words) {
            if (!w.isEmpty()) {
                freq.put(w, freq.getOrDefault(w, 0) + 1);
            }
        }

        return freq;
    }
}

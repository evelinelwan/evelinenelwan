import java.util.*;

public class Main {

    // Fungsi untuk mengecek apakah object adalah tipe primitif JSON
    public static boolean isPrimitiveJson(Object obj) {
        return obj == null ||
               obj instanceof String ||
               obj instanceof Number ||
               obj instanceof Boolean;
    }

    // Fungsi utama validasi JSON nested
    public static boolean isValidJson(Object input) {

        // Jika primitif → valid
        if (isPrimitiveJson(input)) {
            return true;
        }

        // Jika Map → cek key harus String, value harus valid (rekursif)
        if (input instanceof Map<?, ?> map) {
            for (Map.Entry<?, ?> entry : map.entrySet()) {

                // Key harus String
                if (!(entry.getKey() instanceof String)) {
                    return false;
                }

                // Value harus valid JSON → rekursif
                if (!isValidJson(entry.getValue())) {
                    return false;
                }
            }
            return true;
        }

        // Jika List → cek semua elemen valid JSON
        if (input instanceof List<?> list) {
            for (Object element : list) {
                if (!isValidJson(element)) {
                    return false;
                }
            }
            return true;
        }

        // Jika Array → cek semua elemen
        if (input.getClass().isArray()) {
            int len = java.lang.reflect.Array.getLength(input);
            for (int i = 0; i < len; i++) {
                Object element = java.lang.reflect.Array.get(input, i);
                if (!isValidJson(element)) {
                    return false;
                }
            }
            return true;
        }

        // Selain itu → tidak valid JSON
        return false;
    }

    public static void main(String[] args) {

        // Contoh JSON valid: Map → List → Map → primitive
        Map<String, Object> valid = new HashMap<>();
        valid.put("nama", "Eveline");
        valid.put("umur", 22);
        valid.put("alamat", Map.of("kota", "Jakarta", "kode", 12345));
        valid.put("hobi", List.of("coding", "music"));

        System.out.println("Valid JSON ? " + isValidJson(valid)); // true

        // Contoh JSON tidak valid: key bukan String → false
        Map<Object, Object> invalidKey = new HashMap<>();
        invalidKey.put(123, "angka sebagai key"); // salah

        System.out.println("Valid JSON ? " + isValidJson(invalidKey)); // false

        // Contoh JSON tidak valid: tipe aneh
        Map<String, Object> invalidType = new HashMap<>();
        invalidType.put("obj", new Thread()); // tidak boleh

        System.out.println("Valid JSON ? " + isValidJson(invalidType)); // false
    }
}

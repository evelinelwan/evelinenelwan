import java.util.Map;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Map<String, Object> data = Map.of(
            "user", Map.of(
                "name", "Eveline",
                "age", "31"
            )
        );

        String output = toMapOfFormat(data);
        System.out.println(output);
    }

    // ============================
    // Convert object → Map.of(...)
    // ============================
    public static String toMapOfFormat(Object input) {

        if (input == null) return "null";

        // Primitive types
        if (input instanceof String) return '"' + input.toString() + '"';
        if (input instanceof Number || input instanceof Boolean) return input.toString();

        // List
        if (input instanceof List<?>) {
            List<?> list = (List<?>) input;
            StringBuilder sb = new StringBuilder("List.of(");
            for (int i = 0; i < list.size(); i++) {
                sb.append(toMapOfFormat(list.get(i)));
                if (i < list.size() - 1) sb.append(", ");
            }
            sb.append(")");
            return sb.toString();
        }

        // Map
        if (input instanceof Map<?, ?>) {
            Map<?, ?> map = (Map<?, ?>) input;

            StringBuilder sb = new StringBuilder("Map.of(");
            int count = 0;

            for (Map.Entry<?, ?> e : map.entrySet()) {
                sb.append('"').append(e.getKey().toString()).append('"').append(", ");
                sb.append(toMapOfFormat(e.getValue()));

                if (++count < map.size()) sb.append(", ");
            }

            sb.append(")");

            return sb.toString();
        }

        // Semua tipe lain → dianggap string
        return '"' + input.toString() + '"';
    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Second_part {
    public static void main(String[] args) {
        String filePath = "Day_5/input.txt"; // Path to the file
        List<int[]> rules = new ArrayList<>();
        List<List<Integer>> updates = new ArrayList<>();
        int sum = 0;

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty() && line.contains("|")) {
                    String[] parts = line.split("\\|");
                    int x = Integer.parseInt(parts[0]);
                    int y = Integer.parseInt(parts[1]);
                    rules.add(new int[]{x, y});
                } else if (!line.isEmpty()) {
                    String[] parts = line.split(",");
                    List<Integer> update = new ArrayList<>();
                    for (String part : parts) {
                        update.add(Integer.parseInt(part));
                    }
                    updates.add(update);
                }
            }

            for (List<Integer> update : updates) {
                if (!isUpdateCorrectlyOrdered(update, rules)) {
                    List<Integer> sortedUpdate = sortUpdate(update, rules);
                    int middleIndex = sortedUpdate.size() / 2;
                    sum += sortedUpdate.get(middleIndex);
                }
            }

            System.out.println("Sum of middle page " + sum);

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    public static List<Integer> sortUpdate(List<Integer> update, List<int[]> rules) {

        //Create a map for storing positions of each elements
        Map<Integer, Integer> positions = new HashMap<>();

        for (int i = 0; i < update.size(); i++) {
            positions.put(update.get(i), i);
        }

        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int[] rule : rules) {
                int x = rule[0];
                int y = rule[1];

                if (positions.containsKey(x) && positions.containsKey(y)) {
                    int indexX = positions.get(x);
                    int indexY = positions.get(y);

                    // Swap if necessary to satisfy the rule
                    if (indexX > indexY) {
                        Collections.swap(update, indexX, indexY);
                        // update of the map
                        positions.put(x, indexY);
                        positions.put(y, indexX);
                        sorted = false;
                    }
                }
            }
        }
        return update;
    }

    private static boolean isUpdateCorrectlyOrdered(List<Integer> update, List<int[]> rules) {
        for (int[] rule : rules){
            int x = rule[0];
            int y = rule[1];

            int indexX = update.indexOf(x);
            int indexY = update.indexOf(y);

            if (indexX != -1 && indexY != -1 && indexX > indexY) {
                return false;
            }
        }
        return true;
    }
}


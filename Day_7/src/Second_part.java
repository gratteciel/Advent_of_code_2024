import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Second_part {
    public static void main(String[] args) {
        String filePath = "Day_7/input.txt"; // Path to the file
        long sum = 0;

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                /* Get data input */
                String[] numbers = line.split(":");
                long goal = Long.parseLong(numbers[0].trim()); // the objective
                List<String> values = List.of(numbers[1].trim().split(" ")); // values for target the obj

                if (bridgeRepair(goal, values)) {
                    sum += goal;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        System.out.println("Sum: " + sum);
    }

    private static boolean bridgeRepair(long goal, List<String> values) {
        // Convert string values to long
        List<Long> valuesLong = new ArrayList<>();
        for (String value : values) {
            valuesLong.add(Long.parseLong(value));  // Use Long instead of Integer
        }

        // Generate combinations and evaluate them
        return generateCombinationsAndEvaluate(valuesLong, goal);
    }

    private static boolean generateCombinationsAndEvaluate(List<Long> values, long goal) {
        // We use a queue for mem
        Queue<String> queue = new LinkedList<>();
        queue.add(String.valueOf(values.get(0))); // Start with the first value

        // Process each value and generate possible combinations incrementally
        for (int i = 1; i < values.size(); i++) {
            int size = queue.size();
            long currentValue = values.get(i);
            // For each current expression in the queue, add + and * and || with the next value
            for (int j = 0; j < size; j++) {
                String expr = queue.poll();
                queue.add(expr + " + " + currentValue);
                queue.add(expr + " * " + currentValue);
                queue.add(expr + " || " + currentValue);
            }
        }


        while (!queue.isEmpty()) {
            String expr = queue.poll();
            long result = evaluateExpression(expr);

            if (result == goal) {
                return true;
            }
        }

        return false;
    }

    private static long evaluateExpression(String expr) {
        String[] tokens = expr.split(" ");
        long result = Long.parseLong(tokens[0]);

        for (int i = 1; i < tokens.length; i += 2) {
            String operator = tokens[i];
            long value;


            if (operator.equals("||")) {
                // Concatenate number
                value = Long.parseLong(tokens[i + 1]);
                String resultString = String.valueOf(result) + value;
                result = Long.parseLong(resultString);
            } else {
                value = Long.parseLong(tokens[i + 1]);

                if (operator.equals("+")) {
                    result += value;
                } else if (operator.equals("*")) {
                    result *= value;
                }
            }
        }

        return result;
    }
}

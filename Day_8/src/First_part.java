import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class First_part {
    public static void main(String[] args) {
        String filePath = "Day_8/input.txt"; // Path to the file
        int uniqueAntinodes = 0;
        Map<Character, List<Pair<Integer, Integer>>> antenna = new HashMap<>();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            List<String> lines = new ArrayList<>();

            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }

            int rows = lines.size();
            int columns = rows > 0 ? lines.get(0).length() : 0;

            char[][] matrix = new char[rows][columns];

            Pattern pattern = Pattern.compile("[a-zA-Z0-9]"); // Matches antenna charac

            // Populate the matrix and antenna map
            for (int i = 0; i < rows; i++) {
                String row = lines.get(i);

                for (int j = 0; j < columns; j++) {
                    matrix[i][j] = row.charAt(j);
                }

                Matcher matcher = pattern.matcher(row);
                while (matcher.find()) {
                    char currentChar = row.charAt(matcher.start());
                    antenna.putIfAbsent(currentChar, new ArrayList<>());
                    antenna.get(currentChar).add(new Pair<>(i, matcher.start()));
                }
            }

            uniqueAntinodes = createAntinodes(matrix, antenna);


            System.out.println("Unique Antinode Locations: " + uniqueAntinodes);

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    private static int createAntinodes(char[][] matrix, Map<Character, List<Pair<Integer, Integer>>> antenna) {
        Set<Pair<Integer, Integer>> uniqueAntinodes = new HashSet<>(); //does not allows duplicates

        for (Character frequency : antenna.keySet()) {
            List<Pair<Integer, Integer>> positions = antenna.get(frequency);

            for (int i = 0; i < positions.size(); i++) {
                for (int j = 0; j < positions.size(); j++) {
                    if (i == j) continue; //itself

                    Pair<Integer, Integer> p1 = positions.get(i);
                    Pair<Integer, Integer> p2 = positions.get(j);

                    // Calculate antinode position
                    int antinodeRow = p1.first + (p1.first - p2.first);
                    int antinodeCol = p1.second + (p1.second - p2.second);

                    //if valid add
                    if (isWithinBounds(antinodeRow, antinodeCol, matrix)) {
                        uniqueAntinodes.add(new Pair<>(antinodeRow, antinodeCol));
                    }
                }
            }
        }

        return uniqueAntinodes.size();
    }

    private static boolean isWithinBounds(int row, int col, char[][] matrix) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length;
    }
}

// Pair class implementation found on stackOverflow need all this methods to perform on pair
class Pair<T, U> {
    public final T first;
    public final U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
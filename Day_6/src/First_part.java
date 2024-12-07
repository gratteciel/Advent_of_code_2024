import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class First_part {
    public static void main(String[] args) {
        String filePath = "Day_6/input.txt"; // Path to the file
        int sum = 0;
        int[] start = new int[2];

        try (Scanner scanner = new Scanner(new File(filePath))) {
            long length  = 0;
            long width = 0;
            boolean firstRow = true;
            while (scanner.hasNextLine()) {
                if (firstRow){
                    width =  scanner.nextLine().length();
                }
                length++;
            }
            scanner.close();
            //So we have a matrix
            char[][] matrix = new char[Long.valueOf(length).intValue()][Long.valueOf(width).intValue()];

            Scanner scanner1= new Scanner(new File(filePath));

            //populate the matrix
            for (int i = 0; i < length; i++) {
                String row = scanner1.nextLine();
                for (int j = 0; j < width; j++) {
                    char currentChar = row.charAt(j);
                    matrix[i][j] = currentChar;
                    if (currentChar == '^') {
                        start[0] = i; // Row index
                        start[1] = j; // Column index
                    }
                }
            }

            scanner.close();

            //search the word in the matrix :
            sum = guardPatrolling(matrix,start);

            System.out.println("Sum : " + sum);


        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    private static int guardPatrolling(char[][] matrix, int[] start) {
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // up, right, down, left
        int currentDirection = 0;
        int x = start[0], y = start[1];

        Set<String> visited = new HashSet<>();
        visited.add(x + "," + y); // Add starting position

        while (true) {
            int nextX = x + directions[currentDirection][0];
            int nextY = y + directions[currentDirection][1];

            // Check bounds
            if (nextX < 0 || nextX >= matrix.length || nextY < 0 || nextY >= matrix[0].length) {
                break;
            }

            // Check if the next cell is blocked
            if (matrix[nextX][nextY] == '#') {
                currentDirection = (currentDirection + 1) % 4; // Turn right %4 to stay 0-3
            } else {
                // Move forward
                x = nextX;
                y = nextY;
                visited.add(x + "," + y); // Mark the position as visited
            }
        }
        return visited.size();
    }
}
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class First_part {
    public static void main(String[] args) {
        String filePath = "Day_4/input.txt"; // Path to the file
        int sum = 0;
        String searchedWord = "MAS";


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
                    matrix[i][j] = row.charAt(j);
                }
            }

            scanner.close();

            //search the word in the matrix :
            sum = searchWordInMatrix(matrix,searchedWord);

            System.out.println("Sum : " + sum);


        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    private static int searchWordInMatrix(char[][] matrix, String searchedWord) {
        int count = 0;
        int n = matrix.length;
        int m = matrix[0].length;
        int wordLength = searchedWord.length();

        String reversedWord = new StringBuilder(searchedWord).reverse().toString();

        // Directions: right, down, diagonal right-down, diagonal left-down
        int[] dx = {0, 1, 1, 1};
        int[] dy = {1, 0, 1, -1};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // Check if the word starts at matrix[i][j] in any direction
                for (int dir = 0; dir < wordLength; dir++) {
                    if (canFindWord(matrix, searchedWord, i, j, dx[dir], dy[dir]) || canFindWord(matrix, reversedWord, i, j, dx[dir], dy[dir])) {

                        count++;
                    }
                }
            }
        }
        return count;

    }

    private static boolean canFindWord(char[][] matrix, String searchedWord, int x, int y, int dx, int dy) {

        int n = matrix.length;
        int m = matrix[0].length;
        int wordLength = searchedWord.length();

        // Traverse the word and check each character
        for (int k = 0; k < wordLength; k++) {
            int nx = x + k * dx; // Next x-coordinate
            int ny = y + k * dy; // Next y-coordinate

            // Check if out of bounds or mismatch
            if (nx < 0 || ny < 0 || nx >= n || ny >= m || matrix[nx][ny] != searchedWord.charAt(k)) {
                return false;
            }
        }
        return true;
    }
}
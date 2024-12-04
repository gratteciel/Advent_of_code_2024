import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Second_part {
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
            sum = searchWordInMatrix(matrix);

            System.out.println("Sum : " + sum);


        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    private static int searchWordInMatrix(char[][] matrix) {
        int count = 0;
        int n = matrix.length;
        int m = matrix[0].length;

        // Directions: right, down, diagonal right-down, diagonal left-down
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 'A'){ //when we find A search diagonally
                    if(i + 1 < n && j + 1 < m && i - 1 >= 0 && j - 1 >= 0 &&
                            matrix[i+1][j+1] == 'S' &&
                            matrix[i-1][j-1] == 'M' &&
                            matrix[i-1][j+1] == 'M' &&
                            matrix[i+1][j-1] == 'S'){
                        count++;
                    }
                    if(i + 1 < n && j + 1 < m && i - 1 >= 0 && j - 1 >= 0 &&
                            matrix[i+1][j+1] == 'M' &&
                            matrix[i-1][j-1] == 'S' &&
                            matrix[i-1][j+1] == 'M' &&
                            matrix[i+1][j-1] == 'S'){
                        count++;
                    }
                    if(i + 1 < n && j + 1 < m && i - 1 >= 0 && j - 1 >= 0 &&
                            matrix[i+1][j+1] == 'S' &&
                            matrix[i-1][j-1] == 'M' &&
                            matrix[i-1][j+1] == 'S' &&
                            matrix[i+1][j-1] == 'M'){
                        count++;
                    }
                    if(i + 1 < n && j + 1 < m && i - 1 >= 0 && j - 1 >= 0 &&
                            matrix[i+1][j+1] == 'M' &&
                            matrix[i-1][j-1] == 'S' &&
                            matrix[i-1][j+1] == 'S' &&
                            matrix[i+1][j-1] == 'M'){
                        count++;
                    }
                }
            }
        }
        return count;
    }



}

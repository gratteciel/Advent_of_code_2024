import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class First_part {
    public static void main(String[] args) {
        String filePath = "Day_3/input.txt"; // Path to the file
        int count = 0;

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                count += countMul(line);
                System.out.println(count);
            }
            System.out.println(count);

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
    private static int countMul(String input){
        int countTemp = 0; // count for the line
        Pattern pattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");
        Matcher matcher = pattern.matcher(input);
        while(matcher.find()){//matches the regex
            int product = 1;
            String match = matcher.group();
            String[] digits = match.split("[^0-9]+");
            for (String number: digits){
                if (!number.isEmpty()){
                    product *= Integer.parseInt(number);
                }
            }
            countTemp += product;
        }
        return countTemp;
    }
}
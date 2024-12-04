import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Second_part {
    public static void main(String[] args) {
        String filePath = "Day_3/input.txt"; // Path to the file
        int count = 0;
        boolean isFirstLine = true; // active for the first line
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (isFirstLine){
                    count += countMul(line,true);
                    isFirstLine = false;
                }
                else count += countMul(line,false);


            }
            System.out.println(count);

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }


    private static int countMul(String input, boolean isFirstLine){
        int countTemp = 0; // count for the line
        boolean active = isFirstLine;

        Pattern pattern = Pattern.compile("(mul\\((\\d+),(\\d+)\\))|(do\\(\\))|(don't\\(\\))"); // what we want
        Matcher matcher = pattern.matcher(input);

        while(matcher.find()){//matches the regex
            String match = matcher.group();
            if (match.equals("do()")){
                active = true;
            }
            else if(match.equals("don't()")){
                active = false;
            } else if (match.startsWith("mul") && active) {
                Matcher mulMatcher = Pattern.compile("mul\\((\\d+),(\\d+)\\)").matcher(match);
                if (mulMatcher.matches()) {
                    int num1 = Integer.parseInt(mulMatcher.group(1));
                    int num2 = Integer.parseInt(mulMatcher.group(2));
                    int product = num1 * num2;
                    countTemp += product;
                }

            }
        }
        return countTemp;
    }

}

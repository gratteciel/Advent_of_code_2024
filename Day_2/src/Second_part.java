import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Second_part {
    public static void main(String[] args) {
        String filePath = "Day_2/input.txt"; // Path to the file
        int sum = 0;
        List<String[]> newList = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filePath))) {// read the file and fill lists
            int count = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split(" ");// Every digits
                //Store levels in an list
                List<Integer> levels = new ArrayList<>();

                for(String word: words){
                    levels.add(Integer.parseInt(word));
                }
                if (isSafe(levels) || safeWithDeletion(levels)){
                    count++;
                }
            }
            System.out.println(count);

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

    }
    private static boolean safeWithDeletion(List<Integer> levels){
        for (int i = 0; i < levels.size(); i++) {
            //With modified Levels
            List<Integer> modifiedLevels = new ArrayList<>(levels);
            modifiedLevels.remove(i);
            if(isSafe(modifiedLevels)){
                return true;
            }
        }
        return false;


    }
    private static boolean isSafe(List<Integer> levels){
        boolean isAscending = true, isDescending = true;
        for (int i = 0; i < levels.size()-1; i++) {
            int diff = levels.get(i+1) - levels.get(i);
            if (Math.abs(diff) > 3 || Math.abs(diff) < 1) {
                return false; // Unsafe if difference too high
            }

            // Track if Descending or Ascending
            if (diff < 0) isAscending = false;
            if (diff > 0) isDescending = false;
        }
        return isAscending ^ isDescending; // return true if one of them is true (XOR)
    }

}
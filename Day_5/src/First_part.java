import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class First_part {
    public static void main(String[] args) {
        String filePath = "Day_5/input.txt"; // Path to the file
        List<int[]> rules = new ArrayList<>();
        List<List<Integer>> updates = new ArrayList<>(); // list of all numbers

        int sum = 0;


        try (Scanner scanner = new Scanner(new File(filePath))) {
            //store in two different arrays page ordering / produce
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (!line.isEmpty() && line.contains("|") ) { //rules
                    List<String> pages = List.of(line.split("\\|"));
                    int x = Integer.parseInt(pages.get(0));
                    int y = Integer.parseInt(pages.get(1));
                    rules.add(new int[] {x,y});
                }
                else if(!line.isEmpty()){ //page numbers of each update
                    List<String> numbers = List.of(line.split(","));
                    List<Integer> update = new ArrayList<>();
                    for (String number : numbers) {
                        update.add(Integer.parseInt(number));
                    }
                    updates.add(update);
                }
            }
            //process
            for (List<Integer> update : updates) {
                if(isUpdateCorrectlyOrdered(update, rules)){
                    int middleIndex = update.size() / 2;
                    sum += update.get(middleIndex); // Add the middle page number to the sum
                }


            }

            System.out.println("Sum of middle page " + sum);


        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    private static boolean isUpdateCorrectlyOrdered(List<Integer> update, List<int[]> rules) {
        for (int[] rule : rules) {
            int x = rule[0];
            int y = rule[1];

            int indexX = update.indexOf(x);
            int indexY = update.indexOf(y);

            if (indexX != -1 && indexY != -1 && indexX > indexY){
                return false;
            }
        }
        return true;
        }

}
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Second_part {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        String filePath = "Day_1/input.txt"; // Path to the file
        int sum = 0;

        try (Scanner scanner = new Scanner(new File(filePath))) {// read the file and fill lists
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split("   "); // Split

                // Add integers to the lists
                list1.add(Integer.parseInt(words[0]));
                list2.add(Integer.parseInt(words[1]));
            }
            // We check for each element how many times appears in the second list
            for (int i = 0; i < list1.size(); i++) {
                int count = 0;
                for (int nums : list2){
                    if (nums == list1.get(i)){
                        count++;
                    }
                }
                sum += list1.get(i) * count;
            }
            System.out.println(sum);



        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

    }

}
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class First_part {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        String filePath = "input.txt"; // Path to the file
        int sum = 0;

        try (Scanner scanner = new Scanner(new File(filePath))) {// read the file and fill lists
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split("   "); // Split

                // Add integers to the lists
                list1.add(Integer.parseInt(words[0]));
                list2.add(Integer.parseInt(words[1]));
            }
            //After read all data we sorted both arrays
            Collections.sort(list1);
            Collections.sort(list2);

            for (int i = 0; i < list1.size(); i++) {
               sum += Math.abs(list1.get(i) - list2.get(i));
            }
            System.out.println(sum);



        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

    }

}
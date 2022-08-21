import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Anthony_Sol {
    public static void main(String[] args) {
        // Reading both input files and check validity
        // CHANGE BOTH INPUT FILES HERE
        File f1 = new File("C:\\Users\\fauza\\Desktop\\School stuff\\Elements of Software Construction\\Software Testing Campaign\\Software-Testing-Campaign\\sample_file_3.csv");
        File f2 = new File("C:\\Users\\fauza\\Desktop\\School stuff\\Elements of Software Construction\\Software Testing Campaign\\Software-Testing-Campaign\\sample_file_1.csv");
        String path1 = checkValid(f1);
        String path2 = checkValid(f2);
        // DO NOT CHANGE THIS FILE PATH
        String pathOut = "../output.csv";
        system(path1, path2, pathOut);
    }

    // Where all the logic happens
    public static void system(String path1, String path2, String pathOut) {
        // Either file is not valid
        if (path1 == null || path2 == null) {
            System.out.println("File does not exist, or is of wrong format");
        }
        // Both files valid
        else {
            // Check if either file is blank
            boolean blank1 = checkBlank(path1);
            boolean blank2 = checkBlank(path2);
            ArrayList<String> list1 = new ArrayList<>();
            ArrayList<String> list2 = new ArrayList<>();
            // Either file is blank
            if (blank1 || blank2) {
                writeFileBlank(blank1, blank2, path1, path2, pathOut);
            }
            // Both files not blank
            else {
                try {
                    // Retrieve the entries from both files
                    list1 = readFile(path1);
                    list2 = readFile(path2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // Generate difference between them
                ArrayList<String> list = generateDifference(list1, list2);
                // Write to output file
                writeFile(list, pathOut);
            }
        }
    }

    // Checks if file valid
    public static String checkValid(File f) {
        // Declare extension
        String extension = "";
        try {
            boolean exists = f.exists();
            // Get and return the path of file
            if (exists) {
                // Get file extension
                String filename = f.toString();
                int index = filename.lastIndexOf('.');
                if (index > 0) {
                    extension = filename.substring(index + 1);
                }
                // Check file extension
                if (extension.equals("csv")) {
                    return f.getAbsolutePath();
                }
            }
            // File does not exist, or is of wrong type, return null
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred";
        }
    }

    // Detects a blank file
    public static boolean checkBlank(String path) {
        BufferedReader csvReader;
        try {
            csvReader = new BufferedReader(new FileReader(path));
            // No content found
            if (csvReader.readLine().length() == 1) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Content found
        return false;
    }

    // Reads the CSV file and stores lines into an ArrayList
    public static ArrayList<String> readFile(String path) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader csvReader = new BufferedReader(new FileReader(path));
        // Read line by line
        String data = csvReader.readLine();
        while (data != null) {
            // Store line in ArrayList
            list.add(data);
            // Next line
            data = csvReader.readLine();
        }
        csvReader.close();
        return list;
    }

    // Generate difference between both ArrayLists
    public static ArrayList<String> generateDifference(ArrayList<String> list1, ArrayList<String> list2) {
        // Create copies of both lists
        ArrayList<String> difference1 = new ArrayList<>(list1);
        ArrayList<String> difference2 = new ArrayList<>(list2);
        // Get differences
        difference1.removeAll(list2);
        difference2.removeAll(list1);
        // Get a list that has the difference of both lists
        difference1.addAll(difference2);
        return difference1;
    }

    // Case to write if one CSV input is blank
    public static void writeFileBlank(boolean blank1, boolean blank2, String path1, String path2, String pathOutput) {
        ArrayList<String> content = new ArrayList<>();
        // File 1 is blank
        if (blank1) {
            try {
                // Read and write entire File 2 content to output
                content = readFile(path2);
                writeFile(content, pathOutput);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // File 2 is blank
        else if (blank2) {
            try {
                // Read and write entire File 1 content to output
                content = readFile(path1);
                writeFile(content, pathOutput);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Both files blank
        else {
            writeFile(content, pathOutput);
        }
    }

    // Writing to CSV output file
    public static void writeFile(ArrayList<String> list, String path) {
        try {
            // Create file to write to
            // DO NOT CHANGE THIS FILE PATH
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter output = new BufferedWriter(fileWriter);
            // Write lines into file
            for (int i = 0; i < list.size(); i++) {
                String data = String.valueOf(list.get(i));
                output.write(data);
                output.write("\n");
            }
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

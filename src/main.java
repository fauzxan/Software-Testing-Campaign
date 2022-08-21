import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Scanner;

public class main {

    public static HashMap<String, String> createHash(File file) throws Exception{ //  this method has a time complexity of O(n), where n is the number of entries in the one of the files
        HashMap<String, String> entry = new HashMap<>();
        Scanner sc = new Scanner(file);
        int counter = 0;
        while (sc.hasNext()){
            if (counter<3){
                counter++;
                sc.next();
            }
            else {
                String line = sc.next();
                // System.out.println(line);
                // Key: is the ID of each row- this is the unique identifier
                // Value: the entire line to be compared
                try {
                    entry.put(line.split(",")[0], line);
                }
                catch(Exception err){
                    System.out.println(err);
                }
            }

        }
        sc.close();
        return entry;
    }

    public static int compareInputs(File file1, File file2) throws Exception{
        File file = new File ("./output.csv");
        FileWriter fileWriter = new FileWriter(file);

        // you still need header in the second file
        String header = "Customer ID#,Account No.,Currency,Type,Balance\n";
        fileWriter.append(header);

        // some non-important variables, used to display some cool stats!
        int count = 0;
        int lineNumber = -1;
        int numberOfIterations = 0;

        // creation of hashmap for quick retrieval of entries
        // the creation of hashmap is purely to improve time complexity.
        // accessing and checking elements takes O(1) time.
        // other method includes brute force search
        // If you are interested in the brute force search, then you can have a look at the previous commits- namely draft1 and draft2
        HashMap<String, String> file1Entries = createHash(file1);

        Scanner sc2 = new Scanner(file2);
        for (int explainLater = 0; explainLater<3; explainLater++){
            sc2.next();
        }
        while (sc2.hasNext()){

            String lineFromSecondFile = sc2.next();
            String lineKey = lineFromSecondFile.split(",")[0]; // basically the ID, which uniquely identifies each row

            // checking if the key even exists on the other file
            if (!file1Entries.containsKey(lineKey)){
                System.out.println("\nKey "+ lineKey+ " does not exist in the second file at all! Therefore, it's not even a mismatch lmao so I cannot put it into the new file even\n");
                break;
            }
            // if the key does exist, then...
            else {
                // ...check if the entries match. If they do not, then append it into the new csv file.
                String lineFromHashMap = file1Entries.get(lineKey);
                if (!lineFromHashMap.equals(lineFromSecondFile)){
                    // multiple \n included to improve legibility
                    fileWriter.append(lineFromSecondFile);
                    fileWriter.append("\n");
                    fileWriter.append(lineFromHashMap);
                    fileWriter.append("\n");
                    System.out.println("\nMismatched line on row "+ lineNumber + " of the second file entered!\n" + lineFromSecondFile + "\n and \n"+ lineFromHashMap);
                    count++;
                }
            }
            numberOfIterations++;
            lineNumber++;
        }
        sc2.close();
        // to gauge how many lines are not matching
        System.out.println("\nNumber of mismatches: " + count);
        System.out.println("\nNumber of iterations: "+ numberOfIterations);
        //closing statements
        fileWriter.flush();
        fileWriter.close();
        return count;
    }

    public static void main(String[] args) {

        /*
        TODO: Enter your own files into the following variables: file1 and file2
        */

        File file2 = new File ("C:\\Users\\fauza\\Desktop\\School stuff\\Elements of Software Construction\\Software Testing Campaign\\Software-Testing-Campaign\\sample_file_3.csv");
            File file1 = new File ("C:\\Users\\fauza\\Desktop\\School stuff\\Elements of Software Construction\\Software Testing Campaign\\Software-Testing-Campaign\\sample_file_1.csv");
        try {
            int num = compareInputs(file1, file2);
        }catch(Exception e){
            System.out.println("either file not found or the file 'output.csv' is open in another tab!");
            System.out.println(e);
        }
    }
}

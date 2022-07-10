import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Scanner;

public class main {

    public static void compareInputs(File file1, File file2) throws Exception{
        Scanner sc1 = new Scanner(file1);
        Scanner sc2 = new Scanner(file2);

        File file = new File ("./output.csv");
        FileWriter fileWriter = new FileWriter(file);
        String header = "Customer ID#,Account No.,Currency,Type,Balance\n";
        fileWriter.append(header);

        int count = 0;
        int lineNumber = -1;

        while(sc1.hasNext() && sc2.hasNext()){

            // read the respective lines
            // I am making use of line and line.split() separately because:
            // 1. line1 will be directly appended into the fileWriter
            // 2. line1Split will be used for comparison
            String line1 = sc1.next();
            String[] line1Split = line1.split(",");
            String line2 = sc2.next();
            String[] line2Split = line2.split(",");

            // compare each line to see if they are different
            if (line1Split[line1Split.length-1].compareTo(line2Split[line2Split.length-1]) != 0) { // checking to see if the last letter of each line does not match
                System.out.println("\nMismatch on line " + lineNumber+ "!");
                System.out.println("Mismatched line (also appended into output.csv): " + line1);
                count++; // number of mismatches
                // adding an extra line for legibility!
                fileWriter.append("\n");
                fileWriter.append(line1);
                fileWriter.append("\n");
            }

            lineNumber++;// line number to indicate the line on which the mismatch occured
        }

        // to gauge how many lines are not matching
        System.out.println("\nNumber of mismatches: " + count);

        //closing statements
        fileWriter.flush();
        fileWriter.close();
        sc1.close();
        sc2.close();
    }

    public static void main(String[] args) {

        File file2 = new File ("C:\\Users\\fauza\\Desktop\\School stuff\\Elements of Software Construction\\Software Testing Campaign\\Software-Testing-Campaign\\sample_file_3.csv");
        File file1 = new File ("C:\\Users\\fauza\\Desktop\\School stuff\\Elements of Software Construction\\Software Testing Campaign\\Software-Testing-Campaign\\sample_file_1.csv");
        try {
            compareInputs(file1, file2);
        }catch(Exception e){
            System.out.println("either file not found or the file 'output.csv' is open in another tab!");
        }
    }
}

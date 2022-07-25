import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static org.junit.Assert.*;


public class Tests {

    private File file1, file2, file3;
    private String header;
    private Path workingDir;
    private Path file_1;
    private Path file_2, output, outputP;
    private String content1, content2, content3, content4;

    @Before
    public void setup() throws Exception{
        System.out.println("Setting up variables using @Before...");
        file1 = new File("abs.exe\\DEskTop\\input.csv");
        file2 = new File("C:\\Users\\fauza\\Desktop\\School stuff\\Elements of Software Construction\\Software Testing Campaign\\Software-Testing-Campaign\\sample_file_3.csv");
        file3 = new File("C:\\Users\\fauza\\Desktop\\School stuff\\Elements of Software Construction\\Software Testing Campaign\\Software-Testing-Campaign\\sample_file_1.csv");
        header = "Customer ID#,Account No.,Currency,Type,Balance";
        workingDir = Path.of("", "C:\\Users\\fauza\\Desktop\\School stuff\\Elements of Software Construction\\Software Testing Campaign\\Software-Testing-Campaign");
        file_1 = workingDir.resolve("sample_file_1.csv");
        file_2 = workingDir.resolve("sample_file_2.csv");
        output = workingDir.resolve("sample_file_output_comparing_1_and_3_scenario2.csv");
        outputP = workingDir.resolve("output.csv");
        content1 = Files.readString(file_1);
        content2 = Files.readString(file_2);
        content3 = Files.readString(output);
        content4 = Files.readString(outputP);
    }

    // System test: this checks if the path is entered correctly
    // we know that this.file1 is incorrect, and this.file2 is correct. We need to know that the system checks for this error.
    @Test
    public void pathTest(){

        assertThrows(FileNotFoundException.class, () -> {main.createHash(file1);});
        System.out.println("File not found exception correctly thrown for createHash method");

        assertThrows(FileNotFoundException.class, () -> {main.compareInputs(file1, file2);});
        System.out.println("File not found exception correctly thrown for compareInput method");
    }

    // Unit test: this checks for the similarity in header rows. We know that the code won't work if the headers are dissimilar
    @Test
    public void headerCheck() throws Exception{
        // set file names


        // extract header for file_1

        String headerFile_1 = content1.split("\n")[0]; // this is the header of the first file

        // extract header for file_2
        String headerFile_2 = content2.split("\n")[0]; // this is the header of the second file

        //validate file1's header
        assertEquals(header, headerFile_1.strip());
        System.out.println("\nThe header from file 1 is correct! ");

        //validate file2's header
        assertEquals(header, headerFile_2.strip());
        System.out.println("The header from file 2 is also correct! Therefore, they should be comparable");
    }

    // Unit test: this checks if the data entered is of the correct data type (as in everything is string, but the numbers' field should only have numbers, and not some random letters, etc.)
    // In the given format, we know:
    //      Type field should only contain String values
    //      Balance field should only be numbers
    // Hence, we will be checking for these two mainly:
    @ Test(expected = Test.None.class)
    public void checkDT() throws Exception{

        String [] content1Split = content1.split("\n");
        String [] content2Split = content2.split("\n");

        for (int i=1; i<content1Split.length; i++){

            String [] furtherSplit1 = content1Split[i].split(",");
            String [] furtherSplit2 = content2Split[i].split(",");

            // the following two lines should not throw an error
            assertTrue(Integer.valueOf(furtherSplit1[furtherSplit1.length-1].strip()) instanceof Integer);// if the value is not a number, i.e., if the value contains even one letter, an error will be thrown
            assertTrue(Integer.valueOf(furtherSplit2[furtherSplit2.length-1].strip()) instanceof Integer);

            assertTrue((furtherSplit1[furtherSplit1.length-2].strip()).equals("CURRENT") || (furtherSplit1[furtherSplit1.length-2].strip()).equals("SAVINGS") );
            assertTrue((furtherSplit2[furtherSplit2.length-2].strip()).equals("CURRENT") || (furtherSplit2[furtherSplit2.length-2].strip()).equals("SAVINGS"));
        }
        System.out.println("The data types are correct!");

    }

    // Unit test: this tests if the customer IDs are all unique.
    @Test
    public void checkUniqueCustomerIds(){
        String [] content1Split = content1.split("\n");
        String [] content2Split = content2.split("\n");


        ArrayList<String> f1 = new ArrayList<>();
        ArrayList<String> f2 = new ArrayList<>();

        for (int i = 1; i<content1Split.length; i++){
            String id1 = content1Split[i].split(",")[0];
            String id2 = content2Split[i].split(",")[0];

            if (f1.contains(id1)) {
                fail("non unique key found");
            }
            else{
                f1.add(id1);
            }
            if (f2.contains(id2)){
                fail("non unique key found");
            }
            else{
                f2.add(id2);
            }
        }
        System.out.println("The keys were all unique");
    }


    @After
    public void cleanUp(){
        System.out.println("Running Clean up tasks now...");
        file1 = null;
        file2 = null;
        file3 = null;
        header = null;
        workingDir = null;
        content1 = null;
        content2 = null;
        content3 = null;
        content4 = null;
        output = null;
        outputP = null;

    }

}

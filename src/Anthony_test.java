import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Anthony_test {

    private String file1, file2, outputFile, ColumnInput;


    @Before
    public void init() throws Exception{
        System.out.println("Setting up variables using @Before...");
        file1 = "C:\\Users\\fauza\\Desktop\\School stuff\\Elements of Software Construction\\Software Testing Campaign\\Software-Testing-Campaign\\sample_file_3.csv";
        file2 = "C:\\Users\\fauza\\Desktop\\School stuff\\Elements of Software Construction\\Software Testing Campaign\\Software-Testing-Campaign\\sample_file_1.csv";
        outputFile ="../output_anthony.csv";
        ColumnInput = "../columnExchange.csv";
    }

    public String ExchangeArray(String[] strArray){
        String temp = strArray[0];
        strArray[0] = strArray[strArray.length-1];
        strArray[strArray.length-1] = temp;
        String returnString = "";
        for (int i=0; i<strArray.length;i++){
            returnString+=strArray[i];
        }
        return returnString;
    }

    public void ColumnExchange(String Path1) throws Exception{

        Scanner sc1 = new Scanner(new File (Path1));
        FileWriter returnFile = new FileWriter (ColumnInput);
        String appendValue;
        String newHeader = "Balance,Account No.,Currency,Type,Customer ID#";
        returnFile.append(newHeader);
        while (sc1.hasNext()){
            String line = sc1.next();
            String [] split = line.split(",");
            appendValue = ExchangeArray(split);
            returnFile.append(appendValue);
        }
        returnFile.flush();
        returnFile.close();
    }

    public int countRows(String path) throws Exception{
        int count = 0;
        Scanner sc = new Scanner(new File (path));
        while (sc.hasNext()){
            String line = sc.next();
            count++;
        }
        return count;
    }

    /*
    Exchanges the columns of file2. Then feeds the updated column as input to Anthony's Solution, with the other argument as file2.
    It returns true if system(file1, ColumnInput, outputFile) == system(file1, file1, outputFile)
     */
    @Test
    public void ColumnExchangeTest() throws Exception{

        ColumnExchange(file1);
        String mutatedFile1 = ColumnInput;
        // ColumnInput: contains the mutated version of file1, with columns exchanged
        // file1: the original file that contains an altered version in ColumnInput

        /*
        TODO: Play around by changing the values passed as inputs into Anthony_Sol(). But do not change outputFile. Expected: We expect the assertion to pass, even after mutating the file.
        If the assertion fails, I have managed to find a bug in his code!
         */
        Anthony_Sol.system(file1,mutatedFile1 , outputFile);
        int count1 = countRows(outputFile);
        Anthony_Sol.system(file1, file1 , outputFile);
        int count2 = countRows(outputFile);
        assert (count1 == count2);
        System.out.println("Exchanging the columns produces the same output!!");
    }
}

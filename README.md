# Software-Testing-Campaign

#### Student ID: 1005404
#### Done by Mohammed Fauzaan, Cohort 4

##### The code has been well documented, with comments and steps taken to improve time complexity :)

### Steps to view functionality:
<br />
1. Make a copy of the file on your local device: <br />
<t /><t /> a. Clone the repository using github Desktop or command line, or<br />
<t /><t /> b. Download the zip file and extract the folder into a folder of your choice<br />
<br />
2. Open the code in your IDE. The code consists of a single class-  ./src/main.java<br /><br />
3. In order to test the code you need to input the correct file path into the file1 and file2 variables in public static void main(String args[])<br />

##### You only need to modify these two lines in ./src/main.java

```
File file1 = new File("__enter filepath for the first CSV file you want to test__");
File file2 = new File("__enter filepath for the second CSV file you want to test__");
```

<br />
<br />
4. Run the code and see the output terminal to see how many lines are mismatched<br /><br />
5. Open the folder where you extracted the files to see the output stored in output.csv

### Use case and Misuse case diagram
##### You can view these in the following directory:
./Use cases_and_Misuse cases
<br/>

### Equivalence class partitioning 
|   |Invalid   |Valid   |   
|---|----------|--------|
|Path to csv file | Path is entered incorrectly | Path must be entered correctly: 'pathtodir//csvfilename.csv' (including the escape sequence for the forward slash) | 
|Data types in csv file |Data type of a cell is incorrect/ different from the data type of the corresponding cell in file2|All data types must be of string type|      
|Header row in both the files|Header row!= ["Customer ID#","Account Number", "Currency", "Type", "Balance"]|Header row == ["Customer ID#","Account Number", "Currency", "Type", "Balance"]|     
|Cutomer ID #|-| Customer ID must be unique within a given file and must have a corresponding copy in the other file|

### Boundary value analysis


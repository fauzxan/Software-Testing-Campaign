# Software-Testing-Campaign
## Week 8
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

## Week 9

### Equivalence class partitioning 
|   |Invalid   |Valid   |   
|---|----------|--------|
|Path to csv file | Path is entered incorrectly | Path must be entered correctly: 'pathtodir//csvfilename.csv' (including the escape sequence for the forward slash) | 
|Data types in csv file |Data type of a cell is incorrect/ different from the data type of the corresponding cell in file2|All data types must be of string type|      
|Header row in both the files|Header row!= ["Customer ID#","Account Number", "Currency", "Type", "Balance"]|Header row == ["Customer ID#","Account Number", "Currency", "Type", "Balance"]|     
|Cutomer ID #|-| Customer ID must be unique within a given file and must have a corresponding copy in the other file|

### Boundary value analysis

|   |Invalid   |Valid   |   
|---|----------|--------|
|Middle value|File file1 = "abc.exs/DEsktoP/%xyz//csv.csv"| File file1 = "C://Users//%USERNAME//file1.csv"|
|Boundary value|File file1 = "C:/Users/%USERNAME%/file1.csv"| Same as above|
|Reason|The file name entered is of String format, so the forward slashes must be use escape character as "//". Also the path and file name should be correct and should exist as a real directory|-|
<br />
2. Data Types <br/>
|   |Invalid   |Valid   |   
|---|----------|--------|
|Middle value| file1 contains a row "ID1", "BOS963211", $US , savings, 304932 <br/> and file2's corresponding row "ID1", "BOS963211", USD , "savings", 30493dc |"ID1", "BOS963211", "USD" , "SAVINGS", "304932"|
|Boundary value|||
|Reason|||

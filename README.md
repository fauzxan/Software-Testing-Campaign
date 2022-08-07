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
##### 1. Path
|   |Invalid   |Valid   |   
|---|----------|--------|
|Middle value|File file1 = "abc.exs/DEsktoP/%xyz//csv.csv"| File file1 = "C://Users//%USERNAME//file1.csv"|
|Boundary value|File file1 = "C:/Users/%USERNAME%/file1.csv"| Same as above|
|Reason|The file name entered is of String format, so the forward slashes must be use escape character as "//". Also the path and file name should be correct and should exist as a real directory|-|
##### 2. Data types 
|   |Invalid   |Valid   |   
|---|----------|--------|
|Middle value| file1 contains a row ["ID1", "BOS963211", $US , savings, 304932]  and file2's corresponding row ["ID1", "BOS963211", USD , "savings", 30493dc] |Both files should contain rows of the following type: ["ID1", "BOS963211", "USD" , "SAVINGS", "304932"]|
|Boundary value|file 1 contains ["ID1", "BOS963211", "USD" , "SAVINGS", 304932]  and file 2's corresponding row contains ["ID1", "BOS963211", "USD" , "SAVINGS", "304932"]|same as above|
|Reason|The data type of the two corresponding rows in the two files must match. One cannot be of String type while the other is of Number type (Excel data types)||
##### 3. Header row
|   |Invalid   |Valid   |   
|---|----------|--------|
|Middle value|Header row = ["Food", "Vitamins", "Water content", "Season grown"]| Header row = ["Customer ID#","Account Number", "Currency", "Type", "Balance"]|
|Boundary value|Header row = ["Account Number","Customer ID#", "Currency", "Type", "Balance", "Income"]|Header row = ["Customer ID#","Account Number", "Currency", "Type", "Balance"]|
|Reason|Although my code can overcome the issue of mismatched/ incorrect header row, the number of header columns and the order matters as I use .split(",") and indexing to retrieve cells to use as key values in hash mapping. So the ordering of the columns matters||
##### 4. Customer ID# 
|   |Invalid   |Valid   |   
|---|----------|--------|
|Middle value| All the rows have the same Customer ID# value| Each customer has a unique customer ID|
|Boundary value| Customer ID value from two differnt rows of the same file matches. E.g., two rows have Customer ID# = ID23| Must be different|
|Reason|My implementation makes use of a Hash Map, so the Customer ID# needs to be unique| - |

## Fuzzer implementation
#### What does the fuzzer output?
The fuzzer's output is an input csv whose rows contain ID number, followed by a randomly generated line. <br/>
#### What does the fuzzer test?
The test is as follows: <br/>
There is a method called mutateCSV(File file, int errno): this method mutates a maximum of "n" fields in the input csv, where 
n = errno. The method also keeps track of the number of mutations created (because the errno only provides the upper bound on the number of mutations, not the exact amount) <br/>
If at any point, the number of mutations is not equal to the number of mismatches, flag will be set to 1, and an <br/>
error message will be printed out at the end of fuzzer method.  

#### What errors did the fuzzer find?
The fuzzer was able to find the following errors, that have now been fixed:<br/>
1. The first few lines of the csv are not read correcly. 
2. The closing of some write streams were not done correctly

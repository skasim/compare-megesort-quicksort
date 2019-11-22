# Lab 4 - Sorting Algorithm Runtime Analysis by Samra Kasim
Program analyzes the run time of four types of Quicksort algorithms and a Natural Merge sort algorithm. The latter uses
a linked list. All sorts are recursive.

## To Run the program
* In terminal, `cd` to the `src` directory in the project directory structure.
* To compile the program run `javac $(find . -name '*.java')` from the `src` directory.
* To run the program, make sure to be in the `src` directory and run:
` java com.sk.sorts.Sorts ../input/ ../output/ run_file.txt 100` to process the input files in the `input` directory,
output the files to the `output`, create a file that is in human readable format with run times called `run_file.txt` (a csv file
will also be written using the same name but with a `.csv` appended), and each algorithm will be run `100` times.


## Errata
* If providing comments in the input file, make sure they are preceded by `//`.
* The write to output file appends to the last entry, so if generating a new file, delete the older version or update the output filename.
* The two input files are provided in the `input` directory. Please only use the same file naming convention as found in the lab assignment.
* The output files are in the `output` directory.
* For input, only use input files where the numbers are separated by newline
* Java version 1.8 and IDE is IntelliJ


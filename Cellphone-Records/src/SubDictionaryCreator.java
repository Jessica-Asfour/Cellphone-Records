// -----------------------------------------------------
// Assignment 4
// Question: Part 1
// Written by: Jessica Asfour 40029395
// -----------------------------------------------------

/**
 * Jessica Asfour 40029395
 * COMP249
 * Assignment 4 - Part 1
 * Due date: Sunday April 19, 2020
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * The Dictionary Creator program reads through any text file and creates a Sub-Dictionary
 */
public class SubDictionaryCreator {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in); //Uses Scanner class and creates a scanner called keyboard
        System.out.println("Welcome to the \"Dictionary Creator\"!");
        System.out.print("Please enter the name of a file to get a Sub-Dictionary: "); //Prompts the user to enter a file name they want to open
        String fileToReview = keyboard.next();
        Scanner readFile = null;

        try {
            readFile = new Scanner(new FileInputStream(fileToReview));//Uses Scanner class to read from an input file
            ArrayList<String> arrayList = new ArrayList<>(); //Creates the array list

            while (readFile.hasNextLine()) { //Reads through the file until the end
                String line = readFile.nextLine(); //nextLine() method used to move to the next line
                String[] words = line.split(" "); //Creates an array which stores each word split after a space


                for (int i = 0; i < words.length; i++) { //Goes through every word in the array
                    boolean validOneLetterWord = true;
                    String thisWord = words[i];
                    String updatedWord = thisWord.toUpperCase(); //Takes the word and puts it in uppercase

                    if (updatedWord.length() > 1) { //Checks if a word has a 's or 'm at the end of it
                        String thisSubString = updatedWord.substring(updatedWord.length() - 2); //Takes the last 2 characters of a string
                        if (thisSubString.equals("'S") || thisSubString.equals("'M")) { //Checks if the last two characters are 's or 'm and replaces them with a space
                            String updatedWord2 = updatedWord.replace(thisSubString, "");
                            updatedWord = updatedWord2; //Sets it to the updated word variable
                        }
                    }
                    updatedWord = updatedWord.replaceAll("\\p{Punct}", ""); //Removes all punctuations

                    if (updatedWord.length() == 1) { //Checks if the words are either A or I and keeps them in the list
                        if (updatedWord.equals("A")) {
                            validOneLetterWord = true;
                        } else if (updatedWord.equals("I")) {
                            validOneLetterWord = true;
                        } else {
                            validOneLetterWord = false;
                        }
                    }

                    arrayList.sort(String:: compareToIgnoreCase); //Sorts the strings in the array list to be in alphabetical order

                    boolean noNumInWord;
                    noNumInWord = wordContainsNumber(updatedWord); //Calls the wordContainsNumber method

                    if (!arrayList.contains(updatedWord) && validOneLetterWord && noNumInWord == true) { //Adds the formatted words in the array list
                        arrayList.add(updatedWord);
                    }

                }
            }

            int numberOfEntries = arrayList.size(); //Outputs the size of the array list
            boolean stringWasPrinted = false;

            for (int ascii = 65; ascii >= 65 && ascii <= 90; ascii++) {
                char letter = (char) ascii;
                boolean oneLetterIsDone = false;
                for (String element : arrayList) {
                    writeToTextFile(element, letter, oneLetterIsDone, numberOfEntries, stringWasPrinted); //Calls the method to write to a file
                    oneLetterIsDone = true;
                    stringWasPrinted = true;
                }
            }

        } catch (FileNotFoundException e) { //Exception
            System.out.println("This file doesn't exist! The program will now terminate");
        } finally {
            if (readFile != null) {
                readFile.close(); //Closes input stream
            }
        }
    }

    /**
     * Method that checks if there is a number if the string before read
     * Breaks down the word into characters and stores them into a character array
     * Goes through the array to check if any of the characters are numbers using ascii values
     * @param s
     * @return
     */
    public static boolean wordContainsNumber(String s) { //Checks if a string contains a number
        boolean noNumInWord = false;

        char[] c = new char[s.length()];
        char charAtIndex;
        for (int i = 0; i < c.length; i++) { //Stores characters of the string into the indexes
            c[i] = s.charAt(i);
            charAtIndex = s.charAt(i);
            int integerValueOfChar = charAtIndex;
            if (integerValueOfChar >= 65 && integerValueOfChar <= 90 || s.equals("MC²")) {
                noNumInWord = true;
            } else {
                noNumInWord = false;
                break;
            }
        }
        return noNumInWord;
    }

    /**
     * Method writes to the text file
     * Takes care of some formatting
     * @param string
     * @param letter
     * @param oneLetterIsDone
     * @param numberOfEntries
     * @param entriesStringWasPrinted
     */
    public static void writeToTextFile(String string, char letter, boolean oneLetterIsDone, int numberOfEntries, boolean entriesStringWasPrinted) {
        PrintWriter writeFile = null;  //Use the PrintWriter class to write to a file
        try {
            writeFile = new PrintWriter(new FileOutputStream("SubDictionary2.txt", true)); //Open the file output stream

            if (entriesStringWasPrinted == false) {
                writeFile.print("The document produced this sub-dictionary, which includes " + numberOfEntries + " entries."); //Prints the number of entries
            }

            if (oneLetterIsDone == false) { //Used to print out the letter in the dictionary
                writeFile.println("\n" + letter + "\n==");
            }

            if (string.charAt(0) == letter) {
                writeFile.println(string);
            }

        } catch (FileNotFoundException e) {
            System.out.println("This file doesn't exist! The program will now terminate");
        } finally {
            if (writeFile != null) {
                writeFile.close(); //Closes output stream
            }
        }
    }
}

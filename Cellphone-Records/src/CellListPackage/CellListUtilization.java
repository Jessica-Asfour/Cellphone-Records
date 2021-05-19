// -----------------------------------------------------
// Assignment 4
// Question: Part 2
// Written by: Jessica Asfour 40029395
// -----------------------------------------------------

/**
 * Jessica Asfour 40029395
 * COMP249
 * Assignment 4
 * Due date: Sunday April 19, 2020
 */

package CellListPackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The Cellphone program creates a link list of cellphones and allows the user to perform several actions to the list
 */
public class CellListUtilization {

    public static void main(String[] args) {

        CellList firstCellList = new CellList(); //Creation of 2 empty cellphone lists
        CellList secondCellList = new CellList();
        Scanner readFile = null;

        try {
            readFile = new Scanner(new FileInputStream("Cell_Info.txt")); //Uses Scanner class to read from an input file

            while (readFile.hasNextLine()) { //Reads through the file until the end
                long serialNumber = readFile.nextLong();
                String brand = readFile.next();
                double price = readFile.nextDouble();
                int year = readFile.nextInt();
                CellPhone newCellPhone = new CellPhone(serialNumber, brand, year, price);

                if (firstCellList.contains(serialNumber)) { //contains method test also uses the find function
                    continue;                               //Makes sure there are no duplicate objects in the list
                } else {
                    firstCellList.addToStart(newCellPhone); //addToStart method test and intializes the firstCellList list
                    secondCellList.addToStart(newCellPhone); //adding all the same object to test the equals method in case 8
                }
            }

            System.out.println("Welcome to the \"The Cellphone Program\"! Created by Jessica Asfour"); //Welcome Banner
            System.out.println("Current size of the list is " + firstCellList.size() + ". Here's what you can do to your list");
            System.out.println("===================================================================");

            Scanner keyboard = new Scanner(System.in);

            int choice = 0;

            while (choice != 9) { //While loop used to output the menu after every action until they quit

                System.out.println("\nWhat do you want to do to your list? ");
                System.out.println("\t 1. Show list of all cellphones ");
                System.out.println("\t 2. Print out information of a phone ");
                System.out.println("\t 3. Insert a new cellphone ");
                System.out.println("\t 4. Delete a cellphone ");
                System.out.println("\t 5. Delete the first cellphone ");
                System.out.println("\t 6. Replace a cellphone from the list ");
                System.out.println("\t 7. Create a duplicate cell list ");
                System.out.println("\t 8. Check if both lists are equal ");
                System.out.println("\t 9. Quit ");
                System.out.print("Please enter your choice> ");

                choice = keyboard.nextInt();
                System.out.println("");

                switch (choice) {
                    case 1: //Outputs the content in the list
                        firstCellList.showContents(); //showContents method test
                        break;
                    case 2: //Looks for the phone with a desired serial number
                        System.out.print("Please enter a serial number: ");
                        long serialNumEnteredByUser = keyboard.nextLong();

                        firstCellList.cellPhoneWithSerialNum(serialNumEnteredByUser);

                        if (firstCellList.contains(serialNumEnteredByUser) == false) {
                            System.out.println("This serial number doesn't exist! Going back to the main menu");
                        }
                        break;
                    case 3: //Tests the insertAtInedex method
                        boolean isAnswerNo = false;

                        while (!isAnswerNo) { //Prompts the user to enter a new cellphone every time
                            System.out.print("Would you like to enter a new cellphone? Type yes or no --> ");
                            String answer = keyboard.next();
                            if (answer.equals("yes")) {
                                CellPhone cellPhone = new CellPhone();
                                System.out.print("Enter the serial number: ");
                                long serialNumber = keyboard.nextLong();
                                cellPhone.setSerialNum(serialNumber);
                                System.out.print("Enter the brand: ");
                                String brand = keyboard.next();
                                cellPhone.setBrand(brand);
                                System.out.print("Enter the year it was made: ");
                                int year = keyboard.nextInt();
                                cellPhone.setYear(year);
                                System.out.print("Enter the price: ");
                                double price = keyboard.nextDouble();
                                cellPhone.setPrice(price);
                                System.out.print("Enter a number to store this phone in the list: ");
                                int index = keyboard.nextInt();
                                System.out.println("");
                                firstCellList.insertAtIndex(cellPhone, index); //insertAtIndex method test
                                System.out.println("Size of the list after inserting a new cellphone " + firstCellList.size() + ". Here is the updated list: ");
                                firstCellList.showContents();
                                System.out.println("\nThis was the cellphone we added:");
                                firstCellList.cellPhoneWithSerialNum(serialNumber);
                            } else {
                                System.out.println("No more phones will be added!");
                                isAnswerNo = true;
                            }
                        }
                        break;
                    case 4: //Tests the deleteAtIndex method
                        System.out.print("Enter the index of the cellphone you want to delete: ");
                        int index = keyboard.nextInt();
                        firstCellList.deleteFromIndex(index); //deleteFromIndex method test
                        System.out.println("Size of the list after deleting a cellphone " + firstCellList.size() + ". Here is the updated list: ");
                        firstCellList.showContents();
                        break;
                    case 5: //Tess the deleteFromStart method
                        System.out.println("The first cellphone in the list will now be deleted");
                        firstCellList.deleteFromStart(); //deleteFromStart method test
                        System.out.println("Size of the list after deleting the first cellphone " + firstCellList.size() + ". Here is the updated list: ");
                        firstCellList.showContents();
                        break;
                    case 6: //Tests the replaceAtIndex method
                        System.out.println("New cellphone info ");
                        CellPhone cellPhone = new CellPhone();
                        System.out.print("Enter the serial number: ");
                        long serialNumber = keyboard.nextLong();
                        cellPhone.setSerialNum(serialNumber);
                        System.out.print("Enter the brand: ");
                        String brand = keyboard.next();
                        cellPhone.setBrand(brand);
                        System.out.print("Enter the year it was made: ");
                        int year = keyboard.nextInt();
                        cellPhone.setYear(year);
                        System.out.print("Enter the price: ");
                        double price = keyboard.nextDouble();
                        cellPhone.setPrice(price);
                        System.out.print("Enter a number to store this phone in the list: ");
                        int index2 = keyboard.nextInt();
                        firstCellList.replaceAtIndex(cellPhone, index2); //replaceAtIndex method test
                        System.out.println("Size of the list after replacing the phone " + firstCellList.size() + ". Here is the updated list: ");
                        firstCellList.showContents();
                        System.out.println("\nThis was the cellphone we added:");
                        firstCellList.cellPhoneWithSerialNum(serialNumber);
                        break;
                    case 7: //Checks if the copy constructor works
                        CellList thirdCellList = new CellList(firstCellList); //Checks copy constructor
                        System.out.println("This is the third list: ");
                        thirdCellList.showContents();
                        break;
                    case 8:
                        if (firstCellList.equals(secondCellList) == true) { //Equals method test
                            System.out.println("Yes they are!");
                        } else {
                            System.out.println("No they are not!");
                        }
                        break;
                    case 9: //Quit condition
                        System.out.println("Thank you for using \"The Cellphone Program\"!");
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("This file doesn't exist! The program will now terminate");
        } finally {
            if (readFile != null) {
                readFile.close(); //Closes input stream
            }
        }
    }
}

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

import java.util.Objects;
import java.util.Scanner;

public class CellPhone implements Cloneable {

    private long serialNum;
    private String brand;
    private int year;
    private double price;

    //Mutator methods
    /**
     * Mutator method for the serial number
     * @param serialNum
     */
    public void setSerialNum(long serialNum) {
        this.serialNum = serialNum;
    }

    /**
     * Mutator method for the brand
     * @param brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Mutator method for the year
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Mutator method for the price
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    //Accessor methods
    /**
     * Accessor method for the serial number
     * @return
     */
    public long getSerialNum() {
        return serialNum;
    }

    /**
     * Accessor method for the brand
     * @return
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Accessor method for the year
     * @return
     */
    public int getYear() {
        return year;
    }

    /**
     * Accessor method for the price
     * @return
     */
    public double getPrice() {
        return price;
    }

    public CellPhone() { //Default constructor
        serialNum = 0;
        brand = null;
        year = 0;
        price = 0.0;
    }

    /**
     * Parameterized constructor
     * @param serialNum
     * @param brand
     * @param year
     * @param price
     */
    public CellPhone(long serialNum, String brand, int year, double price) { //Parameterized constructor
        this.serialNum = serialNum;
        this.brand = brand;
        this.year = year;
        this.price = price;
    }

    /**
     * Copy constructor
     * @param cellPhone
     * @param serialNumber
     */
    public CellPhone(CellPhone cellPhone, long serialNumber) { //Copy constructor
        brand = cellPhone.brand;
        year = cellPhone.year;
        price = cellPhone.price;
    }

    /**
     * toString method
     * @return
     */
    @Override
    public String toString() { //toString method
        return ("Serial number: " + serialNum + "\nBrand: " + brand + "\nPrice: " + price + "\nYear: " + year);
    }

    /**
     * Equals method
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) { //Equals method
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CellPhone cellPhone = (CellPhone) o;
        return year == cellPhone.year && Double.compare(cellPhone.price, price) == 0 && Objects.equals(brand, cellPhone.brand);
    }

    private CellPhone thisCellPhone;


    /**
     * Clone method
     * Use clone to maintain encapsulation
     * Methods that pass by an object might have a privacy leak for example in the case for copy constructors
     * @return
     */
    public Object clone() { //Clone method for deep copy
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Enter a serial number: ");
            long serialNumber = keyboard.nextLong();

            thisCellPhone.setSerialNum(serialNumber);

            CellPhone copy = (CellPhone) super.clone();
            copy.thisCellPhone = (CellPhone) thisCellPhone.clone();
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}

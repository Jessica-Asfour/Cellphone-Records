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

import java.util.NoSuchElementException;

public class CellList {

    private class CellNode implements Cloneable { //private CellNode inner class to avoid privacy leaks

        private CellPhone cellPhone;
        private CellNode cellNode;

        public CellNode() { //Default constructor
            cellPhone = null;
            cellNode = null;
        }

        /**
         * Parameterized constructor
         * @param cellPhone
         * @param cellNode
         */
        public CellNode(CellPhone cellPhone, CellNode cellNode) { //Parameterized constructor
            this.cellPhone = cellPhone;
            this.cellNode = cellNode;
        }

        /**
         * Copy constructor
         * @param thisCellNode
         */
        public CellNode(CellNode thisCellNode) { //Copy Constructor
            cellPhone = thisCellNode.cellPhone;
            cellNode = thisCellNode.cellNode;
        }

        private CellNode thisCellNode;

        /**
         * Clone method
         * Use clone to maintain encapsulation
         * Methods that pass by an object might have a privacy leak for example in the case for copy constructors
         * @return
         */
        public Object clone() { //Clone method
            try {
                CellNode copy = (CellNode) super.clone();
                copy.thisCellNode = (CellNode) thisCellNode.clone();
                return copy;
            } catch (CloneNotSupportedException e) {
                return null;
            }
        }
        //Accessor methods

        /**
         * Accessor method for the cellphone object
         * @return
         */
        public CellPhone getCellPhone() {
            return cellPhone;
        }

        /**
         * Accessor method for the cell node
         * @return
         */
        public CellNode getCellNode() {
            return cellNode;
        }

        //Mutator methods
        /**
         * Mutator method for the cellphone object
         * @return
         */
        public void setCellPhone(CellPhone cellPhone) {
            this.cellPhone = cellPhone;
        }

        /**
         * Mutator method for the cell node
         * @return
         */
        public void setCellNode(CellNode cellNode) {
            this.cellNode = cellNode;
        }
    } //End of inner class


    private int size = size();
    private CellNode head; //First node in the list

    /**
     * Size method returns the size of the cell list
     * @return
     */
    public int size() { //Size method
        int counter = 0;
        CellNode position = head;

        while (position != null) { //Uses the counter to count the number of nodes in the list
            counter++;
            position = position.cellNode;
        }
        return counter;
    }

    public CellList() { //Default Constructor
        head = null;
    }

    /**
     * Copy constructor
     * @param cellList
     */
    public CellList(CellList cellList) { //Copy constructor
        head = cellList.head;
    }

    /**
     * Method adds a node to the head of the list
     * @param cellPhone
     */
    public void addToStart(CellPhone cellPhone) {
        head = new CellNode(cellPhone, head); //Makes the head equal to the last cellphone object added
    }

    /**
     * Method adds a cellphone in a desired index in the cell list
     * Controls for all cases
     * @param cellPhone
     * @param index
     */
    public void insertAtIndex(CellPhone cellPhone, int index) {
        try {
            size = size();

            if (contains(cellPhone.getSerialNum()) == true) { //Checks if a cellphone with this serial number already exists
                System.out.println("A cellphone with this serial number already exists!");
                System.exit(0);
            }

            if (index >= 0 || index <= size - 1) { //Case 1: Checks if the index is within the size of the list
                if (head == null) { //Case 2: Checks if linked list is empty
                    System.out.println("The linked list is empty.");
                }
                CellNode newNode = head;
                CellNode node = newNode;

                if (index == 0) { //Case 3: If the user wants to enter a cellphone at the beginning of the list
                    head = new CellNode(cellPhone, head); //This new node becomes the head of the list
                    return;
                }

                if (index == size - 1 && newNode != null){ //Case 4: If the user wants to enter a cellphone at the end of the list
                    node = node.cellNode;
                }

                for (int i = 0; node != null && i < index - 1; i++) { //Case 5: If the user wants to enter a cellphone anywhere else in the list
                    node = node.cellNode;
                }

                CellNode thisNode = new CellNode(); //New node
                thisNode.cellPhone = cellPhone; //Node will carry the new cellphone
                thisNode.cellNode = node.cellNode;
                node.cellNode = thisNode;
                return;
            }
        } catch (NoSuchElementException e) {
            System.out.println("There is no cellphone at this index!");
            System.exit(0);
        }
    }

    /**
     * Method deletes a cellphone from a desired index
     * Controls for all cases
     * @param index
     */
    public void deleteFromIndex(int index) {
        try {
            size = size();
            if (index >= 0 && index <= size - 1) { //Case 1: Checks if the index is within the size of the list
                if (head == null) { //Case 2: Checks if linked list is empty
                    System.out.println("The linked list is empty.");
                    return;
                }
                CellNode node = head;

                if (index == 0){ //Case 3: If the user wants to delete the head
                    head = node.cellNode; //Head gets changed
                    return;
                }
                if (size == 1) { //Case 4: If the list size is equal to 1
                    return;
                }

                for (int i = 0; node != null && i < index - 1; i++) // Case 5: If the user wants to enter a cellphone anywhere else in the list
                    node = node.cellNode;                           //Finds previous node of the node to be deleted

                if (node == null || node.cellNode == null)
                    return;

                // CellNode node->next is the node to be deleted
                // Store pointer to the next of node to be deleted
                CellNode next = node.cellNode.cellNode;
                node.cellNode = next;  // Unlink the deleted node from list
            }
        } catch (NoSuchElementException e) {
            System.out.println("There is no cellphone at this index!");
            System.exit(0);
        }
    }

    /**
     * Method deletes a cellphone from the beginning of the list i.e., the head of the list
     * @return
     */
    public boolean deleteFromStart() {
        if (head != null) {
            head = head.cellNode;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method replaces a cellphone at a desired location
     * Calls the delete and insert method for this action to take place
     * @param cellPhone
     * @param index
     */
    public void replaceAtIndex(CellPhone cellPhone, int index) {
        size = size();
        if (index >= 0 && index <= size - 1) { //Checks if the index is valid
            if (contains(cellPhone.getSerialNum()) == true) { //Checks if a cellphone with this serial number already exists
                System.out.println("A cellphone with this serial number already exists!");
                System.exit(0);
            }
            deleteFromIndex(index); //Calls the deleteFromIndex method and deletes the cellphone object at this index
            insertAtIndex(cellPhone, index); //Calls the insertAtIndex method and inserts the new cellphone object at this index
        }
        else{ //Returns if index is not valid
            return;
        }
    }

    /**
     * Find method looks for a desired cellphone by looking for the right serial number
     * Counts the number of iterations
     * @param serialNumber
     * @return
     */
    private CellNode find(long serialNumber) {
        CellNode position = head;
        CellPhone cellPhoneAtPosition;
        int count = 0;
        while (position != null) { //Goes through the list until it finds an object with the desired serial number
            cellPhoneAtPosition = position.cellPhone;
            long serialNumberAtPosition = cellPhoneAtPosition.getSerialNum();
            count++; //Keeps track of how many iterations were done to before the search ended
            if (serialNumberAtPosition == serialNumber) {
                return position; //Returns pointer if the desired object is found
            }
            position = position.cellNode;
        }
        return null; //Otherwise the method returns null
    }

    /**
     * Boolean method contains calls the find method and informs the user if the list contains a particular serial number
     * @param serialNumber
     * @return
     */
    public boolean contains(long serialNumber) {
        return (find(serialNumber) != null);
    }

    /**
     * Method used to print out the content in the list
     */
    public void showContents() {
        CellNode position = head;
        while (position != null) {
            System.out.println("[" + position.getCellPhone().getSerialNum() + ": " + position.getCellPhone().getBrand()
                    + " " + position.getCellPhone().getPrice() + " " + position.getCellPhone().getYear() + "]");
            position = position.getCellNode();
        }
    }

    /**
     * Equals method checks if two lists are the same or not
     * @param c
     * @return
     */
    @Override
    public boolean equals(Object c) {
        if (this == c) {
            return true;
        }
        if (c == null || getClass() != c.getClass()) {
            return false;
        }
        CellList cellList = (CellList) c;
        if (size() != cellList.size()) {
            return false;
        }
        CellNode position = head;
        CellNode otherPosition = cellList.head;
        while (position != null) {
            if (!(position.cellPhone.equals(otherPosition.cellPhone))) {
                return false;
            }
            position = position.cellNode;
            otherPosition = otherPosition.cellNode;
        }
        return true;
    }

    /**
     * Added method to print out the information of the phone with the desired serial number
     * @param serialNum
     * @return
     */
    public CellPhone cellPhoneWithSerialNum(long serialNum) {
        CellNode position = head;
        CellPhone cellPhoneAtPosition = null;
        int count = 0;
        while (position != null) {
            cellPhoneAtPosition = position.cellPhone;
            long serialNumberAtPosition = cellPhoneAtPosition.getSerialNum();
            count++; //Keeps track of how many iterations were done to before the search ended
            if (serialNumberAtPosition == serialNum) {
                System.out.println(cellPhoneAtPosition.toString());
                System.out.println("Number of iterations: " + count);
            }
            position = position.cellNode;
        }
        return null;
    }
}

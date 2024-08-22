/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project.actor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.NumberFormatException;
import java.util.ArrayList;
/**
 * @author Rishi
 *
 * Overview: This class handles all customer management operations, including loading customers from files,
 * retrieving customer information, creating and removing customers, and reading customer data from files.
 * This class is mutable as it allows for modification of the customer list.
 *
 * Abstraction Function:
 * - AF(customerList) = a list of customers stored in the customerList ArrayList.
 *
 * Rep Invariant:
 * - The customerList ArrayList must not be null.
 */
public class CustomerManager {
    private static final ArrayList<Customer> customerList = new ArrayList<>();
        /**
     * Effects:
     * - Loads customers from files located in the 'src/customers' directory.
     * 
     * Modifies:
     * - Customer list.
     * 
     * Requires:
     * - None
     */
    public final static void loadCustomers() {
        File folder = new File("src/customers");
        File[] files = folder.listFiles();

        try {
            if (files == null) { 
                throw new IOException();
            }
            for (File file : files) {
                if (file.isFile()) {
                    String[] data = CustomerManager.readCustomerFile(file.getAbsolutePath());
                    
                    if(data == null && data.length != 3) {
                        throw new IOException();
                    }
                    Customer customer = new Customer(data[0], data[1], Double.parseDouble(data[2]));
                    customerList.add(customer);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
            System.err.println("Files loading error");
        } 
    }
    /**
     * Effects:
     * - Retrieves the customer with the specified username.
     * 
     * Modifies:
     * - None
     * 
     * Requires:
     * - None
     * 
     * @param name The username of the customer to retrieve.
     * @return The Customer object if found, otherwise null.
     */
    public final static Customer getCustomer(String name) {
        for (Customer customer: customerList) {
            if(customer.getUsername().equals(name)) {
                return customer;
            }
        }
        return null;
    }
    /**
     * Effects:
     * - Retrieves the list of customers.
     * 
     * Modifies:
     * - None
     * 
     * Requires:
     * - None
     * 
     * @return The list of customers.
     */
    public final static ArrayList<Customer> getCustomers() {
        return customerList;
    }
    /**
     * Effects:
     * - Reads customer data from a file.
     * 
     * Modifies:
     * - None
     * 
     * Requires:
     * - None
     * 
     * @param filePath The path of the file to read.
     * @return An array containing customer data (username, password, balance).
     */
    public static String[] readCustomerFile(String filePath) {
        String[] customerData = new String[3]; 

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null && index < 3) {
                customerData[index++] = line.trim();
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            System.err.println("File read error");
        }

        return customerData;
    }
        
    /**
     * Effects:
     * - Removes a customer and their corresponding file.
     * 
     * Modifies:
     * - Customer list, file system.
     * 
     * Requires:
     * - The customer file exists.
     * 
     * @param username The username of the customer to remove.
     */
    public static void removeCustomer(String username) {
        try {
            File file = new File("src/customers", username + ".txt");
            
            if(!file.exists()) {
                throw new IOException();
            }
            file.delete();
            customerList.remove(getCustomer(username));
        } catch(IOException e) {
            e.printStackTrace();
            System.err.println("Customer file not found");
        }
    }
        /**
     * Effects:
     * - Creates a new customer and their corresponding file.
     * 
     * Modifies:
     * - Customer list, file system.
     * 
     * Requires:
     * - The customer file doesn't already exist.
     * 
     * @param username The username of the new customer.
     * @param password The password of the new customer.
     * @param balance The initial balance of the new customer.
     */
    public static void createCustomer(String username, String password, double balance) {
        try {
            File file = new File("src/customers",username+".txt");
            
            if(file.exists()) {
                //io
            }
            if(!file.createNewFile()) {
                //io
            }
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(username);
                writer.write(System.lineSeparator());
                writer.write(password);
                writer.write(System.lineSeparator());
                writer.write(Double.toString(balance));
            }
            Customer customer = new Customer(username, password, balance);
            customerList.add(customer);
        } catch(IOException|NumberFormatException e) {
            e.printStackTrace();
            System.err.println("Customer not created successfully");
        }
    }

    /**
     * Returns a string representation of the CustomerManager object.
     *
     * @return A string representation including the list of customers.
     */
    @Override
    public String toString() {
        return "Customer Manager: " + customerList.toString();
    }

    /**
     * Checks if the rep invariant holds for the CustomerManager object.
     *
     * @return true if the rep invariant holds, false otherwise.
     */
    private boolean repOk() {
        return customerList != null;
    }
}

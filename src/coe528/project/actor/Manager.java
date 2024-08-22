/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project.actor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Rishi
 *
 * Overview: This class represents a manager in the system. It provides methods to retrieve
 * manager's username, password, and role and allows the manager to add and remove customers.
 * This class is immutable as it does not have any instance variables and does not edit customer
 * object and its states directly
 *
 * Abstraction Function:
 * - AF: This class represents a manager with a fixed username and password.
 *
 * Rep Invariant:
 * - None
 */
public class Manager implements Session {
    /**
     * Effects:
     * - Retrieves the username of the manager.
     * 
     * Modifies:
     * - None
     * 
     * Requires:
     * - None
     * 
     * @return The username of the manager.
     */
    public final static String getUsername() {
        return "admin";
    }
        /**
     * Effects:
     * - Retrieves the password of the manager.
     * 
     * Modifies:
     * - None
     * 
     * Requires:
     * - None
     * 
     * @return The password of the manager.
     */
    public final static String getPassword() {
        return "admin";
    }
        /**
     * Effects:
     * - Retrieves the role of the manager.
     * 
     * Modifies:
     * - None
     * 
     * Requires:
     * - None
     * 
     * @return The role of the manager.
     */
    public final static String getRole() {
        return Session.MANAGER_ROLE;
    }
        /**
     * Effects:
     * - Adds a new customer to the system.
     * 
     * Modifies:
     * - Customer files.
     * 
     * Requires:
     * - None
     * 
     * @param name The username of the new customer.
     * @param password The password of the new customer.
     * @param balance The initial balance of the new customer.
     */
    public static void addCustomer(String name, String password, double balance) {
        String fileName = "customer_" + name + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(name + "\n");
            writer.write(balance + "\n");
            writer.write(password + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        /**
     * Effects:
     * - Removes a customer from the system.
     * 
     * Modifies:
     * - Customer files.
     * 
     * Requires:
     * - None
     * 
     * @param name The username of the customer to remove.
     */
    public final void removeCustomer(String name) {
        String fileName = "customer_" + name + ".txt";
        File file = new File(fileName);

        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Customer session deleted successfully.");
            } else {
                System.out.println("Failed to delete customer session.");
            }
        } else {
            System.out.println("Customer session does not exist.");
        }
    }

    /**
     * Returns a string representation of the Manager object.
     *
     * @return A string representation including the manager's username and role.
     */
    @Override
    public String toString() {
        return "Manager: [Username: " + getUsername() + ", Role: " + getRole() + "]";
    }

    /**
     * Checks if the rep invariant holds for the Manager object.
     *
     * @return true if the rep invariant holds, false otherwise.
     */
    private boolean repOk() {
        return true; // There is no rep invariant to check
    }
}

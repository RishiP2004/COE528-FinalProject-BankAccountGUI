/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project.actor;

import coe528.project.level.GoldLevel;
import coe528.project.level.PlatinumLevel;
import coe528.project.level.SilverLevel;
import coe528.project.level.Level;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Rishi
 *
 * Overview: This class represents a customer in the system. It provides methods to retrieve
 * customer's username, password, balance, and level. It also allows the customer to handle
 * online purchases, withdraw and deposit funds, and update their balance in the file system.
 * This class is mutable as it allows for modification of the customer's variables.
 *
 * Abstraction Function:
 * - AF: This class represents a customer with a fixed username, password, balance, and level.
 *
 * Rep Invariant:
 * - The username, password, and level of the customer must not be null.
 */
public class Customer implements Session {
    private Level level;
    private final String username;
    private final String password;
    private double balance;
    /**
     * Effects:
     * - Constructs a new Customer object with the specified username, password, and balance.
     * 
     * Modifies:
     * - None
     * 
     * Requires:
     * - None
     * 
     * @param username The username of the customer.
     * @param password The password of the customer.
     * @param balance The initial balance of the customer.
     */
    public Customer(String username, String password, double balance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        initLevel();
    }
     /**
     * Effects:
     * - Retrieves the username of the customer.
     * 
     * Modifies:
     * - None
     * 
     * Requires:
     * - None
     * 
     * @return The username of the customer.
     */
    public final String getUsername() {
        return username;
    }
     /**
     * Effects:
     * - Retrieves the password of the customer.
     * 
     * Modifies:
     * - None
     * 
     * Requires:
     * - None
     * 
     * @return The password of the customer.
     */
    public final String getPassword() {
        return password;
    }
    /**
     * Effects:
     * - Retrieves the role of the customer.
     * 
     * Modifies:
     * - None
     * 
     * Requires:
     * - None
     * 
     * @return The role of the customer.
     */
    public final String getRole() {
        return Session.CUSTOMER_ROLE;
    }
        /**
     * Effects:
     * - Initializes the customer's level based on their balance.
     * 
     * Modifies:
     * - None
     * 
     * Requires:
     * - None
     */
    private void initLevel() {
        if (balance >= 20000) {
            level = new PlatinumLevel(this);
        } else if (balance >= 10000) {
            level = new GoldLevel(this);
        } else {
            level = new SilverLevel(this);
        }
    }
    /**
     * Effects:
     * - Retrieves the customer's current level.
     * 
     * Modifies:
     * - None
     * 
     * Requires:
     * - None
     * 
     * @return The customer's current level.
     */
    public final Level getLevel() {
        return level;
    }
     /**
     * Effects:
     * - Sets the customer's level.
     * 
     * Modifies:
     * - None
     * 
     * Requires:
     * - None
     * 
     * @param level The level to set.
     */
    public void setLevel(Level level) {
        this.level = level;
    }
    /**
     * Effects:
     * - Handles an online purchase for the customer.
     * 
     * Modifies:
     * - None
     * 
     * Requires:
     * - None
     * 
     * @param purchaseAmount The amount of the purchase.
     * @return true if the purchase is successfully handled, false otherwise.
     */
    public boolean handleOnlinePurchase(double purchaseAmount) {
        return this.getLevel().handleOnlinePurchase(purchaseAmount);
    }
    /**
     * Effects:
     * - Retrieves the customer's current balance.
     * 
     * Modifies:
     * - None
     * 
     * Requires:
     * - None
     * 
     * @return The customer's current balance.
     */
    public final double getBalance() {
        return balance;
    }
     /**
     * Effects:
     * - Sets the customer's balance.
     * 
     * Modifies:
     * - None
     * 
     * Requires:
     * - None
     * 
     * @param balance The balance to set.
     */
    public final void setBalance(double balance) {
        this.balance = balance;
        updateFile();
    }
     /**
     * Effects:
     * - Withdraws funds from the customer's account.
     * 
     * Modifies:
     * - None
     * 
     * Requires:
     * - None
     * 
     * @param amount The amount to withdraw.
     */
    public void withdraw(double amount) {
        this.getLevel().withdraw(amount);
    }
    /**
     * Effects:
     * - Deposits funds into the customer's account.
     * 
     * Modifies:
     * - None
     * 
     * Requires:
     * - None
     * 
     * @param amount The amount to deposit.
     */
    public void deposit(double amount) {
        this.getLevel().deposit(amount);
    }
	//NOT WORKING?????
    /**
     * Effects:
     * - Updates the customer's balance in the file system.
     * 
     * Modifies:
     * - File system
     * 
     * Requires:
     * - None
     */
    public void updateFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/customers/" + getUsername() + ".txt"));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            int lineNumber = 1;

            while ((line = reader.readLine()) != null) {
                if (lineNumber == 3) {
                    stringBuilder.append(getBalance()).append(System.lineSeparator());
                } else {
                    stringBuilder.append(line).append(System.lineSeparator());
                }
                lineNumber++;
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter("src/customers/" + getUsername() + ".txt"));
            writer.write(stringBuilder.toString());
            writer.close();

            System.out.println("Third line edited successfully.");

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    /**
     * Returns a string representation of the Customer object.
     *
     * @return A string representation including the customer's username and balance.
     */
    @Override
    public String toString() {
        return "Customer: [Username: " + getUsername() + ", Balance: " + getBalance() + "]";
    }

    /**
     * Checks if the rep invariant holds for the Customer object.
     *
     * @return true if the rep invariant holds, false otherwise.
     */
    private boolean repOk() {
        return username != null && password != null && level != null;
    }
}

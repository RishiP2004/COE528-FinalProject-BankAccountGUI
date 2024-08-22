/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project.level;

import coe528.project.actor.Customer;

/**
 *
 * @author Rishi
 *
 * Overview: This abstract class represents a level for each customer. 
 * It defines methods for withdrawing and depositing funds, handling online purchases,
 * and updating the customer's level based on their behavior. This class is mutable
 * as it allows for modification of customer balance and level.
 *
 * Abstraction Function:
 * - AF(customer) = a level with a customer whose balance and level are determined
 *                  by the specific subclass implementation.
 *
 * Rep Invariant:
 * - The customer object must not be null.
 */
public abstract class Level {
    protected final Customer customer;
    
    public Level(Customer customer) {
        this.customer = customer;
    }
    
    public abstract double getPurchaseFee();
   /**
    * Effects: 
    * - Withdraws the specified amount from the customer's balance.
    *
    * Modifies: 
    * - Customer's balance.
    *
    * Requires:
    * - The specified amount should be non-negative and should not exceed the customer's balance.
    *
    * @param amount The amount to withdraw.
    */
    public final void withdraw(double amount) {
        customer.setBalance(customer.getBalance() - amount);
        updateCustomerLevel();
    }
   /**
    * Effects: 
    * - Deposits the specified amount to the customer's balance.
    *
    * Modifies: 
    * - Customer's balance.
    *
    * Requires:
    * - The specified amount should be non-negative.
    *
    * @param amount The amount to deposit.
    */
    public final void deposit(double amount) {
        customer.setBalance(customer.getBalance() + amount);
        updateCustomerLevel();
    }
   /**
    * Effects: 
    * - Handles an online purchase for the given purchase amount.
    *
    * Modifies:
    * - Customer balance (from lower class) 
    *
    * Requires:
    * - Customer has enough funds after calculation
    *
    * @param purchaseAmount The amount of the purchase.
    * @return true if the purchase is successfully handled, false otherwise.
    */
    public abstract boolean handleOnlinePurchase(double purchaseAmount);
   /**
    * Effects:
    * - Updates the customer's level based on their behavior.
    *
    * Modifies:
    * - Customer's level.
    *
    * Requires:
    * - None
    */
    public abstract void updateCustomerLevel();

    /**
     * Returns a string representation of the Level object.
     *
     * @return A string representation including the customer's information.
     */
    @Override
    public String toString() {
        return "Customer Level: " + this.getClass().getSimpleName() + ", Customer: " + customer.toString();
    }

    /**
     * Checks if the rep invariant holds for the Level object.
     *
     * @return true if the rep invariant holds, false otherwise.
     */
    private boolean repOk() {
        return customer != null;
    }
}

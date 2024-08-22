/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project.level;

import coe528.project.actor.Customer;

public class SilverLevel extends Level {

    public SilverLevel(Customer customer) {
        super(customer);
    }
    
    @Override
    public double getPurchaseFee() {
        return 20;
    }

    @Override
    public boolean handleOnlinePurchase(double purchaseAmount) {
        double totalAmount = purchaseAmount + getPurchaseFee();
        
        if (customer.getBalance() >= totalAmount) {
            customer.setBalance(customer.getBalance() - totalAmount);
            updateCustomerLevel();
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public void updateCustomerLevel() {
        if (customer.getBalance() >= 20000) {
            customer.setLevel(new PlatinumLevel(customer));
        } else if (customer.getBalance() >= 10000) {
            customer.setLevel(new GoldLevel(customer));
        }
    }
    
    @Override
    public String toString() {
        return "Silver";
    }
}

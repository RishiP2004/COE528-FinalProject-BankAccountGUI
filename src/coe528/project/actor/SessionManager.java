/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project.actor;

/**
 * @author Rishi 
 *
 * Overview: This class handles everything related to login authentication and session management.
 * It allows for the creation, destruction, and retrieval of sessions, as well as login attempts for
 * both managers and customers. This class is mutable as it allows for modification of the session state.
 *
 * Abstraction Function:
 * - AF(session) = a session representing the current user logged in, or null if no user is logged in.
 *
 * Rep Invariant:
 * - None
 */

public class SessionManager {
    private static Session session = null;
   
    /**
     * Effects:
     * - Creates a new session for the specified user.
     * 
     * Modifies:
     * - Session state.
     * 
     * Requires:
     * - None
     * 
     * @param user The user for whom to create the session.
     */
    public final static void createSession(Session user) {
        session = user;
    }
    /**
     * Effects:
     * - Destroys the current session.
     * 
     * Modifies:
     * - Session state.
     * 
     * Requires:
     * - None
     */
    public final static void destroySession() {
        session = null;
    }
    /**
     * Effects:
     * - Checks if a session is currently active.
     * 
     * Modifies:
     * - None
     * 
     * Requires:
     * - None
     * 
     * @return true if a session is active, false otherwise.
     */
    public final static boolean isInSession() {
        return session != null;
    }
        /**
     * Effects:
     * - Retrieves the current session.
     * 
     * Modifies:
     * - None
     * 
     * Requires:
     * - None
     * 
     * @return The current session, or null if no session exists.
     */
    public final static Session getSession() {
        return session;
    }
        /**
     * Effects:
     * - Attempts to log in as a manager.
     * 
     * Modifies:
     * - None
     * 
     * Requires:
     * - None
     * 
     * @param name The username of the manager.
     * @param enteredPassword The entered password.
     * @return true if the login attempt is successful, false otherwise.
     */
    public static boolean tryManagerLogin(String name, String enteredPassword) {
        return (name.equals(Manager.getUsername()) && enteredPassword.equals(Manager.getPassword()));
    }
        /**
     * Effects:
     * - Attempts to log in as a customer.
     * 
     * Modifies:
     * - None
     * 
     * Requires:
     * - None
     * 
     * @param name The username of the customer.
     * @param enteredPassword The entered password.
     * @return true if the login attempt is successful, false otherwise.
     */
    public static boolean tryCustomerLogin(String name, String enteredPassword) {
        Customer customer = CustomerManager.getCustomer(name);

        if(customer == null) {
            System.out.println("User not found.");
            return false;
        }
        if(!enteredPassword.equals(customer.getPassword())) {
            System.out.println("Incorrect password.");
            return false;
        }
        System.out.println("Login successful.");
        return true;
    } 

    /**
     * Returns a string representation of the SessionManager object.
     *
     * @return A string representation including the current session.
     */
    @Override
    public String toString() {
        return "Session Manager: " + (session != null ? session.toString() : "No session");
    }

    /**
     * Checks if the rep invariant holds for the SessionManager object.
     *
     * @return true if the rep invariant holds, false otherwise.
     */
    private boolean repOk() {
        return true; // There is no rep invariant to check
    } 
}

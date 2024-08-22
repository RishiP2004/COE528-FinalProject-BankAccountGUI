/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package coe528.project;

import coe528.project.actor.Customer;
import coe528.project.actor.SessionManager;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Rishi
 */
public class ManagerSceneController {
    private Customer customer;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    Label balanceLabel;
    @FXML
    Label levelLabel;
    
    public void initialize(Customer customer) {
        this.customer = customer;
        balanceLabel.setText("Your balance: " + customer.getBalance());
        levelLabel.setText(customer.getLevel() + " level");
    }
    
    @FXML
    private void handleLogoutAction(ActionEvent event) throws IOException {
        if(SessionManager.getSession() == null) {
            return;
        }
        root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        SessionManager.destroySession();
    }
    
    @FXML
    private void handleAddCustomerAction(ActionEvent event) throws IOException {
         if(SessionManager.getSession() == null) {
            return;
        }
        root = FXMLLoader.load(getClass().getResource("subScene/AddCustomerScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
     @FXML
    private void handleRemoveCustomerAction(ActionEvent event) throws IOException {
         if(SessionManager.getSession() == null) {
            return;
        }
        root = FXMLLoader.load(getClass().getResource("subScene/RemoveCustomerScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

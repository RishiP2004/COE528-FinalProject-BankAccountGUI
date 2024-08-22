/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package coe528.project.subScene;

import coe528.project.CustomerSceneController;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Rishi
 */
public class OnlinePurchaseSceneController {
    private Customer customer;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    Label headingLabel;
    @FXML
    Label balanceLabel;
    @FXML
    Label levelLabel;
    @FXML
    TextField onlinePurchaseTextField;
    @FXML
    Label resultLabel;
   
    @FXML
    Label feeLabel;
    
    public void initialize(Customer customer) {
        this.customer = customer;
        headingLabel.setText("Welcome, " + customer.getUsername());
        
        if(customer.getLevel().getPurchaseFee() != 0) {
            feeLabel.setText("NOTE: There will be a fee of " + Double.toString(customer.getLevel().getPurchaseFee()));
        }
        updateLabels();
    }
    
    @FXML
    private void handleSubmitAction(ActionEvent event) throws IOException {
        if(SessionManager.getSession() == null) {
            return;
        }
        if(!customer.handleOnlinePurchase(Double.parseDouble(onlinePurchaseTextField.getText()))) {
            resultLabel.setText("You do not have enough money to purchase online");
            return;
        }
        
        resultLabel.setText("Purchased amount for $" + onlinePurchaseTextField.getText() + " successfully");
        updateLabels();
    }
    
    private void updateLabels() {
        balanceLabel.setText("Your balance: " + customer.getBalance());
        levelLabel.setText(customer.getLevel() + " level");
    }
  
    @FXML
    private void handleBackButtonAction(ActionEvent event) throws IOException {
        if(SessionManager.getSession() == null) {
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../CustomerScene.fxml"));
        root = loader.load();
            
        CustomerSceneController controller = loader.getController();
        controller.initialize(customer);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
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
public class DepositSceneController {
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
    TextField depositTextField;
    @FXML
    Label resultLabel;
    
    public void initialize(Customer customer) {
        this.customer = customer;
        headingLabel.setText("Welcome, " + customer.getUsername());
        updateLabels();
    }
    
    @FXML
    private void handleSubmitAction(ActionEvent event) throws IOException {
        if(SessionManager.getSession() == null) {
            return;
        }
        if(Double.parseDouble(depositTextField.getText()) < 0) {
            resultLabel.setText("Deposit money must be positive");
            return;
        }
        //make sure its a double
        customer.deposit(Double.parseDouble(depositTextField.getText()));
        resultLabel.setText("Deposited $" + depositTextField.getText() + " successfully");
        updateLabels();
    }
    
    private void updateLabels() {
         balanceLabel.setText("Your balance: " + customer.getBalance());
        levelLabel.setText(customer.getLevel() + " level");
    }
    //use sessions?
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
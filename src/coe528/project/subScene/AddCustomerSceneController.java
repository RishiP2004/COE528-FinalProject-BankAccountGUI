/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package coe528.project.subScene;

import coe528.project.actor.CustomerManager;
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
 * @author Paresh
 */
public class AddCustomerSceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    TextField usernameTextField;
    @FXML
    TextField passwordTextField;
    @FXML
    TextField balanceTextField;
    
    @FXML
    Label resultLabel;
    
    @FXML
    private void handleCreateAction(ActionEvent event) throws IOException {
        if(CustomerManager.getCustomer(usernameTextField.getText()) != null) {
            resultLabel.setText("Customer already exists.");
            return;
        }
	if(Double.parseDouble(balanceTextField.getText()) < 100) {
            resultLabel.setText("Must have a starting balance of greater than 100.");
            return;
        }
        CustomerManager.createCustomer(usernameTextField.getText(), passwordTextField.getText(), Double.parseDouble(balanceTextField.getText()));
        resultLabel.setText("Added customer successfully.");
    }
    @FXML
    private void handleBackButtonAction(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../ManagerScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

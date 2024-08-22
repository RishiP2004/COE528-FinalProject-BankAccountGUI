/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package coe528.project.subScene;

import coe528.project.CustomerSceneController;
import coe528.project.actor.Customer;
import coe528.project.actor.CustomerManager;
import coe528.project.actor.SessionManager;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rishi
 */
public class RemoveCustomerSceneController implements Initializable {

    @FXML
    private ComboBox<String> comboBox;
    @FXML
    Label resultLabel;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateComboBox();
    } 
    
    public void updateComboBox() {
        ArrayList<Customer> customers = CustomerManager.getCustomers();
        
        ObservableList<String> usernames = FXCollections.observableArrayList();
        for (Customer customer : customers) {
            usernames.add(customer.getUsername());
        }
        comboBox.setItems(usernames);
    }
    
    @FXML
    private void handleRemoveButtonAction(ActionEvent event) throws IOException {
        String selectedName = comboBox.getSelectionModel().getSelectedItem();
        
        CustomerManager.removeCustomer(selectedName);
        updateComboBox();
        resultLabel.setText("Removed customer successfully.");
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package coe528.project;

import coe528.project.actor.Customer;
import coe528.project.actor.CustomerManager;
import coe528.project.actor.SessionManager;
import coe528.project.subScene.DepositSceneController;
import coe528.project.subScene.OnlinePurchaseSceneController;
import coe528.project.subScene.WithdrawSceneController;
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
public class CustomerSceneController {
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
    
    public void initialize(Customer customer) {
        this.customer = customer;
        headingLabel.setText("Welcome, " + customer.getUsername());
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
    private void handleWithdrawAction(ActionEvent event) throws IOException {
        if(SessionManager.getSession() == null) {
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("subScene/WithdrawScene.fxml"));
        root = loader.load();
            
        WithdrawSceneController controller = loader.getController();
        controller.initialize(customer);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
     @FXML
    private void handleDepositAction(ActionEvent event) throws IOException {
         if(SessionManager.getSession() == null) {
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("subScene/DepositScene.fxml"));
        root = loader.load();
            
        DepositSceneController controller = loader.getController();
        controller.initialize(customer);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void handleOnlinePurchaseAction(ActionEvent event) throws IOException {
        if(SessionManager.getSession() == null) {
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("subScene/OnlinePurchaseScene.fxml"));
        root = loader.load();
            
        OnlinePurchaseSceneController controller = loader.getController();
        controller.initialize(customer);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

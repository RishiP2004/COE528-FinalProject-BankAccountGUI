/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package coe528.project;

import coe528.project.actor.Manager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import coe528.project.actor.SessionManager;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.Node;
/**
 * FXML Controller class
 *
 * @author Rishi
 */
public class ManagerLoginSceneController {

    @FXML
    TextField usernameTextField;
    @FXML
    PasswordField passwordTextField;
    
    @FXML
    Label resultLabel;
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private void handleSubmitAction(ActionEvent event) throws IOException {
        if(SessionManager.tryManagerLogin(usernameTextField.getText(), passwordTextField.getText())) {
            root = FXMLLoader.load(getClass().getResource("ManagerScene.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            SessionManager.createSession(new Manager()); //indicates manager session logged in
        } else {
            resultLabel.setText("Login failed. Check username or password.");
        }
    }
    
    @FXML
    private void handleBackButtonAction(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

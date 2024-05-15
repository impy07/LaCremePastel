package com.ispwproject.lacremepastel.controller.gui;

import com.ispwproject.lacremepastel.engineeringclasses.bean.OrderBean;
import com.ispwproject.lacremepastel.model.Order;
import com.ispwproject.lacremepastel.model.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class GUIControllerManageOrder {

    @FXML
    private Button acceptButton;

    @FXML
    private Button backButton;

    @FXML
    private ListView<?> orderView;

    @FXML
    private Button rejectButton;

    @FXML
    void acceptOrder(ActionEvent event) {
        Order order=new Order();
        order.setCompleted(orderView.getSelectionModel().getSelectedIndex());
    }


    @FXML
    void goBack(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/directorFirstPage.fxml"));
            stage.setScene(new Scene(root, 615, 480));
            stage.setTitle("La Creme Pastel");
            stage.show();
        } catch (Exception e) {
            System.err.println("Errore nel caricamento della schermata della gestione degli ordini!" + e.getMessage());
            e.printStackTrace();
        }
    }
    @FXML
    void rejectOrder(ActionEvent event) {
    }

}
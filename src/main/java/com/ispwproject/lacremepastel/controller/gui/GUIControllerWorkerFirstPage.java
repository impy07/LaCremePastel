package com.ispwproject.lacremepastel.controller.gui;

import com.ispwproject.lacremepastel.engineeringclasses.bean.SessionBean;
import com.ispwproject.lacremepastel.other.FXMLPaths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GUIControllerWorkerFirstPage extends AbstractGUIController{
        private SessionBean sessionBean;

        @FXML
        private Label welcomeLabel;

        @FXML
        public void requestHelp(ActionEvent helpEvent) {
            Node node= (Node) helpEvent.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            try {
                Parent root = FXMLLoader.load(getClass().getResource(FXMLPaths.HELP_PAGE));
                Stage poupopStage= new Stage();
                poupopStage.setScene(new Scene(root, 629, 481));
                poupopStage.setTitle("La Creme Pastel");
                poupopStage.initModality(Modality.APPLICATION_MODAL);   //blocca l'interazione con la finestra principale fino alla chiusura del poupop
                poupopStage.initOwner(stage);
                poupopStage.show();
            }catch(Exception e){

            }
        }
        @FXML
        public void confirmProduction(ActionEvent confirmEvent){
    }


}

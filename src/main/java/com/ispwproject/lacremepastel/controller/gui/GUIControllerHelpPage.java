package com.ispwproject.lacremepastel.controller.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GUIControllerHelpPage {

    @FXML
    private Label welcomeLabel;
    @FXML
    private TextArea messageArea;
    @FXML
    private Button inviaSegnalazione;

    @FXML
    public void setWelcomeLabel(){
        welcomeLabel=new Label();
        welcomeLabel.setText("");
    }

    @FXML
    public void getText(ActionEvent event){
        try{
            String text=messageArea.getText();
        }catch(Exception e){
            System.err.println("Impossibile ricevere il testo!");
        }
    }
    @FXML
    public void sendMessage(ActionEvent messageEvent){
        Node node = (Node) messageEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        try{
            Label label=new Label("Segnalazione correttamente inviata, grazie mille!");
            Scene scene=new Scene(label,300,100);
            Stage poupopStage=new Stage();
            poupopStage.initModality(Modality.APPLICATION_MODAL);   //blocca l'interazione con la finestra principale fino alla chiusura del poupop
            poupopStage.initOwner(stage);

            poupopStage.setScene(scene);
            poupopStage.showAndWait(); //mostra il poupop e aspetta la chiusura prima di procedere con il codice successivo
            System.out.println("Segnalazione inviata correttamente");
        }catch (Exception e){
            System.err.println("Impossibile inviare la segnalazione!");
        }
    }
}


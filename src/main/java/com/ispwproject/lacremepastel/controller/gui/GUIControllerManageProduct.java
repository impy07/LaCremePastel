package com.ispwproject.lacremepastel.controller.gui;

import com.ispwproject.lacremepastel.controller.app.ManageProductController;
import com.ispwproject.lacremepastel.engineeringclasses.bean.ProductBean;
import com.ispwproject.lacremepastel.engineeringclasses.bean.ProductFilterBean;
import com.ispwproject.lacremepastel.engineeringclasses.bean.SessionBean;
import com.ispwproject.lacremepastel.engineeringclasses.factory.PopupFactory;
import com.ispwproject.lacremepastel.other.FXMLPaths;
import com.ispwproject.lacremepastel.other.SupportedProductCategory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.util.List;

public class GUIControllerManageProduct extends AbstractGUIController{

    @FXML
    private ComboBox<ProductBean> productBox;
    @FXML
    private TextField category;
    @FXML
    private TextField price;
    @FXML
    private ImageView image;
    @FXML
    private Button confirmButton;
    @FXML
    private TextField productName;
    @FXML
    private Button backButton;
    @FXML
    private Button deleteProduct;
    @FXML
    private Button searchButton;

    private SessionBean sessionData;

    @FXML
    void confirmModify() {
        PopupFactory popupFactory = new PopupFactory();

        ProductBean productBean = productBox.getSelectionModel().getSelectedItem();
        try {
            productBean.setProductName(this.productName.getText());
            productBean.setCategory(SupportedProductCategory.valueOf(this.category.getText()));
            productBean.setPrice(Double.parseDouble(this.price.getText()));
        }catch (Exception ignored){
            popupFactory.createBasePopup("Parametri non validi","red").show(confirmButton.getScene().getWindow());
            this.clearFields();
            return;
        }
        ManageProductController manageProductController = new ManageProductController();
        if(manageProductController.updateProduct(productBean, sessionData)){
            popupFactory.createBasePopup("Aggiornamento completato").show(confirmButton.getScene().getWindow());
            this.clearFields();
        }else{
            popupFactory.createBasePopup("Errore","red").show(confirmButton.getScene().getWindow());
        }
    }

    @FXML
    public void goBack(ActionEvent backEvent) {
        this.setupStage(backEvent, FXMLPaths.DIRECTOR_HOME);
    }

    @FXML
    public void deleteProduct() {

        Stage stage = (Stage) deleteProduct.getScene().getWindow();

        ManageProductController manageProductController = new ManageProductController();
        ProductBean productBean = productBox.getSelectionModel().getSelectedItem();
        PopupFactory popupFactory = new PopupFactory();
        if(manageProductController.removeProduct(productBean, sessionData)){
            this.clearFields();
            this.productBox.setVisible(false);
            popupFactory.createBasePopup("Prodotto Eliminato con Successo","black").show(stage);
        }else{
            popupFactory.createBasePopup("Errore","red").show(stage);
        }

    }

    @FXML
    public void searchProduct() {
        String name = this.productName.getText();
        if(name == null){
            return;
        }
        ManageProductController manageProductController = new ManageProductController();
        ProductFilterBean productFilterBean = new ProductFilterBean(name);
        List<ProductBean> productList = manageProductController.getProductList(sessionData,productFilterBean);
        if(!productList.isEmpty()) {
            this.clearFields();
            for (ProductBean productBean : productList) {
                productBox.getItems().add(productBean);
            }
            productBox.setVisible(true);
        }
    }

    @Override
    public void configure() {
        this.sessionData = (SessionBean) this.getUserData(SESSION_DATA);
    }

    public void oracolo() {
        ProductBean selected = productBox.getSelectionModel().getSelectedItem();
        if(selected != null) {
            this.category.setText(selected.getCategory().toString());
            this.price.setText(String.valueOf(selected.getPrice()));
            this.productName.setText(selected.getProductName());
        }
    }

    private void clearFields(){
        this.productBox.getSelectionModel().clearSelection();
        this.productBox.getItems().clear();
        this.price.clear();
        this.category.clear();
        this.productName.clear();
        productBox.setVisible(false);
    }
}

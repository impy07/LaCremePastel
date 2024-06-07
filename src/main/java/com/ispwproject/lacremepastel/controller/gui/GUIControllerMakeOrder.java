package com.ispwproject.lacremepastel.controller.gui;
import com.ispwproject.lacremepastel.controller.app.ManageProductController;
import com.ispwproject.lacremepastel.engineeringclasses.bean.ProductBean;
import com.ispwproject.lacremepastel.engineeringclasses.bean.SessionBean;
import com.ispwproject.lacremepastel.other.FXMLPaths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.util.List;

public class GUIControllerMakeOrder  extends AbstractGUIController{

    @FXML
    private ComboBox<String> productBox;
    @FXML
    private TextField quantityField;

    private List<ProductBean> productList ;

    @FXML
    void addCart(ActionEvent cartEvent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @FXML
    public void goBack(ActionEvent backEvent) {
        this.setupStage(backEvent, FXMLPaths.CUSTOMER_HOME);
    }
    @FXML
    public void showShoppingCart(ActionEvent cartEvent) {
        this.setupStage(cartEvent, FXMLPaths.SHOPPING_CART);
    }

    @Override
    public void configure(){
        SessionBean sessionBean = (SessionBean) this.getUserData();
        ManageProductController manageProductController = new ManageProductController();
        this.productList = manageProductController.getProductList(sessionBean, null);
        for(ProductBean productBean : productList){
            productBox.getItems().add(productBean.getProductName());
        }
    }
}



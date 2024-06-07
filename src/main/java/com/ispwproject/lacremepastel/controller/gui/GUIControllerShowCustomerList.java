package com.ispwproject.lacremepastel.controller.gui;

import com.ispwproject.lacremepastel.engineeringclasses.bean.SessionBean;
import com.ispwproject.lacremepastel.engineeringclasses.dao.CustomerDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GUIControllerShowCustomerList extends AbstractGUIController implements Initializable {
        private SessionBean sessionBean;
        @FXML
        private ListView<String> customerView;
        private CustomerDAO customerDAO;
        private List<String> customers;
        private String selectedCustomer;
        @FXML
        void goBack(ActionEvent event) {
        }

        public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
        }
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
             CustomerDAO customerDAO1=new CustomerDAO();
             this.customers=customerDAO1.getAllCustomer();
             customerView.getItems().addAll(customers);
        }
}

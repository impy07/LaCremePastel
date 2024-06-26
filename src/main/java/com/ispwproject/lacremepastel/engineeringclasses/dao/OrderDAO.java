package com.ispwproject.lacremepastel.engineeringclasses.dao;

import com.ispwproject.lacremepastel.engineeringclasses.query.OrderQuery;
import com.ispwproject.lacremepastel.engineeringclasses.singleton.Configurations;
import com.ispwproject.lacremepastel.engineeringclasses.singleton.Connector;
import com.ispwproject.lacremepastel.model.Order;
import com.ispwproject.lacremepastel.model.OrderLine;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class OrderDAO {
    public boolean createOrder(Order order) {
        if (order == null) {
            return false;
        }
        int idOrder;
        int isPending;
        int isAccepted;
        int isClosed;
        if (order.isPending()) {
            isPending = 1;
            isAccepted = 0;
        } else {
            if (order.isAccepted()) {
                isAccepted = 1;
                isPending = 0;
            } else {
                isPending = 0;
                isAccepted = 0;
            }
        }

        if (order.isClosed()) {
            isClosed = 1;
        } else {
            isClosed = 0;
        }
        OrderLineDAO orderLineDAO = new OrderLineDAO();
        try (ResultSet rs = OrderQuery.createSimpleOrder(Connector.getConnection(), isPending, isAccepted, isClosed, order.getCustomerName())) {
            if (rs.next()) {
                idOrder = rs.getInt("insert_id");
                orderLineDAO.saveOrderLines(idOrder, order.getCart());
                return true;
            }
        } catch (SQLException e) {
            Logger.getLogger(Configurations.LOGGER_NAME).severe(e.getMessage());
            orderLineDAO.cleanOnFail(order.getIdOrder());
            this.cleanOnFail(order);
        }
        return false;
    }

    public List<Order> getPendingOrders() {
        List<Order> orders = new ArrayList<>();
        try (ResultSet rs = OrderQuery.getPendingOrders(Connector.getConnection())){
            while (rs.next()) {
                Order order = new Order(rs.getInt("id"), rs.getString("customer"));
                orders.add(order);
            }
        } catch (SQLException e) {
            Logger.getLogger(Configurations.LOGGER_NAME).severe(e.getMessage());
        }
        return orders;
    }

    private void cleanOnFail(Order order) {
        try {
            OrderQuery.cleanOnFail(Connector.getConnection(), order.getIdOrder());
        }catch (SQLException e){
            Logger.getLogger(Configurations.LOGGER_NAME).severe(e.getMessage());
        }
    }

    public Order getOrderById(int idOrder) {
        Order order = null;
        OrderLineDAO orderLineDAO = new OrderLineDAO();
        List<OrderLine> orderLines = orderLineDAO.getOrderCartById(idOrder);
        try {
            ResultSet rs = OrderQuery.getOrderById(Connector.getConnection(),idOrder);
            if (rs.next()) {
                boolean pending = rs.getInt("pending") == 1;
                boolean accepted = rs.getInt("accepted") == 1;
                boolean done = rs.getInt("done") == 1;
                String customer = rs.getString("customer");
                order = new Order(idOrder,customer,orderLines,pending,accepted,done);
            }
        }catch (SQLException e) {
            Logger.getLogger(Configurations.LOGGER_NAME).severe(e.getMessage());
        }
        return order;
    }

    public boolean updateOrder(Order order){
        if(order == null){
            return false;
        }
        try {
            OrderQuery.updateOrder(Connector.getConnection(), order);
            return true;
        }catch (SQLException e){
            Logger.getLogger(Configurations.LOGGER_NAME);
            return false;
        }
    }
}


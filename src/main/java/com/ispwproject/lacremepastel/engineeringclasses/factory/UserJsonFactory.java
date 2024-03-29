package com.ispwproject.lacremepastel.engineeringclasses.factory;

import com.ispwproject.lacremepastel.engineeringclasses.dao.CustomerDAO;
import com.ispwproject.lacremepastel.engineeringclasses.dao.DirectorDAO;
import com.ispwproject.lacremepastel.engineeringclasses.dao.WorkerDAO;
import com.ispwproject.lacremepastel.engineeringclasses.dao.json.CustomerJsonDAO;
import com.ispwproject.lacremepastel.engineeringclasses.dao.json.DirectorJsonDAO;
import com.ispwproject.lacremepastel.engineeringclasses.dao.json.WorkerJsonDAO;

public class UserJsonFactory extends UserDAOFactory{
    @Override
    public DirectorDAO createDirectorDAO() {
        return new DirectorJsonDAO();
    }

    @Override
    public CustomerDAO createCustomerDAO() {
        return new CustomerJsonDAO();
    }

    @Override
    public WorkerDAO createWorkerDAO() {
        return new WorkerJsonDAO();
    }
}

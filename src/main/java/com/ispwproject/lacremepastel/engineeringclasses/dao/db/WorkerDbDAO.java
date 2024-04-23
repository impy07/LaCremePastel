package com.ispwproject.lacremepastel.engineeringclasses.dao.db;

import com.ispwproject.lacremepastel.engineeringclasses.dao.WorkerDAO;
import com.ispwproject.lacremepastel.engineeringclasses.exception.InvalidParameterException;
import com.ispwproject.lacremepastel.engineeringclasses.exception.UserAlreadyExistentException;
import com.ispwproject.lacremepastel.engineeringclasses.query.WorkerQuery;
import com.ispwproject.lacremepastel.engineeringclasses.singleton.Connector;
import com.ispwproject.lacremepastel.model.Register;
import com.ispwproject.lacremepastel.other.SupportedUserTypes;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.logging.Logger;

public class WorkerDbDAO implements WorkerDAO{
    @Override
    public boolean userRegister(Register register) throws UserAlreadyExistentException, InvalidParameterException {
        if(register != null){
            try{
                WorkerQuery.addWorker(
                        Connector.getConnection(),
                        register.getUsername(),
                        BCrypt.hashpw(register.getPasswd(), BCrypt.gensalt()),
                        register.getFirstname(),
                        register.getLastname(),
                        register.getEmail(),
                        register.getCfPiva(),
                        register.getRole().toString(),
                        SupportedUserTypes.WORKER.toString()
                );
                return true;
            } catch (SQLException e) {
                Logger.getLogger(WorkerDbDAO.class.getName()).severe(e.getMessage());
                if(e.getMessage().contains("Duplicate entry")){
                    throw new UserAlreadyExistentException("User " + register.getUsername() + " already exists");
                }else{
                    throw new InvalidParameterException("Invalid Parameters");
                }
            }
        }
        return false;
    }
}

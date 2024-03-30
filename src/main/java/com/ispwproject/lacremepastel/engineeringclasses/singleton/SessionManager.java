package com.ispwproject.lacremepastel.engineeringclasses.singleton;

import com.ispwproject.lacremepastel.engineeringclasses.exception.SessionNotFoundException;
import com.ispwproject.lacremepastel.engineeringclasses.exception.UserAlreadyLoggedException;
import com.ispwproject.lacremepastel.engineeringclasses.exception.UuidAlreadyExistent;
import com.ispwproject.lacremepastel.model.Session;

import java.util.Date;
import java.util.HashMap;

public class SessionManager {

    private static SessionManager instance = null;
    private HashMap<String, Session> loggedUsers;

    private SessionManager(){
        loggedUsers = new HashMap<>();
    }

    public static synchronized SessionManager getInstance(){
        if(instance == null){
            instance = new SessionManager();
        }
        return instance;
    }

    public void addSession(Session session) throws UserAlreadyLoggedException, UuidAlreadyExistent {
        if(session != null){
            if(!loggedUsers.containsKey(session.getUuid())){
                loggedUsers.put(session.getUuid(),session);
            }else{
                Session tmp = loggedUsers.get(session.getUuid());
                if(session.getUsername().equals(tmp.getUsername())){
                    throw new UserAlreadyLoggedException("User "+session.getUsername()+" already logged in");
                }else{
                    //è possibile che si generino 2 uuid uguali?
                    throw new UuidAlreadyExistent("Uuid "+tmp.getUuid()+" already online!");
                }
            }
        }
    }

    public Session delSession(String uuid) throws SessionNotFoundException{
        Session ret = null;
        if(uuid != null){
            if(loggedUsers.containsKey(uuid)){
                ret = loggedUsers.remove(uuid);
            }else{
                throw new SessionNotFoundException("Session "+uuid+"not found");
            }
        }
        return ret;
    }

    public boolean checkSession(String uuid) throws SessionNotFoundException {
        if(uuid != null){
            Session session = loggedUsers.get(uuid);
            if(session != null){
                //Verify expire date
                Date now = new Date();
                if(session.getExpire().before(now)){
                    //Session expired!
                    delSession(session.getUuid());
                    return false;
                }else{
                    //Session still valid
                    session.refresh();
                    return true;
                }
            }else{
                throw new SessionNotFoundException("Session "+uuid+"not found");
            }
        }
        return false;
    }


}
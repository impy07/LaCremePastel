package com.ispwproject.lacremepastel.model;

import com.ispwproject.lacremepastel.other.SupportedUserTypes;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Session {

    private String uuid;
    private String username;
    private String role;
    private SupportedUserTypes usertype;
    private Date expire;
    private static final int TIMESPAN = 2;

    public Session(String uuid, String username, SupportedUserTypes usertype){
        this.uuid = uuid;
        this.username = username;
        this.usertype = usertype;
        expire = setExpire(new Date(),TIMESPAN);
    }

    public Session(String username, SupportedUserTypes usertype){
        this(generateUUID(),username,usertype);
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public String getRole(){
        return role;
    }
    public void setRole(String role) {
        this.role=role;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public SupportedUserTypes getUsertype() {
        return usertype;
    }

    public void setUsertype(SupportedUserTypes usertype) {
        this.usertype = usertype;
    }

    public Date getExpire(){
        return (Date) expire.clone();
    }

    public void refresh(){
        this.expire = setExpire(new Date(),TIMESPAN);
    }

    private static String generateUUID(){
        return UUID.randomUUID().toString();
    }
    private static Date setExpire(Date date, int hours){
        //Setting expire time
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }
}

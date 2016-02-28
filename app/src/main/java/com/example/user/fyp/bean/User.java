package com.example.user.fyp.bean;

import java.io.Serializable;

/**
 * Created by User on 28/1/2016.
 */
public class User implements Serializable {
    int id;
    String password,use_name;
    public User(int id,String password,String use_name){
        this.id = id;
        this.password = password;
        this.use_name = use_name;
    }
    public int getId(){return id;}
    public String getPassword(){return password;}
    public String getUse_name(){return use_name;}
    public void setId(int id){this.id = id;}
    public void setPassword(String password){this.password = password;}
    public void setUse_name(String use_name){this.use_name = use_name;}
}

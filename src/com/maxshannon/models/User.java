package com.maxshannon.models;

import java.io.Serializable;

public class User implements Serializable {

    static final long serialVersionUID = -68799149288400503L;
    private int userId;
    private String fName;
    private String sName;
    private String role;
    private String mobileNo;
    private String email;
    private String userType;
    private String password;

    public User(int userId, String fName, String sName, String role, String mobileNo, String email, String userType, String password) {
        this.userId = userId;
        this.fName = fName;
        this.sName = sName;
        this.role = role;
        this.mobileNo = mobileNo;
        this.email = email;
        this.userType = userType;
        this.password = password;
    }

    public String getfName() {
        return fName;
    }
}

package models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class User implements Serializable {

    static final long serialVersionUID = -68799149288400503L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int userId;
    private String fName;
    private String sName;
    private String role;
    private String mobileNo;
    private String email;
    private String userType;
    private String password;

    public User(){}

    public User(int userId, String fName, String sName, String role, String mobileNo, String email, String userType, String password) {
        super();
        this.userId = userId;
        this.fName = fName;
        this.sName = sName;
        this.role = role;
        this.mobileNo = mobileNo;
        this.email = email;
        this.userType = userType;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

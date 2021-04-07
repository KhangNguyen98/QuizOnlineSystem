/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangnh.accounts;

import java.io.Serializable;

/**
 *
 * @author khang nguyen
 */
public class AccountDTO implements Serializable{
    private String email, name, password;
    private int roleID, status;

    public AccountDTO(String email, String name, String password, int roleID, int status) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.roleID = roleID;
        this.status = status;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kigalicabsapis.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author DolphiX People'S
 */
@Entity
public class User {
    @Id
    private String username;
    private String email;
    private String password;
    private String cpassword;

    public User() {
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getCpassword() {
	return cpassword;
    }

    public void setCpassword(String cpassword) {
	this.cpassword = cpassword;
    }

    @Override
    public String toString() {
	return "User{" + "username=" + username + ", email=" + email + ", password=" + password + ", cpassword=" + cpassword + '}';
    }

    
}

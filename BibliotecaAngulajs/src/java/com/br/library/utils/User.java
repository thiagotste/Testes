package com.br.library.utils;
import java.io.Serializable;
public class User implements Serializable{
    private long id;
    private String name;
    private String email;
    private String pass;
    private boolean enabled;
    private String createDate;
    private String rg;
    private String cpf;
    private String phone;
    private String address;
    private String birthday;
    private boolean superUser;
    
    public User(){
        this.id = 0;
        this.name = "";
        this.email = "";
        this.pass = "";
        this.enabled = true;
        this.createDate = "";
        this.rg = "";
        this.cpf = "";
        this.phone = "";
        this.address = "";
        this.birthday = "";
        this.superUser = false;
    }

    public User(int id, String name, String email, String pass, boolean enabled, 
            String createDate, String rg, String cpf, String phone, String address, 
            String birthday, boolean superUser) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.enabled = enabled;
        this.createDate = createDate;
        this.rg = rg;
        this.cpf = cpf;
        this.phone = phone;
        this.address = address;
        this.birthday = birthday;
        this.superUser = superUser;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public boolean isSuperUser() {
        return superUser;
    }

    public void setSuperUser(boolean superUser) {
        this.superUser = superUser;
    }
}

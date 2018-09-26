package com.br.library.accsess;

import com.sun.org.apache.bcel.internal.Constants;
import java.io.Serializable;

public class User implements Serializable {

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
    private String zcode;
    private String complement;
    private String neighbor;
    private String city;
    private String state;
    private boolean librarian;

    public User() {
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
        this.zcode = "";
        this.complement = "";
        this.neighbor = "";
        this.city = "";
        this.state = "";
        this.librarian = false;
    }

    public User(int id, String name, String email, String pass, boolean enabled,
            String createDate, String rg, String cpf, String phone, String address,
            String birthday, boolean superUser, String zcode, String complement,
            String neighbor, String city, String state, boolean librarian) {
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
        this.zcode = zcode;
        this.complement = complement;
        this.neighbor = neighbor;
        this.city = city;
        this.state = state;
        this.librarian = librarian;
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

    public String getZcode() {
        return zcode;
    }

    public void setZcode(String zcode) {
        this.zcode = zcode;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighbor() {
        return neighbor;
    }

    public void setNeighbor(String neighbor) {
        this.neighbor = neighbor;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isLibrarian() {
        return librarian;
    }

    public void setLibrarian(boolean librarian) {
        this.librarian = librarian;
    }

    public String firstName() {
        String[] nameArray = this.name.split(" ");
        return nameArray[0];
    }

    public String getFullAddress() {
        if(!complement.equals("")){
            return this.address + ", " + this.complement + ", "  + this.neighbor + ", "
                + this.city + "/" + this.state + " - " + this.zcode;
        } else{
            return this.address + ", "  + this.neighbor + ", "
                + this.city + "/" + this.state + " - " + this.zcode;
        }
    }
}

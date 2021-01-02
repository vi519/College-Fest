package com.example.c_fest;

public class Datamodel {

    String uname,email,pwd;

    public Datamodel(String uname, String email, String pwd) {
        this.uname = uname;
        this.email = email;
        this.pwd = pwd;


    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


}

package com.borovoi.dmitrii.dbtutorial.model;

/**
 * Created by dimas on 1/12/2016.
 */
public class User {
    private Long id;
    private String name;
    private String login;
    private String password;
    private boolean isAdmin;

    public User(String name, String login, String password, boolean isAdmin) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "[" + id + "]" + name;
    }
}

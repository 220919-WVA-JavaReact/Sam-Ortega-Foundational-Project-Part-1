package com.revature.models;

import java.util.Objects;

public class Users {
    private int id;
    private String first;
    private String last;
    private String email;
    private String password;
    private Boolean isManager;

    public Users(int id, String first, String last, String email, String password, Boolean isManager) {
        this.id = id;
        this.first = first;
        this.last = last;
        this.email = email;
        this.password = password;
        this.isManager = isManager;
    }

    public Users(String first, String last, String email, String password, Boolean isManager) {
        this.first = first;
        this.last = last;
        this.email = email;
        this.password = password;
        this.isManager = isManager;
    }

    public Users() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
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

    public Boolean getManager() {
        return isManager;
    }

    public void setManager(Boolean manager) {
        isManager = manager;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isManager=" + isManager +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return id == users.id && first.equals(users.first) && last.equals(users.last) && email.equals(users.email) && password.equals(users.password) && isManager.equals(users.isManager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first, last, email, password, isManager);
    }
}

package com.revature.models;

import java.util.Objects;

public class Reimbursement {

    private int id;
    //    private int employee_id;
    private Users user;
    private float cost;
    private String description;
    private Boolean status;


    public Reimbursement(int id, Users user, Float cost, String description, Boolean status) {
        this.id = id;
        this.user = user;
        this.cost = cost;
        this.description = description;
        this.status = status;
    }

    public Reimbursement(int id, Float cost, String description, Boolean status) {
        this.id = id;
        this.cost = cost;
        this.description = description;
        this.status = status;
    }

    public Reimbursement(Users user, Float cost, String description, Boolean status) {
        this.user = user;
        this.cost = cost;
        this.description = description;
        this.status = status;
    }

    public Reimbursement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "id=" + id +
                ", user=" + user +
                ", cost=" + cost +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursement that = (Reimbursement) o;
        return id == that.id && Float.compare(that.cost, cost) == 0 && Objects.equals(user, that.user) && Objects.equals(description, that.description) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, cost, description, status);
    }
}
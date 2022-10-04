package com.revature.models;

import java.util.Objects;

public class Reimbursement {

    private int id;
    private int employee_id;
    private float cost;
    private String description;
    private Boolean status;

    public Reimbursement(int employee_id, float cost, String description, Boolean status) {
        this.id = id;
        this.employee_id = employee_id;
        this.cost = cost;
        this.description = description;
        this.status = status;
    }

    public Reimbursement() {
    }

    public Reimbursement(int id, int employee_id) {
        this.id = id;
        this.employee_id = employee_id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursement that = (Reimbursement) o;
        return id == that.id && employee_id == that.employee_id && Float.compare(that.cost, cost) == 0 && description.equals(that.description) && status.equals(that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employee_id, cost, description, status);
    }
}

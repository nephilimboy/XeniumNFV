package com.ahghorab.xenonet.web.rest.vm;

public class BaseEntityVM<T> {
//    public id?:T,
//    public createdDate?: string,
//    public updatedDate?: string,
    private T id;
    private String createdDate;
    private String updatedDate;

    public BaseEntityVM() {
    }

    public BaseEntityVM(T id, String createdDate, String updatedDate) {
        this.id = id;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }
}

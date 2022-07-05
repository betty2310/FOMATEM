package com.betty2310.app.model;

public class LogTable {
    private String stamp;
    private char operation;
    private Integer id;

    public LogTable(String stamp, char operation, Integer id) {
        this.stamp = stamp;
        this.operation = operation;
        this.id = id;
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

    public char getOperation() {
        return operation;
    }

    public void setOperation(char operation) {
        this.operation = operation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

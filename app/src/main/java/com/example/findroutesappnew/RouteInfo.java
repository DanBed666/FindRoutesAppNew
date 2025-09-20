package com.example.findroutesappnew;

import java.io.Serializable;

public class RouteInfo implements Serializable
{
    private String name;
    private String colour;
    private String operator;

    public RouteInfo(String name, String colour, String operator) {
        this.name = name;
        this.colour = colour;
        this.operator = operator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}

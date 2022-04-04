package com.example.pogulum.model;

import lombok.Data;

@Data
public class POJO {

    private String name;
    private int id;

    public POJO(String name, int id){
        this.name = name;
        this.id = id;
    }

    public POJO(){}

}

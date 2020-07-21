package com.dangnian.springboot.interview.serializable;

import java.io.Serializable;

public class User implements Serializable{

    private static final long serialVersionUID = 8525607974307409608L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}

package com.example.taxidriver.domain.model;

/**
 * Created by milossimic on 3/21/16.
 */
public class Cinema {
    private String name;
    private String description;
    private int avatar;

    public Cinema(){

    }

    public Cinema(String name, String description, int avatar) {
        this.name = name;
        this.description = description;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return  "name: " + name + "\n"  +
                "description: " + description + "\n" ;
    }
}

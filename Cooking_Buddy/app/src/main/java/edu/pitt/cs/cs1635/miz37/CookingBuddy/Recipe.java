package edu.pitt.cs.cs1635.miz37.CookingBuddy;

/**
 * Created by Michael on 3/28/2017.
 */

public class Recipe {
    private int id;
    private String name;


    public Recipe(int id, String name) {
        this.id = id;
        this.name = name;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

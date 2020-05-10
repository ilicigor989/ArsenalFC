package com.example.arsenalfc;

import java.util.ArrayList;

public class SingletonList {
    private static SingletonList uniqInstance;
    public ArrayList<Player> names = new ArrayList<Player>();
    ;

    private SingletonList() {
    }

    public static SingletonList getInstance() {
        if (uniqInstance == null)
            uniqInstance = new SingletonList();
        return uniqInstance;
    }

    public void setArrayList(ArrayList<Player> names) {
        this.names = names;

    }

    public ArrayList<Player> getArrayList() {
        return this.names;

    }
}

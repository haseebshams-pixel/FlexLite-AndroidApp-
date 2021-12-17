package com.example.flexlite.Firebase;

import java.util.ArrayList;
import java.util.Hashtable;

public interface IFlexLiteDAO {
    public void save(Hashtable<String,String> attributes);
    public void save(ArrayList<Hashtable<String,String>> objects);
    public ArrayList<Hashtable<String,String>> load();
}

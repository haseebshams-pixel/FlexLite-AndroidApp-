package com.example.flexlite.Firebase;

import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.Hashtable;

public interface IFlexLiteDAO {
    public void save(Hashtable<String,String> attributes);
    public void save(ArrayList<Hashtable<String,String>> objects);
    public ArrayList<Hashtable<String,String>> load();
    public void find(FirebaseDAO.DataObserver obs, String id);
    public void delete(String id);
}

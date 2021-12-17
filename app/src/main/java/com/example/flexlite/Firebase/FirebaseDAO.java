package com.example.flexlite.Firebase;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class FirebaseDAO implements IFlexLiteDAO{
    public interface DataObserver {
        public void update();
    }

    Context context;
    private DataObserver observer;
    FirebaseDatabase database;
    DatabaseReference myRef;

    ArrayList<Hashtable<String, String>> data;

    public FirebaseDAO(String path) {
        this.myRef = FirebaseDatabase.getInstance().getReference(path);
    }

    public FirebaseDAO(DataObserver obs,String path) {

        observer = obs;
        database = FirebaseDatabase.getInstance();
        //database.setPersistenceEnabled(true);
        myRef = database.getReference(path);
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    data = new ArrayList<Hashtable<String, String>>();
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        GenericTypeIndicator<HashMap<String, Object>> type = new GenericTypeIndicator<HashMap<String, Object>>() {
                        };
                        HashMap<String, Object> map = d.getValue(type);

                        Hashtable<String, String> obj = new Hashtable<String, String>();
                        for (String key : map.keySet()) {
                            obj.put(key, map.get(key).toString());
                        }
                        data.add(obj);
                    }

                    observer.update();
                } catch (Exception ex) {
                    Log.e("firebasedb", ex.getMessage());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("firebasedb", "Failed to read value.", error.toException());
            }
        };
        myRef.addValueEventListener(listener);
    }

    @Override
    public void save(Hashtable<String, String> attributes) {
        myRef.child(attributes.get("id")).setValue(attributes);
    }

    @Override
    public void save(ArrayList<Hashtable<String, String>> objects) {
        for (Hashtable<String, String> obj : objects) {
            save(obj);
        }
    }

    @Override
    public ArrayList<Hashtable<String, String>> load() {
        if (data == null) {
            data = new ArrayList<Hashtable<String, String>>();
        }

        return data;
    }
}

package com.example.flexlite.Firebase;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.flexlite.Classes.Student;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.Semaphore;

import javax.security.auth.callback.Callback;

public class FirebaseDAO implements IFlexLiteDAO {

    public interface DataObserver {
        public void update();
    }

    Context context;
    private DataObserver observer;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Boolean status;

    ArrayList<Hashtable<String, String>> data;

    public FirebaseDAO(String path) {
        this.myRef = FirebaseDatabase.getInstance().getReference(path);
        status=false;
    }

    public FirebaseDAO(DataObserver obs, String path) {
        observer = obs;
        database = FirebaseDatabase.getInstance();
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


    @Override
    public void find(DataObserver obs, String id) {
        observer = obs;
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    Boolean check = false;
                    if (dataSnapshot.hasChild(id)) {
                        check = true;
                        Log.d("User", "Found");
                        observer.update();
                    } else {
                        check = false;
                        observer.update();
                    }
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
    public void delete(String id) {
        myRef.child(id).removeValue();
    }


}

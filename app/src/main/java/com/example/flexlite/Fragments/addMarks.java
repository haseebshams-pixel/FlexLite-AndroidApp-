package com.example.flexlite.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flexlite.Activities.StartActivity;
import com.example.flexlite.Activities.TeacherLoginActivity;
import com.example.flexlite.Classes.Assessment;
import com.example.flexlite.Classes.Attendance;
import com.example.flexlite.Classes.Registration;
import com.example.flexlite.Classes.Section;
import com.example.flexlite.Classes.Student;
import com.example.flexlite.Firebase.FirebaseDAO;
import com.example.flexlite.Firebase.IFlexLiteDAO;
import com.example.flexlite.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link addMarks#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addMarks extends BaseFragment {
    IFlexLiteDAO dao;
    IFlexLiteDAO dao2;
    IFlexLiteDAO dao3;
    IFlexLiteDAO dao4;
    EditText totalMarks;
    EditText weightage;
    EditText obtainedMarks;
    View btn;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public addMarks() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addMarks.
     */
    // TODO: Rename and change types and number of parameters
    public static addMarks newInstance(String param1, String param2) {
        addMarks fragment = new addMarks();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_marks, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        totalMarks = (EditText) getView().findViewById(R.id.totalmarks);
        weightage =(EditText) getView().findViewById(R.id.weightage);
        btn = (View) getView().findViewById(R.id.saveBtn);
        dao = new FirebaseDAO(new FirebaseDAO.DataObserver() {
            @Override
            public void update() {
                ArrayList<Registration> regList = Registration.load(dao);
                dao2 = new FirebaseDAO(new FirebaseDAO.DataObserver() {
                    @Override
                    public void update() {
                        ArrayList<Section> seclist = Section.load(dao2, user.getUid().toString(), 1);
                        dao3 = new FirebaseDAO(new FirebaseDAO.DataObserver() {
                            @Override
                            public void update() {
                                ArrayList<Student> studentList = Student.load(dao3);
                                String[] arr = new String[seclist.size()];
                                for (int j = 0; j < seclist.size(); j++) {
                                    arr[j] = seclist.get(j).getName();
                                }
                                TableLayout table = (TableLayout) getView().findViewById(R.id.addmarkstable);
                                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getView().getContext(), android.R.layout.simple_spinner_item, arr);
                                AppCompatSpinner mySpinner = (AppCompatSpinner) getView().findViewById(R.id.sectionList);
                                mySpinner.setAdapter(spinnerArrayAdapter);
                                Section sect = findSection(seclist, mySpinner.getSelectedItem().toString(), user.getUid().toString());
                                ArrayList<Registration> registeredList = findRegistrations(regList, sect.getId());
                                dao4 = new FirebaseDAO("assessment");
                                ArrayList<Assessment> assList = new ArrayList<Assessment>();
                                AppCompatSpinner mySpinner2 = (AppCompatSpinner) getView().findViewById(R.id.assessmentList);
                                mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        Section sect = findSection(seclist, mySpinner.getSelectedItem().toString(), user.getUid().toString());
                                        ArrayList<Registration> registeredList = findRegistrations(regList, sect.getId());
                                        table.removeViews(1, Math.max(0, table.getChildCount() - 1));
                                        for (int i = 0; i < registeredList.size(); i++) {
                                            TableRow row = (TableRow) LayoutInflater.from(getContext()).inflate(R.layout.add_marks_table_row, null);
                                            Student std = findStudent(registeredList.get(i), studentList);
                                            ((TextView) row.findViewById(R.id.stud_name)).setText(std.getName());
                                            obtainedMarks = (EditText) row.findViewById(R.id.std_marks);
                                            int finalI = i;
                                            obtainedMarks.addTextChangedListener(new TextWatcher() {
                                                public void afterTextChanged(Editable s) {
                                                    assList.get(finalI).setObtainedMarks(s.toString());
                                                }
                                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                                                public void onTextChanged(CharSequence s, int start, int before, int count) {}
                                            });
                                            assList.add(new Assessment(dao4));
                                            assList.get(i).setRegID(registeredList.get(i).getid());
                                            table.addView(row);
                                        }
                                        btn.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                if(checkAllFields()){
                                                    for(int i=0;i<assList.size();i++){
                                                        assList.get(i).setTotalMarks(totalMarks.getText().toString());
                                                        assList.get(i).setWeightage(weightage.getText().toString());
                                                        assList.get(i).setType(mySpinner2.getSelectedItem().toString());
                                                        assList.get(i).save();
                                                        Toast.makeText(getContext(), "Marks Added", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            }
                                        });
                                        table.requestLayout();
                                        Log.d("Status",Integer.toString(assList.size()));
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                        for (int i = 0; i < registeredList.size(); i++) {
                                            TableRow row = (TableRow) LayoutInflater.from(getContext()).inflate(R.layout.add_attendance_table_row, null);
                                            Student std = findStudent(registeredList.get(i), studentList);
                                            ((TextView) row.findViewById(R.id.stud_name)).setText(std.getName());
                                            obtainedMarks =(EditText) row.findViewById(R.id.std_marks);
                                            int finalI = i;
                                            obtainedMarks.addTextChangedListener(new TextWatcher() {
                                                public void afterTextChanged(Editable s) {
                                                    assList.get(finalI).setObtainedMarks(s.toString());
                                                }
                                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                                                public void onTextChanged(CharSequence s, int start, int before, int count) {}
                                            });
                                            table.addView(row);
                                            assList.add(new Assessment(dao4));
                                            assList.get(i).setRegID(registeredList.get(i).getid());
                                        }
                                        btn.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                if(checkAllFields()){
                                                    for(int i=0;i<assList.size();i++){
                                                        assList.get(i).setTotalMarks(totalMarks.getText().toString());
                                                        assList.get(i).setWeightage(weightage.getText().toString());
                                                        assList.get(i).setType(mySpinner2.getSelectedItem().toString());
                                                        assList.get(i).save();
                                                        Toast.makeText(getContext(), "Marks Added", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            }
                                        });
                                        table.requestLayout();
                                        Log.d("Status",Integer.toString(assList.size()));
                                    }
                                });

                            }
                        }, "student");
                    }
                }, "sections");
            }
        }, "registration");
    }

    Section findSection(ArrayList<Section> sections, String name, String teachId) {
        Section sect = null;
        for (int i = 0; i < sections.size(); i++) {
            if (sections.get(i).getTeacher().equals(teachId)) {
                if (sections.get(i).getName().equals(name)) {
                    sect = sections.get(i);
                }
            }
        }
        return sect;
    }

    ArrayList<Registration> findRegistrations(ArrayList<Registration> regList, String secId) {
        ArrayList<Registration> registeredList = new ArrayList<Registration>();
        for (int i = 0; i < regList.size(); i++) {
            if (regList.get(i).getSec().equals(secId)) {
                registeredList.add(regList.get(i));
            }
        }
        return registeredList;
    }

    public boolean checkAllFields() {
        if (totalMarks.getText().toString().isEmpty()) {
            totalMarks.setError("Total Marks Required");
            return false;
        }
        if (weightage.getText().toString().isEmpty()) {
            weightage.setError("Weightage Required");
            return false;
        }
        return true;
    }

    Student findStudent(Registration reg, ArrayList<Student> studList) {
        Student regStudent = null;
        for (int j = 0; j < studList.size(); j++) {
            if (reg.getStud().equals(studList.get(j).getId())) {
                regStudent = studList.get(j);
            }
        }
        return regStudent;
    }
    @Override
    public boolean onBackPressed() {
        return false;
    }
}
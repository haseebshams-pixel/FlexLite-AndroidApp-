package com.example.flexlite.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.flexlite.Classes.Course;
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
 * Use the {@link registration#newInstance} factory method to
 * create an instance of this fragment.
 */
public class registration extends BaseFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    IFlexLiteDAO dao;
    IFlexLiteDAO dao2;
    IFlexLiteDAO dao3;
    IFlexLiteDAO dao4;
    IFlexLiteDAO dao5;
    IFlexLiteDAO dao6;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public registration() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment registration.
     */
    // TODO: Rename and change types and number of parameters
    public static registration newInstance(String param1, String param2) {
        registration fragment = new registration();
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
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        dao2 = new FirebaseDAO(new FirebaseDAO.DataObserver() {
            @Override
            public void update() {
                Student std = Student.load(dao2, user.getUid().toString());
                dao = new FirebaseDAO(new FirebaseDAO.DataObserver() {
                    @Override
                    public void update() {
                        ArrayList<Course> courseList = Course.load(dao, std.getSemesterNo());
                        dao4 = new FirebaseDAO(new FirebaseDAO.DataObserver() {
                            @Override
                            public void update() {
                                ArrayList<Registration> registerList = Registration.load(dao4, std.getId());

                                dao5 = new FirebaseDAO(new FirebaseDAO.DataObserver() {
                                    @Override
                                    public void update() {
                                        ArrayList<Course> RegisteredCourses = new ArrayList<Course>();
                                        for (int i = 0; i < courseList.size(); i++) {
                                            ArrayList<Section> sectionList = Section.load(dao5, courseList.get(i).getId());
                                            for (int j = 0; j < sectionList.size(); j++) {
                                                boolean f = find(sectionList.get(j).getId(), registerList);
                                                if (f) {
                                                    RegisteredCourses.add(courseList.get(i));
                                                    Log.d("CoureName", courseList.get(i).getName());
                                                }
                                            }
                                        }
                                        TableLayout table = (TableLayout) getView().findViewById(R.id.registerTable);
                                        for (int i = 0; i < courseList.size(); i++) {
                                            boolean f = findCourse(courseList.get(i).getName(), RegisteredCourses);
                                            if (!f) {
                                                TableRow row = (TableRow) LayoutInflater.from(view.getContext()).inflate(R.layout.register_table_row, null);
                                                ((TextView) row.findViewById(R.id.courseName)).setText(courseList.get(i).getName());
                                                ArrayList<Section> sectionList = Section.load(dao5, courseList.get(i).getId());
                                                String[] arr = new String[sectionList.size()];
                                                for (int j = 0; j < sectionList.size(); j++) {
                                                    arr[j] = sectionList.get(j).getName();
                                                }
                                                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getView().getContext(), android.R.layout.simple_spinner_item, arr);
                                                AppCompatSpinner mySpinner = (AppCompatSpinner) row.findViewById(R.id.RegisterSpinner);
                                                mySpinner.setAdapter(spinnerArrayAdapter);
                                                TextView btn = (TextView) row.findViewById(R.id.reg_btn);
                                                int finalI = i;
                                                btn.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {

                                                        dao3 = new FirebaseDAO("registration");
                                                        Log.d("Status", mySpinner.getSelectedItem().toString());
                                                        String secName = mySpinner.getSelectedItem().toString();
                                                        Section sec = Section.load(dao5, courseList.get(finalI).getId(), secName);
                                                        Registration reg = new Registration("Registered", std.getId(), sec.getId());
                                                        reg.setDao(dao3);
                                                        reg.save();
                                                        table.removeView(row);
                                                        FragmentTransaction fragmentTransaction = getActivity()
                                                                .getSupportFragmentManager().beginTransaction();
                                                        fragmentTransaction.replace(R.id.container, new registration());
                                                        fragmentTransaction.commit();
                                                    }
                                                });
                                                table.addView(row);
                                            }
                                        }
                                        table.requestLayout();
                                    }
                                }, "sections");
                            }
                        }, "registration");
                    }
                }, "course");
            }
        }, "student");

    }

    @Override
    public boolean onBackPressed() {

        return false;

    }

    public boolean find(String secId, ArrayList<Registration> regList) {
        for (int i = 0; i < regList.size(); i++) {
            if (secId.equals(regList.get(i).getSec())) {
                return true;
            }
        }
        return false;
    }

    public boolean findCourse(String name, ArrayList<Course> list) {
        boolean check = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(name)) {
                check = true;
                break;
            } else {
                check = false;
            }
        }
        return check;
    }
}
package com.example.flexlite.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.flexlite.Classes.Assessment;
import com.example.flexlite.Classes.Attendance;
import com.example.flexlite.Classes.Course;
import com.example.flexlite.Classes.Registration;
import com.example.flexlite.Classes.Section;
import com.example.flexlite.Firebase.FirebaseDAO;
import com.example.flexlite.Firebase.IFlexLiteDAO;
import com.example.flexlite.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link attendance#newInstance} factory method to
 * create an instance of this fragment.
 */
public class attendance extends BaseFragment {
    IFlexLiteDAO dao;
    IFlexLiteDAO dao2;
    IFlexLiteDAO dao3;
    IFlexLiteDAO dao4;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public attendance() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment attendance.
     */
    // TODO: Rename and change types and number of parameters
    public static attendance newInstance(String param1, String param2) {
        attendance fragment = new attendance();
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
        return inflater.inflate(R.layout.fragment_attendance, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        dao = new FirebaseDAO(new FirebaseDAO.DataObserver() {
            @Override
            public void update() {
                ArrayList<Registration> regList = Registration.load(dao,user.getUid().toString());
                dao2 = new FirebaseDAO(new FirebaseDAO.DataObserver() {
                    @Override
                    public void update() {
                        ArrayList<Section> secList = Section.load(dao2);
                        dao3 = new FirebaseDAO(new FirebaseDAO.DataObserver() {
                            @Override
                            public void update() {
                                ArrayList<Course> courseList = Course.load(dao3);
                                ArrayList<Course> RegisteredCourses = find(regList, secList, courseList);
                                String[] arr = new String[RegisteredCourses.size()];
                                for (int j = 0; j < RegisteredCourses.size(); j++) {
                                    arr[j] = RegisteredCourses.get(j).getName();
                                }
                                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getView().getContext(), android.R.layout.simple_spinner_item, arr);
                                AppCompatSpinner mySpinner = (AppCompatSpinner) getView().findViewById(R.id.coursesList);
                                mySpinner.setAdapter(spinnerArrayAdapter);
                                ArrayList<String> regIds = findRegistrationIds(regList, secList, mySpinner.getSelectedItem().toString(), RegisteredCourses);
                                TableLayout table = (TableLayout) getView().findViewById(R.id.ViewAttendanceTable);
                                mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        ArrayList<String> regIds = findRegistrationIds(regList, secList, mySpinner.getSelectedItem().toString(), RegisteredCourses);
                                        table.removeViews(1, Math.max(0, table.getChildCount() - 1));
                                        if(regIds.size()!=0){
                                            dao4 = new FirebaseDAO(new FirebaseDAO.DataObserver() {
                                                @Override
                                                public void update() {
                                                    ArrayList<Attendance> attendanceList = Attendance.load(dao4);
                                                    ArrayList<Attendance> RegisteredAssessment = findAttendance(regIds, attendanceList);
                                                    for (int i = 0; i < RegisteredAssessment.size(); i++) {
                                                        TableRow row = (TableRow) LayoutInflater.from(getContext()).inflate(R.layout.three_text_row, null);
                                                        ((TextView) row.findViewById(R.id.txt1)).setText(RegisteredAssessment.get(i).getLectureNo());
                                                        ((TextView) row.findViewById(R.id.txt2)).setText(RegisteredAssessment.get(i).getDate());
                                                        ((TextView) row.findViewById(R.id.txt3)).setText(RegisteredAssessment.get(i).getStatus());
                                                        table.addView(row);
                                                    }
                                                    table.requestLayout();
                                                }
                                            },"attendance");
                                        }
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            }
                        },"course");
                    }
                },"sections");
            }
        },"registration");
    }

    @Override
    public boolean onBackPressed() {

        return false;

    }
    ArrayList<Course> find(ArrayList<Registration> regList, ArrayList<Section> seclist, ArrayList<Course> courselist) {
        ArrayList<Course> arr = new ArrayList<Course>();
        for (int i = 0; i < regList.size(); i++) {
            for (int j = 0; j < seclist.size(); j++) {
                for (int k = 0; k < courselist.size(); k++) {
                    if (regList.get(i).getSec().equals(seclist.get(j).getId())) {
                        if (seclist.get(j).getCourseId().equals(courselist.get(k).getId())) {
                            arr.add(courselist.get(k));
                        }
                    }
                }
            }
        }
        return arr;
    }
    ArrayList<String> findRegistrationIds(ArrayList<Registration> regList, ArrayList<Section> seclist, String course, ArrayList<Course> regCourses) {
        ArrayList<String> arr = new ArrayList<String>();
        for (int i = 0; i < regList.size(); i++) {
            for (int j = 0; j < seclist.size(); j++) {
                if (regList.get(i).getSec().equals(seclist.get(j).getId())) {
                    for (int k = 0; k < regCourses.size(); k++) {
                        if (course.equals(regCourses.get(k).getName())) {
                            if (seclist.get(j).getCourseId().equals(regCourses.get(k).getId())) {
                                arr.add(regList.get(i).getid());
                            }
                        }
                    }

                }
            }
        }
        return arr;
    }
    ArrayList<Attendance> findAttendance(ArrayList<String> regIds, ArrayList<Attendance> attList) {
        ArrayList<Attendance> RegisteredAssessment = new ArrayList<Attendance>();
        for (int i = 0; i < attList.size(); i++) {
            for (int j = 0; j < regIds.size(); j++) {
                if (attList.get(i).getRegID().equals(regIds.get(j))) {
                    RegisteredAssessment.add(attList.get(i));
                }
            }
        }
        return RegisteredAssessment;
    }
}
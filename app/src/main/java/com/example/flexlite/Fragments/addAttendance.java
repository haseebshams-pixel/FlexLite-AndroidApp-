package com.example.flexlite.Fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flexlite.Activities.TeacherHomeActivity;
import com.example.flexlite.Activities.TeacherLoginActivity;
import com.example.flexlite.Classes.Attendance;
import com.example.flexlite.Classes.Course;
import com.example.flexlite.Classes.Registration;
import com.example.flexlite.Classes.Section;
import com.example.flexlite.Classes.Student;
import com.example.flexlite.Firebase.FirebaseDAO;
import com.example.flexlite.Firebase.IFlexLiteDAO;
import com.example.flexlite.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link addAttendance#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addAttendance extends BaseFragment {

    EditText edittext;
    Calendar myCalendar;
    IFlexLiteDAO dao;
    IFlexLiteDAO dao2;
    IFlexLiteDAO dao3;
    IFlexLiteDAO dao4;
    EditText lectureNo;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public addAttendance() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addAttendance.
     */
    // TODO: Rename and change types and number of parameters
    public static addAttendance newInstance(String param1, String param2) {
        addAttendance fragment = new addAttendance();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        lectureNo = (EditText) getView().findViewById(R.id.lectureNo);
        myCalendar = Calendar.getInstance();
        edittext = (EditText) getView().findViewById(R.id.date);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

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
                                TableLayout table = (TableLayout) getView().findViewById(R.id.addAttendacneTable);
                                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getView().getContext(), android.R.layout.simple_spinner_item, arr);
                                AppCompatSpinner mySpinner = (AppCompatSpinner) getView().findViewById(R.id.registeredSectionList);
                                mySpinner.setAdapter(spinnerArrayAdapter);
                                Section sect = findSection(seclist, mySpinner.getSelectedItem().toString(), user.getUid().toString());
                                ArrayList<Registration> registeredList = findRegistrations(regList, sect.getId());
                                ArrayList<Attendance> attlist = new ArrayList<Attendance>();
                                dao4 = new FirebaseDAO("attendance");
                                mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        Section sect = findSection(seclist, mySpinner.getSelectedItem().toString(), user.getUid().toString());
                                        ArrayList<Registration> registeredList = findRegistrations(regList, sect.getId());
                                        table.removeViews(1, Math.max(0, table.getChildCount() - 1));

                                        for (int i = 0; i < registeredList.size(); i++) {
                                            attlist.add(new Attendance(dao4));
                                            attlist.get(i).setRegID(registeredList.get(i).getid());
                                            TableRow row = (TableRow) LayoutInflater.from(getContext()).inflate(R.layout.add_attendance_table_row, null);
                                            Student std = findStudent(registeredList.get(i), studentList);
                                            ((TextView) row.findViewById(R.id.stud_name)).setText(std.getName());
                                            AppCompatSpinner attendanceSpinner = (AppCompatSpinner) row.findViewById(R.id.attendanceSpinner);
                                            int finalI = i;
                                            attendanceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                @Override
                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                    attlist.get(finalI).setStatus(attendanceSpinner.getSelectedItem().toString());
                                                    if (checkAllFields()) {
                                                        attlist.get(finalI).setDate(edittext.getText().toString());
                                                        attlist.get(finalI).setLectureNo(lectureNo.getText().toString());
                                                        attlist.get(finalI).save();
                                                        Toast.makeText(getContext(), "Attendance Added", Toast.LENGTH_SHORT).show();
                                                    }
                                                }

                                                @Override
                                                public void onNothingSelected(AdapterView<?> parent) {
                                                    attlist.get(finalI).setStatus("L");
                                                }
                                            });
                                            table.addView(row);
                                        }
                                        table.requestLayout();
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                        for (int i = 0; i < registeredList.size(); i++) {
                                            attlist.add(new Attendance(dao4));
                                            attlist.get(i).setRegID(registeredList.get(i).getid());
                                            TableRow row = (TableRow) LayoutInflater.from(getContext()).inflate(R.layout.add_attendance_table_row, null);
                                            Student std = findStudent(registeredList.get(i), studentList);
                                            ((TextView) row.findViewById(R.id.stud_name)).setText(std.getName());
                                            AppCompatSpinner attendanceSpinner = (AppCompatSpinner) getView().findViewById(R.id.attendanceSpinner);
                                            int finalI = i;
                                            attendanceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                @Override
                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                    attlist.get(finalI).setStatus(attendanceSpinner.getSelectedItem().toString());
                                                    if (checkAllFields()) {
                                                        attlist.get(finalI).setDate(edittext.getText().toString());
                                                        attlist.get(finalI).setLectureNo(lectureNo.getText().toString());
                                                        attlist.get(finalI).save();
                                                        Toast.makeText(getContext(), "Attendance Added", Toast.LENGTH_SHORT).show();
                                                    }
                                                }

                                                @Override
                                                public void onNothingSelected(AdapterView<?> parent) {
                                                    attlist.get(finalI).setStatus("L");
                                                }
                                            });
                                            table.addView(row);
                                        }
                                        table.requestLayout();
                                    }
                                });
                            }
                        }, "student");
                    }
                }, "sections");
            }
        }, "registration");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edittext.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_attendance, container, false);
    }

    @Override
    public boolean onBackPressed() {
        return false;
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

    Student findStudent(Registration reg, ArrayList<Student> studList) {
        Student regStudent = null;
        for (int j = 0; j < studList.size(); j++) {
            if (reg.getStud().equals(studList.get(j).getId())) {
                regStudent = studList.get(j);
            }
        }
        return regStudent;
    }

    public boolean checkAllFields() {
        if (lectureNo.getText().toString().isEmpty()) {
            lectureNo.setError("Lecture No Required");
            return false;
        }
        if (edittext.getText().toString().isEmpty()) {
            edittext.setError("Date Required");
            return false;
        }
        return true;
    }
}
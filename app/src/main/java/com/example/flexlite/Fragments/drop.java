package com.example.flexlite.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.flexlite.Classes.Course;
import com.example.flexlite.Classes.Department;
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
 * Use the {@link drop#newInstance} factory method to
 * create an instance of this fragment.
 */
public class drop extends BaseFragment {
    IFlexLiteDAO dao;
    IFlexLiteDAO dao2;
    IFlexLiteDAO dao3;
    IFlexLiteDAO dao4;
    IFlexLiteDAO dao5;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public drop() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment drop.
     */
    // TODO: Rename and change types and number of parameters
    public static drop newInstance(String param1, String param2) {
        drop fragment = new drop();
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
        return inflater.inflate(R.layout.fragment_drop, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        dao4 = new FirebaseDAO(new FirebaseDAO.DataObserver() {
            @Override
            public void update() {
                ArrayList<Department> deptList = Department.load(dao4);
                dao5 = new FirebaseDAO(new FirebaseDAO.DataObserver() {
                    @Override
                    public void update() {
                        Student std = Student.load(dao5,user.getUid().toString());
                        Department dept = findDepartment(std,deptList);
                        if(!dept.getIsRegOpen().equals("1")){
                            FragmentTransaction fragmentTransaction = getActivity()
                                    .getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.container, new drop2());
                            fragmentTransaction.commit();
                        }
                    }
                },"student");
            }
        },"department");
        dao = new FirebaseDAO(new FirebaseDAO.DataObserver() {
            @Override
            public void update() {
                ArrayList<Registration> regList = Registration.load(dao, user.getUid().toString());
                dao2 = new FirebaseDAO(new FirebaseDAO.DataObserver() {
                    @Override
                    public void update() {
                        ArrayList<Section> sectionList = Section.load(dao2);
                        dao3 = new FirebaseDAO(new FirebaseDAO.DataObserver() {
                            @Override
                            public void update() {
                                ArrayList<Course> courseList = Course.load(dao3);
                                TableLayout table = (TableLayout) getView().findViewById(R.id.dropTable);
                                for (int i = 0; i < regList.size(); i++) {
                                    for (int j = 0; j < sectionList.size(); j++) {
                                        for (int k = 0; k < courseList.size(); k++) {
                                            if (regList.get(i).getSec().equals(sectionList.get(j).getId()) && regList.get(i).getstatus().equals("Registered")) {
                                                if (sectionList.get(j).getCourseId().equals(courseList.get(k).getId())) {
                                                    // Inflate your row "template" and fill out the fields.
                                                    TableRow row = (TableRow) LayoutInflater.from(getContext()).inflate(R.layout.text_button_row, null);
                                                    ((TextView) row.findViewById(R.id.courseName)).setText(courseList.get(k).getName());
                                                    TextView btn = (TextView) row.findViewById(R.id.btn);
                                                    int finalI = i;
                                                    btn.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            table.removeView(row);
                                                            //dao4 = new FirebaseDAO("registration");
                                                            dao.delete(regList.get(finalI).getid());
                                                            FragmentTransaction fragmentTransaction = getActivity()
                                                                    .getSupportFragmentManager().beginTransaction();
                                                            fragmentTransaction.replace(R.id.container, new drop());
                                                            fragmentTransaction.commit();
                                                        }
                                                    });
                                                    table.addView(row);
                                                }
                                            }
                                        }
                                    }
                                }
                                table.requestLayout();
                            }
                        }, "course");
                    }
                }, "sections");
            }
        }, "registration");
    }

    @Override
    public boolean onBackPressed() {

        return false;

    }
    Department findDepartment(Student std,ArrayList<Department> deptList){
        Department dep=null;
        for(int i=0;i<deptList.size();i++){
            if(deptList.get(i).getId().equals(std.getDeptId())){
                dep=deptList.get(i);
                break;
            }
        }
        return dep;
    }
}
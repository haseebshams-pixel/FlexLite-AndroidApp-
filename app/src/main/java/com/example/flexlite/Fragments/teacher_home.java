package com.example.flexlite.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.flexlite.Classes.Student;
import com.example.flexlite.Classes.Teacher;
import com.example.flexlite.Firebase.FirebaseDAO;
import com.example.flexlite.Firebase.IFlexLiteDAO;
import com.example.flexlite.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link teacher_home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class teacher_home extends BaseFragment {

    IFlexLiteDAO dao;
    AdView mAdView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public teacher_home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment teacher_home.
     */
    // TODO: Rename and change types and number of parameters
    public static teacher_home newInstance(String param1, String param2) {
        teacher_home fragment = new teacher_home();
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
        return inflater.inflate(R.layout.fragment_teacher_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdView = getView().findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        TextView degree = (TextView) getView().findViewById(R.id.degree);
        TextView tch_name = (TextView) getView().findViewById(R.id.tch_name);
        TextView cnic = (TextView) getView().findViewById(R.id.cnic);
        TextView dob = (TextView) getView().findViewById(R.id.dob);
        TextView gender = (TextView) getView().findViewById(R.id.gender);
        TextView email = (TextView) getView().findViewById(R.id.email);
        TextView phoneno = (TextView) getView().findViewById(R.id.phoneno);
        TextView address = (TextView) getView().findViewById(R.id.address);
        TextView salary = (TextView) getView().findViewById(R.id.salary);
        dao = new FirebaseDAO(new FirebaseDAO.DataObserver() {
            @Override
            public void update() {
                Teacher tch = Teacher.load(dao,user.getUid().toString());
                salary.setText(tch.getSalary());
                degree.setText(tch.getDegree());
                tch_name.setText(tch.getName());
                cnic.setText(tch.getCNIC());
                dob.setText(tch.getDOB());
                gender.setText(tch.getGender());
                email.setText(tch.getEmail());
                phoneno.setText(tch.getMobileno());
                address.setText(tch.getAddress());
            }
        },"teacher");
    }

    @Override
    public boolean onBackPressed() {

        return false;

    }
}
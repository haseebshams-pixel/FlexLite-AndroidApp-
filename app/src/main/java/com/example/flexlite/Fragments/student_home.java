package com.example.flexlite.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.flexlite.Classes.Student;
import com.example.flexlite.Firebase.FirebaseDAO;
import com.example.flexlite.Firebase.IFlexLiteDAO;
import com.example.flexlite.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link student_home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class student_home extends BaseFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    IFlexLiteDAO dao;
    AdView mAdView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public student_home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment student_home.
     */
    // TODO: Rename and change types and number of parameters
    public static student_home newInstance(String param1, String param2) {
        student_home fragment = new student_home();
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
        return inflater.inflate(R.layout.fragment_student_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdView = getView().findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        TextView rollno = (TextView) getView().findViewById(R.id.rollno);
        TextView batch = (TextView) getView().findViewById(R.id.batchno);
        TextView degree = (TextView) getView().findViewById(R.id.degree);
        TextView std_name = (TextView) getView().findViewById(R.id.student_name);
        TextView cnic = (TextView) getView().findViewById(R.id.cnic);
        TextView dob = (TextView) getView().findViewById(R.id.dob);
        TextView gender = (TextView) getView().findViewById(R.id.gender);
        TextView email = (TextView) getView().findViewById(R.id.email);
        TextView phoneno = (TextView) getView().findViewById(R.id.phoneno);
        TextView address = (TextView) getView().findViewById(R.id.address);
        dao = new FirebaseDAO(new FirebaseDAO.DataObserver() {
            @Override
            public void update() {
                Student std = Student.load(dao,user.getUid().toString());
                rollno.setText(std.getRollno());
                batch.setText(std.getBatch());
                degree.setText(std.getDegree());
                std_name.setText(std.getName());
                cnic.setText(std.getcnic());
                dob.setText(std.getdob());
                gender.setText(std.getGender());
                email.setText(std.getEmail());
                phoneno.setText(std.getMobileno());
                address.setText(std.getAddress());
            }
        },"student");
    }

    @Override
    public boolean onBackPressed() {

            return false;

    }
}
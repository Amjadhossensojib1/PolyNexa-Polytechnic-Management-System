package com.orixvisiontechnologies.polynexa.ui.studentRoutineFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orixvisiontechnologies.polynexa.R;


public class StudentRoutineFragment extends Fragment {


    public StudentRoutineFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_routine, container, false);

        return view;
    }
}
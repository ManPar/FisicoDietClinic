package com.fisicodietclinic.fisicodietclinic.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.fisicodietclinic.fisicodietclinic.BMIDetails;
import com.fisicodietclinic.fisicodietclinic.R;
import com.fisicodietclinic.fisicodietclinic.adapters.diet_adapter;
import com.fisicodietclinic.fisicodietclinic.models.Diet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manyamadan on 08/12/17.
 */

public class BMIFragment extends Fragment {


    private RecyclerView recyclerView;
    private diet_adapter adapter;
    private List<Diet> dietsList = new ArrayList<>();
    ProgressDialog dialog;
    String response_string;
    Button calculate;
    EditText edit_weight,edit_height;
    String weight,height;

    public static BMIFragment newInstance() {
        BMIFragment fragment = new BMIFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bmi, container, false);

            calculate = (Button) view.findViewById(R.id.id_calculate);
        edit_height = (EditText) view.findViewById(R.id.input_edit_height);
        edit_weight = (EditText) view.findViewById(R.id.input_edit_weight);


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                weight = edit_weight.getText().toString();
                height = edit_height.getText().toString();
                Intent intent = new Intent(getActivity(), BMIDetails.class);
                intent.putExtra("weight",weight);
                intent.putExtra("height",height);
                startActivity(intent);

            }
        });





        return view;
    }
}
